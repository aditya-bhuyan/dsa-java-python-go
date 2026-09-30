# Dynamic Programming Problems

> **Week 10** · Core concept: optimal substructure + overlapping subproblems → memoization or tabulation.

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Climbing Stairs | Easy | [climbing-stairs/](climbing-stairs/) |
| 2 | House Robber | Medium | [house-robber/](house-robber/) |
| 3 | Coin Change | Medium | [coin-change/](coin-change/) |
| 4 | Longest Increasing Subsequence | Medium | [longest-increasing-subsequence/](longest-increasing-subsequence/) |

## Key Patterns
- **1D DP** — `dp[i]` depends on `dp[i-1]` and/or `dp[i-2]` (Climbing Stairs, House Robber)
- **Unbounded knapsack** — each item reusable; iterate amounts then coins (Coin Change)
- **LIS recurrence** — `dp[i] = max(dp[j]+1)` for all `j < i` where `arr[j] < arr[i]`
- **Space optimisation** — reduce 1D DP to two variables when only last 1–2 states needed

## Recurrences
| Problem | Recurrence |
|---------|------------|
| Climbing Stairs | `dp[i] = dp[i-1] + dp[i-2]` |
| House Robber | `dp[i] = max(dp[i-1], dp[i-2] + nums[i])` |
| Coin Change | `dp[i] = min(dp[i], dp[i-coin] + 1)` for each coin |
| LIS | `dp[i] = max(dp[j] + 1)` for `j < i`, `arr[j] < arr[i]` |

## Concepts
→ [concepts/dynamic-programming.md](../../concepts/dynamic-programming.md)  
→ [concepts/recursion.md](../../concepts/recursion.md)

← [Back to problems](../README.md)
