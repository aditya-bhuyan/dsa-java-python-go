# Number of Islands

## Problem Statement

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return the number of islands.

An **island** is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are surrounded by water.

**LeetCode:** [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)  
**Difficulty:** Medium  
**Topic Tags:** Array, Depth-First Search, Breadth-First Search, Union Find, Matrix

---

## Examples

### Example 1
```
Input:
grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
```

### Example 2
```
Input:
grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
```

---

## Constraints

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 300`
- `grid[i][j]` is `'0'` or `'1'`

---

## Understanding the Problem

Think of the grid as a map. Every connected group of `'1'` cells (connected horizontally or vertically) forms one island. We need to **count how many such groups exist**.

### Key Observations
1. Two cells belong to the same island if you can walk from one to the other through adjacent `'1'` cells.
2. Diagonal connections do NOT count.
3. We must visit every cell exactly once → classic graph traversal.

### Visual Representation
```
Grid (Example 2):
  1 1 0 0 0
  1 1 0 0 0       Island A = top-left 2×2 block
  0 0 1 0 0       Island B = center cell
  0 0 0 1 1       Island C = bottom-right pair

Answer = 3
```

---

## Approach 1: Brute Force (Naive BFS/DFS without marking)

**Idea:** For every `'1'` cell, do a BFS/DFS to find all connected cells — but don't mark them. Count how many times we enter a new island from any cell.

**Why it fails:** Without marking visited cells we re-count the same island from every one of its cells → grossly overcounts and has exponential re-processing.

**Time Complexity:** O((m×n)²) worst case  
**Space Complexity:** O(m×n)

---

## Approach 2: DFS Flood Fill (Optimal)

**Idea:**
1. Iterate every cell in the grid.
2. When we find a `'1'`, increment our island count and **flood-fill** the entire island by recursively (DFS) marking every connected `'1'` as `'0'` (visited/water).
3. Because we mutate the grid in place, each land cell is visited exactly once.

### Algorithm Steps
```
count = 0
for each cell (r, c):
    if grid[r][c] == '1':
        count++
        dfs(grid, r, c)   ← sinks the whole island

dfs(grid, r, c):
    if out of bounds OR grid[r][c] != '1': return
    grid[r][c] = '0'      ← mark visited
    dfs(grid, r-1, c)     ← up
    dfs(grid, r+1, c)     ← down
    dfs(grid, r, c-1)     ← left
    dfs(grid, r, c+1)     ← right
```

### Dry Run (Example 1 — single island)
```
Start scan at (0,0) → '1' → count=1, flood-fill entire island
After DFS from (0,0):
  0 0 0 0 0
  0 0 0 0 0
  0 0 0 0 0
  0 0 0 0 0
All cells now '0' → no more islands found
Answer = 1 ✓
```

### Dry Run (Example 2 — three islands)
```
Scan (0,0)='1' → count=1, sink A:
  0 0 0 0 0
  0 0 0 0 0
  0 0 1 0 0
  0 0 0 1 1

Scan... skip zeros... reach (2,2)='1' → count=2, sink B:
  0 0 0 0 0
  0 0 0 0 0
  0 0 0 0 0
  0 0 0 1 1

Scan... reach (3,3)='1' → count=3, sink C:
  (all zeros)
Answer = 3 ✓
```

---

## Approach 3: BFS Flood Fill

Same idea, but use a queue instead of recursion. Avoids potential call-stack overflow on very large grids.

```
dfs replaced with:
queue = [(r, c)]
grid[r][c] = '0'
while queue not empty:
    (row, col) = dequeue
    for each of 4 neighbours:
        if in bounds and grid[neighbour] == '1':
            grid[neighbour] = '0'
            enqueue(neighbour)
```

**Time / Space:** Same as DFS — O(m×n) / O(min(m,n)) for BFS queue vs O(m×n) recursion stack for DFS.

---

## Approach 4: Union-Find

**Idea:** Treat each `'1'` cell as a node. Union adjacent `'1'` pairs. Count of distinct components at the end = number of islands.

**Time:** O(m×n × α(m×n)) ≈ O(m×n)  
**Space:** O(m×n)

Useful when the grid updates dynamically (online queries). For a static grid, DFS/BFS is simpler.

---

## Complexity Summary

| Approach | Time | Space | Notes |
|---|---|---|---|
| Brute Force | O((m×n)²) | O(m×n) | Incorrect / exponential |
| DFS Flood Fill | O(m×n) | O(m×n) | Recursion stack |
| BFS Flood Fill | O(m×n) | O(min(m,n)) | Queue size ≤ perimeter |
| Union-Find | O(m×n·α) | O(m×n) | Good for dynamic grids |

**Recommended:** DFS Flood Fill for interview — simple, O(m×n), easy to explain.

---

## Edge Cases

| Case | Expected |
|---|---|
| All water `"0"` | 0 |
| All land `"1"` | 1 |
| Single cell `"1"` | 1 |
| Single cell `"0"` | 0 |
| 1×n row of alternating `"1","0"` | n/2 |
| Diagonal-only `"1"` cells | each cell = its own island |

---

## Interview Discussion Points

1. **Can we modify the grid?** — DFS in-place is cleanest. If not allowed, use a `visited` boolean matrix.
2. **Stack overflow risk?** — For a 300×300 all-land grid DFS depth = 90 000. In Python this hits the default recursion limit; use BFS or increase `sys.setrecursionlimit`.
3. **Why not diagonal?** — Problem says horizontal/vertical only. Clarify this with interviewer.
4. **Follow-up: what if the grid is a stream?** → Union-Find shines here.
5. **Follow-up: count the size of each island?** → Track island size during DFS and store in a list.

---

## Common Mistakes

- Forgetting to mark cells visited → infinite loops / wrong count.
- Using diagonal directions → overcounts islands.
- Not restoring grid if problem says immutable input (use `visited` matrix instead).
- Off-by-one in boundary checks.

---

## Key Takeaways

- **Flood fill = DFS/BFS from a source, marking visited cells** — this pattern appears in dozens of problems (flood fill, word search, surrounded regions, …).
- Grid problems map naturally to graphs: cells = nodes, adjacency = edges.
- DFS on a grid is O(m×n) — each cell visited at most once.
- When recursion depth is a concern, BFS is the safe alternative.

---

## Next Problem

➡️ [Clone Graph](../clone-graph/README.md)
