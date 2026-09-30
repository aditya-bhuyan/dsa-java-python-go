# Benchmark — House Robber

## Algorithm Used

**Space-Optimised Bottom-Up DP** — iterate through `nums` once, maintaining only `prev2` (best up to `i-2`) and `prev1` (best up to `i-1`). At each step: `curr = max(prev1, prev2 + nums[i])`.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n) | Single pass through the array |
| Space | O(1) | Two integer variables |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Rolling two-variable DP | Idiomatic range loop |
| Java | Rolling two-variable DP | Simple for-loop |
| Python | Rolling two-variable DP | Tuple unpacking swap |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

For n ≤ 100 all three are effectively instantaneous. The ordering holds for larger inputs.

---

## Benchmark Results (Indicative — n = 100)

| Language | Approx Time |
|---|---|
| Go | < 0.001 ms |
| Java | < 0.001 ms |
| Python | ~0.005 ms |

---

## References

- [LeetCode 198 — House Robber](https://leetcode.com/problems/house-robber/)
- [LeetCode 213 — House Robber II](https://leetcode.com/problems/house-robber-ii/) (circular)
- [LeetCode 337 — House Robber III](https://leetcode.com/problems/house-robber-iii/) (tree)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
