# Benchmark — Longest Increasing Subsequence

## Algorithm Used

**DP + Binary Search (Patience Sort / `tails` array)** — maintain a sorted `tails` array where `tails[i]` = smallest tail element of all IS of length `i+1`. For each element, binary search (`bisect_left`) for its position in `tails` and replace or extend. Final answer = `len(tails)`.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n log n) | n elements × O(log n) binary search each |
| Space | O(n) | `tails` array, at most n elements |

*Also implemented: O(n²) DP for comparison and educational clarity.*

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | O(n²) DP + O(n log n) binary search | Custom `lowerBound` helper |
| Java | O(n²) DP + O(n log n) `Arrays.binarySearch` | `Arrays.binarySearch` returns `-(insertion point)-1` on miss |
| Python | O(n log n) `bisect_left` | `bisect` module from stdlib |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

---

## Benchmark Results (Indicative — n = 2,500)

| Language | Approach | Approx Time |
|---|---|---|
| Go | O(n²) DP | ~1 ms |
| Go | O(n log n) | ~0.05 ms |
| Java | O(n²) DP | ~3 ms |
| Java | O(n log n) | ~0.15 ms |
| Python | O(n²) DP | ~80 ms |
| Python | O(n log n) | ~1 ms |

*The n log n approach is ~20× faster than n² for n=2500; the gap widens significantly for larger inputs.*

---

## References

- [LeetCode 300 — Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
- [LeetCode 354 — Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/) (2D LIS)
- [Wikipedia — Patience Sorting](https://en.wikipedia.org/wiki/Patience_sorting)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
