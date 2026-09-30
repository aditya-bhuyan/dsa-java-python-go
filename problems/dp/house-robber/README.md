# House Robber

## Problem Statement

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. The only constraint stopping you from robbing each of them is that **adjacent houses have security systems connected** — it will automatically contact the police if two adjacent houses are broken into on the same night.

Given an integer array `nums` representing the amount of money in each house, return the **maximum amount of money you can rob** tonight without alerting the police.

**LeetCode:** [198. House Robber](https://leetcode.com/problems/house-robber/)  
**Difficulty:** Medium  
**Topic Tags:** Array, Dynamic Programming

---

## Examples

### Example 1
```
Input:  nums = [1, 2, 3, 1]
Output: 4
Explanation: Rob house 0 (money=1) then house 2 (money=3). Total = 1+3 = 4.
```

### Example 2
```
Input:  nums = [2, 7, 9, 3, 1]
Output: 12
Explanation: Rob house 0 (2) + house 2 (9) + house 4 (1). Total = 12.
```

---

## Constraints

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 400`

---

## Understanding the Problem

At each house `i`, we make a binary decision:

- **Rob house i:** gain `nums[i]`, but the previous house `i-1` must have been skipped. Best we could do before `i` = `dp[i-2]`.
- **Skip house i:** best we could do through `i-1` = `dp[i-1]`.

```
dp[i] = max(dp[i-1],          ← skip house i
            dp[i-2] + nums[i]) ← rob house i
```

This is the canonical **"include or exclude"** DP pattern.

### Visual
```
nums = [2, 7, 9, 3, 1]

      rob?  skip?
  i=0: 2    0      → dp[0] = 2
  i=1: 2+0? 2      → dp[1] = max(2, 7) = 7    (skip i=0, rob i=1)
  i=2: 2+9? 7      → dp[2] = max(7, 2+9) = 11
  i=3: 7+3? 11     → dp[3] = max(11, 7+3) = 11
  i=4: 11+1?11     → dp[4] = max(11, 11+1) = 12

Answer: 12 ✓
```

---

## Approach 1: Brute Force (Exponential)

Try every subset of non-adjacent houses, track the maximum sum.

**Time:** O(2ⁿ) — exponential.  
Not feasible for `n = 100`.

---

## Approach 2: Top-Down DP (Memoization)

```
memo = {}
rob(i):
    if i < 0:  return 0
    if i in memo: return memo[i]
    memo[i] = max(rob(i-1),           # skip
                  rob(i-2) + nums[i]) # rob
    return memo[i]
return rob(n-1)
```

**Time:** O(n)  **Space:** O(n) memo + O(n) stack.

---

## Approach 3: Bottom-Up DP (Tabulation)

```
dp = [0] * (n + 1)
dp[0] = 0          # no houses yet
dp[1] = nums[0]    # only one house: rob it
for i = 2 to n:
    dp[i] = max(dp[i-1], dp[i-2] + nums[i-1])
return dp[n]
```

### Dry Run (nums = [2, 7, 9, 3, 1])
```
dp[0] = 0
dp[1] = 2
dp[2] = max(dp[1], dp[0]+7) = max(2, 7) = 7
dp[3] = max(dp[2], dp[1]+9) = max(7, 11) = 11
dp[4] = max(dp[3], dp[2]+3) = max(11, 10) = 11
dp[5] = max(dp[4], dp[3]+1) = max(11, 12) = 12

Answer: dp[5] = 12 ✓
```

### DP Table
```
House:  —  0  1  2  3  4
nums:      2  7  9  3  1
dp:     0  2  7 11 11 12
```

---

## Approach 4: Space-Optimised (O(1))

Only two previous values are needed:

```
prev2 = 0          (dp[i-2])
prev1 = nums[0]    (dp[i-1])
for i = 1 to n-1:
    curr = max(prev1, prev2 + nums[i])
    prev2 = prev1
    prev1 = curr
return prev1
```

**Time:** O(n)  **Space:** O(1)

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | O(2ⁿ) | O(n) |
| Top-Down (Memoization) | O(n) | O(n) |
| Bottom-Up (Tabulation) | O(n) | O(n) |
| Space-Optimised | O(n) | O(1) |

---

## Edge Cases

| Case | Expected |
|---|---|
| Single house `[5]` | 5 |
| Two houses `[2, 9]` | 9 |
| All same values `[3,3,3,3]` | 6 (rob alternating) |
| All zeros `[0,0,0]` | 0 |
| Strictly increasing `[1,2,3,4,5]` | 9 (rob 1+3+5) |

---

## Interview Discussion Points

1. **Why can't we use a greedy approach?** — Greedy (always rob the most lucrative adjacent house) fails on inputs like `[2,1,1,2]` — greedy picks 2+1=3, but optimal is 2+2=4.
2. **Circular version (House Robber II)?** — LeetCode 213: houses form a circle. Solve twice — once excluding the first house, once excluding the last — take the max.
3. **Tree version (House Robber III)?** — LeetCode 337: DP on a tree where children are adjacent. Each node stores `(rob_this_node, skip_this_node)`.
4. **What if you can skip at most one house between robberies?** — Modify the recurrence to `dp[i] = max(dp[i-1], dp[i-2] + nums[i], dp[i-3] + nums[i])`.

---

## Common Mistakes

- `dp[1] = nums[0]` not `dp[1] = 1` — `dp[1]` is the max money from the first house, not the count of choices.
- Off-by-one: when using 1-indexed DP array, `dp[i]` corresponds to `nums[i-1]`.
- Forgetting the single-element base case — `n=1` must return `nums[0]`, not 0.

---

## Key Takeaways

- **Include or exclude** is the most common DP decision pattern. At each element, you either take it (skipping the previous) or skip it (keeping the previous best).
- The space optimisation to O(1) using two rolling variables applies whenever only a fixed window of previous states is needed.
- House Robber is the prototype for dozens of "pick non-adjacent elements to maximise sum" variants.

---

## Next Problem

➡️ [Coin Change](../coin-change/README.md)
