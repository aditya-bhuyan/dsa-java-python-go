# Benchmark — Climbing Stairs

## Algorithm Used

**Space-Optimised Bottom-Up DP** — compute `f(n) = f(n-1) + f(n-2)` iteratively using two rolling variables. No array allocated.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n) | Single loop from 2 to n |
| Space | O(1) | Two integer variables only |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Two-variable rolling DP | `prev2, prev1` updated in-place |
| Java | Two-variable rolling DP | Same; no boxing overhead |
| Python | Two-variable rolling DP | Tuple swap idiom |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

All O(n) O(1); Go fastest due to no interpreter overhead. For n ≤ 45 the absolute time is negligible in all three.

---

## Benchmark Results (Indicative — n = 45)

| Language | Approx Time |
|---|---|
| Go | < 0.001 ms |
| Java | < 0.001 ms |
| Python | < 0.001 ms |

*At n=45 all three complete in under 1 microsecond. The benchmark is effectively a constant-time operation.*

---

## References

- [LeetCode 70 — Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
- [LeetCode 509 — Fibonacci Number](https://leetcode.com/problems/fibonacci-number/) (same recurrence)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
