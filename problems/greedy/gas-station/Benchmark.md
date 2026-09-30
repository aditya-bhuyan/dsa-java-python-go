# Benchmark — Gas Station

## Algorithm Used

**Greedy — Single Pass with Running Sum and Reset** — compute `diff[i] = gas[i] - cost[i]` on the fly; track `total` (overall feasibility) and `current` (tank from current candidate start). When `current < 0`, reset it to `0` and advance `start` to `i + 1`.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n) | Single pass |
| Space | O(1) | Three integer variables: `total`, `current`, `start` |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Greedy single scan | Idiomatic range loop |
| Java | Greedy single scan | Standard for-loop |
| Python | Greedy single scan | `enumerate` loop |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

---

## Benchmark Results (Indicative — n = 10⁵)

| Language | Approx Time |
|---|---|
| Go | ~0.05 ms |
| Java | ~0.15 ms |
| Python | ~1.5 ms |

---

## References

- [LeetCode 134 — Gas Station](https://leetcode.com/problems/gas-station/)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
