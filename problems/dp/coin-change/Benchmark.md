# Benchmark — Coin Change

## Algorithm Used

**Bottom-Up Unbounded Knapsack DP** — fill `dp[0..amount]` where `dp[a] = min(dp[a], dp[a-coin]+1)` for each coin at each amount. Sentinel `amount+1` used for "unreachable."

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(amount × k) | k = number of coin denominations |
| Space | O(amount) | DP array of size amount+1 |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Bottom-up DP, sentinel `amount+1` | Range over coins slice |
| Java | Bottom-up DP, sentinel `amount+1` | Nested for-loops |
| Python | Bottom-up DP, `float('inf')` sentinel | `min()` idiom |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

- **Go**: Direct array access, no boxing, tight inner loop.
- **Java**: JIT-compiled hot loop performs well; slight overhead from bounds checks.
- **Python**: Interpreter overhead noticeable at `amount=10⁴` with 12 coins.

---

## Benchmark Results (Indicative — amount = 10,000, 12 coins)

| Language | Approx Time |
|---|---|
| Go | ~0.5 ms |
| Java | ~1.5 ms |
| Python | ~25 ms |

---

## References

- [LeetCode 322 — Coin Change](https://leetcode.com/problems/coin-change/)
- [LeetCode 518 — Coin Change II](https://leetcode.com/problems/coin-change-ii/) (count combinations, not min coins)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
