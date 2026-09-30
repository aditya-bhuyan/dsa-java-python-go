# Benchmark — Maximum Average Subarray I

## Algorithm Used

**Fixed Sliding Window** — compute the sum of the initial window of size `k`, then slide one step at a time: `sum += nums[right] - nums[right-k]`. Track the maximum sum; divide once at the end.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n) | Single pass; each element added/removed exactly once |
| Space | O(1) | Only a running sum and max variable |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Fixed window, integer sum | Divide to float64 at end |
| Java | Fixed window, integer sum | Cast to double at end |
| Python | Fixed window, integer sum | Python int → float division natively |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

All three are O(n) with O(1) space; Go is fastest due to no boxing or interpreter overhead.

---

## Benchmark Results (Indicative — n = 10⁵, k = 1000)

| Language | Approx Time |
|---|---|
| Go | ~0.1 ms |
| Java | ~0.3 ms |
| Python | ~5 ms |

---

## References

- [LeetCode 643 — Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
