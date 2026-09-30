# Flood Fill

## Problem Statement

An image is represented by an `m x n` integer grid `image` where `image[i][j]` represents the pixel value of the image.

You are given three integers `sr`, `sc`, and `color`. Perform a **flood fill** starting from the pixel `image[sr][sc]`.

To perform a flood fill, consider the starting pixel, plus any pixels connected **4-directionally** to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (of the same color), and so on. Replace the color of all mentioned pixels with `color`.

Return the modified image after performing the flood fill.

**LeetCode:** [733. Flood Fill](https://leetcode.com/problems/flood-fill/)  
**Difficulty:** Easy  
**Topic Tags:** Array, Depth-First Search, Breadth-First Search, Matrix

---

## Examples

### Example 1
```
Input:  image = [[1,1,1],[1,1,0],[1,0,1]], sr=1, sc=1, color=2
Output: [[2,2,2],[2,2,0],[2,0,1]]
```

### Example 2
```
Input:  image = [[0,0,0],[0,0,0]], sr=0, sc=0, color=0
Output: [[0,0,0],[0,0,0]]
Explanation: New color == original color; no change needed.
```

---

## Constraints

- `m == image.length`
- `n == image[i].length`
- `1 <= m, n <= 50`
- `0 <= image[i][j], color < 2^16`
- `0 <= sr < m`, `0 <= sc < n`

---

## Understanding the Problem

Classic paint-bucket tool: starting from a seed pixel, color all connected same-color pixels with the new color. Stop at boundaries or pixels with a different color.

### Key Observations
1. Only 4-directional (up/down/left/right) connectivity counts.
2. If the starting pixel already has the target color, no change is needed (prevents infinite loops).
3. This is DFS/BFS on a grid — identical structure to Number of Islands.

### Visual Representation
```
Before:           After (fill from (1,1) with color=2):
  1 1 1               2 2 2
  1 1 0               2 2 0    ← (1,2) is 0 ≠ original, stops
  1 0 1               2 0 1    ← (2,1) is 0, (2,2) is isolated 1
```

---

## Approach 1: Brute Force (no early exit)

Check every cell and recolor if it's connected to the start and has the original color. Without DFS/BFS there is no efficient way to determine connectivity → effectively O((m×n)²).

---

## Approach 2: DFS (Optimal)

**Idea:**
1. Capture `origColor = image[sr][sc]`.
2. If `origColor == color`, return immediately (no-op — prevents infinite recursion).
3. DFS from `(sr, sc)`: set cell to `color`, recurse into 4 neighbors if they are in-bounds and equal to `origColor`.

### Algorithm Steps
```
origColor = image[sr][sc]
if origColor == color: return image

dfs(r, c):
    if out of bounds: return
    if image[r][c] != origColor: return
    image[r][c] = color
    dfs(r-1,c); dfs(r+1,c); dfs(r,c-1); dfs(r,c+1)

dfs(sr, sc)
return image
```

### Dry Run (Example 1)
```
origColor=1, color=2
dfs(1,1): image[1][1]=2
  dfs(0,1): image[0][1]=2
    dfs(-1,1): OOB
    dfs(1,1): color≠origColor (already 2) → stop
    dfs(0,0): image[0][0]=2 → fills (0,0) → left/right/up OOB or wrong color
    dfs(0,2): image[0][2]=2 → similar
  dfs(2,1): image[2][1]=0 ≠ 1 → stop
  dfs(1,0): image[1][0]=2 → continues
  dfs(1,2): image[1][2]=0 ≠ 1 → stop
Result: [[2,2,2],[2,2,0],[2,0,1]] ✓
```

---

## Approach 3: BFS (Iterative)

Same logic using a queue — preferred when grid is very large.

```
queue = deque([(sr, sc)])
image[sr][sc] = color
while queue:
    r, c = queue.popleft()
    for dr, dc in dirs:
        nr, nc = r+dr, c+dc
        if valid(nr,nc) and image[nr][nc] == origColor:
            image[nr][nc] = color
            queue.append((nr,nc))
```

---

## Complexity Summary

| Approach | Time | Space | Notes |
|---|---|---|---|
| Brute Force | O((m×n)²) | O(1) | No real connectivity |
| DFS | O(m×n) | O(m×n) | Recursion stack |
| BFS | O(m×n) | O(m×n) | Queue |

---

## Edge Cases

| Case | Expected |
|---|---|
| New color == original color | Return unchanged image |
| Seed pixel is isolated (surrounded by different colors) | Only seed pixel recolored |
| Entire grid same color | Entire grid recolored |
| 1×1 grid | Single pixel recolored |

---

## Interview Discussion Points

1. **Why check `origColor == color` first?** — Without this, the DFS modifies the cell to `color` and then immediately sees `color ≠ origColor` in every neighbor, causing it to terminate early — but the real danger is an infinite loop if `color == origColor` since we'd keep re-visiting the same cell.
2. **Same as Number of Islands?** — Structurally yes. Flood Fill is the more general primitive; Number of Islands uses it to count components.
3. **Can we avoid mutating the image?** — Use a `visited` boolean grid; trades code simplicity for no mutation.

---

## Common Mistakes

- Not handling the `origColor == color` edge case → either wrong result or infinite recursion.
- Checking `image[r][c] != color` instead of `image[r][c] != origColor` in the DFS base case → misses cells that happen to already have the new color.
- Using 8-directional instead of 4-directional fill when problem says 4-directional.

---

## Key Takeaways

- Flood Fill is the **simplest grid DFS problem** and the building block for Number of Islands, Surrounded Regions, Pacific Atlantic Water Flow, and many more.
- The `origColor == color` early-exit guard is the one surprising edge case interviewers love to probe.
- Mastering this template makes all grid graph problems feel familiar.

---

## Next Problem

➡️ [Back to Graph Concepts](../../concepts/graph.md)
