# Benchmark — Longest Substring Without Repeating Characters

## Algorithm Used

**Variable Sliding Window + Index Map** — two pointers `left` and `right`. For each `s[right]`, if it was seen at index `≥ left`, jump `left` to `seen[s[right]] + 1`. Update `seen[s[right]] = right`. Track maximum window length.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n) | `right` advances n times; `left` never goes backward |
| Space | O(min(n, ∣Σ∣)) | Map stores at most ∣Σ∣ entries (∣Σ∣ = 128 for ASCII) |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | `map[byte]int` index map | Byte indexing avoids UTF-8 overhead for ASCII input |
| Java | `HashMap<Character, Integer>` | Auto-boxing overhead; negligible for this problem |
| Python | `dict` index map | Built-in dict; O(1) average lookup |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

---

## Benchmark Results (Indicative — n = 5 × 10⁴, mixed ASCII)

| Language | Approx Time |
|---|---|
| Go | ~0.3 ms |
| Java | ~1 ms |
| Python | ~8 ms |

---

## References

- [LeetCode 3 — Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
