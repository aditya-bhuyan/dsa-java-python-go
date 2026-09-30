# Benchmark — Permutation in String

## Algorithm Used

**Fixed Sliding Window + Match Counter** — build `need` from `s1`; slide a window of exactly `len(s1)` over `s2`; maintain `have` incrementally; track `matches` (count of chars with satisfied frequency); return `true` when `matches == required`.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(m + n) | m = len(s2), n = len(s1); single pass with O(1) per step |
| Space | O(26) = O(1) | Only lowercase letters; fixed-size frequency arrays |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | `[26]int` arrays | Index by `c - 'a'`; fastest access |
| Java | `int[26]` arrays | Same; no boxing overhead |
| Python | `list[int]` size 26 | Index by `ord(c) - ord('a')` |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

Because the alphabet is fixed at 26, space is O(1) and all operations are cache-friendly array accesses.

---

## Benchmark Results (Indicative — m = 10⁴, n = 100)

| Language | Approx Time |
|---|---|
| Go | ~0.05 ms |
| Java | ~0.15 ms |
| Python | ~1 ms |

---

## References

- [LeetCode 567 — Permutation in String](https://leetcode.com/problems/permutation-in-string/)
- [LeetCode 438 — Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) (direct follow-up)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
