# Jump Game

## Problem Statement

You are given an integer array `nums`. You are initially positioned at the **first index**, and each element represents your **maximum jump length** from that position.

Return `true` if you can reach the last index, or `false` otherwise.

**LeetCode:** [55. Jump Game](https://leetcode.com/problems/jump-game/)  
**Difficulty:** Medium  
**Topic Tags:** Array, Dynamic Programming, Greedy

---

## Examples

### Example 1
```
Input:  nums = [2, 3, 1, 1, 4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```

### Example 2
```
Input:  nums = [3, 2, 1, 0, 4]
Output: false
Explanation: No matter what, you will always arrive at index 3.
             Its maximum jump length is 0, which makes it impossible to reach index 4.
```

---

## Constraints

- `1 <= nums.length <= 10⁴`
- `0 <= nums[i] <= 10⁵`

---

## Understanding the Problem

We start at index `0`. From any index `i` we can jump to any index `j` where `i < j <= i + nums[i]`. We want to know whether index `n - 1` is reachable.

### Key Observation
We do **not** need to enumerate all possible jump paths. We only need to track **how far right we can possibly reach** at each step. If at any point we are standing on an index beyond our current reach, we are stuck.

### Visual
```
nums = [2, 3, 1, 1, 4]
Index:  0  1  2  3  4

From index 0 (jump up to 2): can reach 1, 2  → maxReach = 2
From index 1 (jump up to 3): can reach 2,3,4 → maxReach = 4
Index 4 = last index. Reachable ✓
```

---

## Approach 1: Brute Force (BFS/DFS)

**Idea:** From every reachable index, try all possible jumps and check if the last index is ever reached.

```
visited = {0}
queue = [0]
while queue:
    i = dequeue
    for j = i+1 to i+nums[i]:
        if j == n-1: return True
        if j not visited: enqueue j, mark visited
return False
```

**Time:** O(n²)  **Space:** O(n)

---

## Approach 2: Dynamic Programming

**Idea:** `dp[i] = true` if index `i` is reachable. For each reachable `i`, mark `i+1 .. i+nums[i]` as reachable.

**Time:** O(n²)  **Space:** O(n)  — better than BFS but still sub-optimal.

---

## Approach 3: Greedy — Track Maximum Reach (Optimal)

**Idea:** Maintain `maxReach` = the farthest index reachable so far. For each index `i`:
1. If `i > maxReach` → index `i` is unreachable → return `false`.
2. Update `maxReach = max(maxReach, i + nums[i])`.
3. If `maxReach >= n-1` → last index is reachable → can return `true` early.

### Algorithm
```
maxReach = 0
for i = 0 to n-1:
    if i > maxReach: return false
    maxReach = max(maxReach, i + nums[i])
return true
```

### Dry Run — Example 1
```
nums = [2, 3, 1, 1, 4],  n = 5,  maxReach = 0

i=0: 0 <= 0 ✓, maxReach = max(0, 0+2) = 2
i=1: 1 <= 2 ✓, maxReach = max(2, 1+3) = 4
i=2: 2 <= 4 ✓, maxReach = max(4, 2+1) = 4
i=3: 3 <= 4 ✓, maxReach = max(4, 3+1) = 4
i=4: 4 <= 4 ✓, maxReach = max(4, 4+4) = 8
return true ✓
```

### Dry Run — Example 2
```
nums = [3, 2, 1, 0, 4],  n = 5,  maxReach = 0

i=0: maxReach = max(0, 0+3) = 3
i=1: maxReach = max(3, 1+2) = 3
i=2: maxReach = max(3, 2+1) = 3
i=3: maxReach = max(3, 3+0) = 3
i=4: 4 > 3 → return false ✓
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force (BFS) | O(n²) | O(n) |
| Dynamic Programming | O(n²) | O(n) |
| Greedy | O(n) | O(1) |

---

## Edge Cases

| Case | Expected |
|---|---|
| Single element `[0]` | `true` — already at last index |
| Single element `[5]` | `true` — already at last index |
| All zeros `[0, 0, 0]` | `false` — stuck at index 0 |
| All ones `[1, 1, 1, 1]` | `true` — can always reach next |
| Last element is 0 `[1, 2, 3, 0]` | `true` — 0 at the end doesn't matter |
| First element is 0, `n > 1` | `false` — can't move from index 0 |

---

## Interview Discussion Points

1. **Why greedy over DP?** — DP needs O(n) space and O(n²) time in the straightforward version. Greedy is O(n) time, O(1) space — strictly better.
2. **Greedy choice property proof:** At each step, extending `maxReach` captures every possible jump from every reachable index seen so far. No future information could improve what we've already tracked.
3. **Follow-up: Jump Game II — minimum number of jumps?** → LeetCode 45 — same greedy idea but track current window end and count jumps.
4. **What if `nums[i]` can be negative?** — Problem guarantees `nums[i] >= 0`; but if negatives were allowed, the greedy approach would still work (negative values could not extend reach).

---

## Common Mistakes

- Returning `false` when `i == maxReach` instead of `i > maxReach` — index `i == maxReach` is still reachable.
- Forgetting the single-element case — `n == 1` means we're already at the destination; always `true`.
- Updating `maxReach` after checking, instead of before — order matters: check first, update second.

---

## Key Takeaways

- **Greedy insight:** Instead of tracking all reachable positions, track only the farthest one.
- `maxReach` acts as a "horizon" — anything beyond it is unreachable.
- The pattern "scan + update global best" recurs across many greedy problems (e.g., Best Time to Buy and Sell Stock).

---

## Next Problem

➡️ [Gas Station](../gas-station/README.md)
