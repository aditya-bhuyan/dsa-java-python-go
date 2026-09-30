# Benchmark — Assign Cookies

## Algorithm Used

**Greedy — Sort + Two Pointers** — sort both `g` (greed factors) and `s` (cookie sizes) ascending; use two pointers to greedily assign the smallest sufficient cookie to the least greedy unsatisfied child.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n log n + m log m) | Dominated by sorting both arrays |
| Space | O(1) or O(log n) | In-place sort (O(log n) stack in most languages) |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | `sort.Ints` + two pointers | In-place sort, single scan |
| Java | `Arrays.sort` + two pointers | Primitive array sort (dual-pivot quicksort) |
| Python | `list.sort()` + two pointers | TimSort (stable), O(n log n) |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

Sorting dominates; the two-pointer scan is O(n + m). Java's `Arrays.sort` on primitives is highly optimised.

---

## Benchmark Results (Indicative — n = m = 3 × 10⁴)

| Language | Approx Time |
|---|---|
| Go | ~1 ms |
| Java | ~2 ms |
| Python | ~10 ms |

---

## References

- [LeetCode 455 — Assign Cookies](https://leetcode.com/problems/assign-cookies/)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
