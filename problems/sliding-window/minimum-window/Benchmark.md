# Benchmark — Minimum Window Substring

## Algorithm Used

**Variable Sliding Window + Match Counter** — build a `need` frequency map from `t`; expand `right` adding characters; when all characters are satisfied (`matches == required`), shrink `left` while still valid, recording the minimum window at each valid state.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(m + n) | m = len(s), n = len(t); each char added/removed once |
| Space | O(∣Σ∣) | Two frequency maps; ∣Σ∣ = 52 for upper+lower English |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Sliding window + `[128]int` arrays | Fixed-size arrays instead of maps for O(1) hash |
| Java | Sliding window + `int[128]` arrays | Same; array indexing by char value |
| Python | Sliding window + `Counter` / `dict` | `collections.Counter` for `need`; plain `dict` for `have` |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

The match counter reduces inner-loop work to O(1); Go's array access is fastest.

---

## Benchmark Results (Indicative — m = 10⁵, n = 50)

| Language | Approx Time |
|---|---|
| Go | ~0.8 ms |
| Java | ~2 ms |
| Python | ~18 ms |

---

## References

- [LeetCode 76 — Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
