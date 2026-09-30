# Benchmark — Flood Fill

## Algorithm Used

**DFS Flood Fill** — from the seed pixel, recursively paint all 4-directionally connected pixels that share the original color. Early exit when new color equals original color.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(m × n) | Each pixel visited at most once |
| Space | O(m × n) | Recursion stack worst-case (entire grid same color) |
| Space (BFS) | O(min(m, n)) | Queue holds at most the perimeter |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | DFS recursive | Clean and concise; small grid (50×50 max) so no stack concern |
| Java | DFS recursive | Same; 50×50 max depth = 2500, well within JVM stack |
| Python | BFS iterative | Preferred to avoid recursion limit even on small grids |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

For a 50×50 grid the absolute times are very small (microseconds); the ordering holds but the gap is less pronounced than for larger inputs.

---

## Benchmark Results (Indicative — 50×50 all-same-color grid)

| Language | Approx Time |
|---|---|
| Go | ~0.01 ms |
| Java | ~0.04 ms |
| Python | ~0.2 ms |

---

## References

- [LeetCode 733 — Flood Fill](https://leetcode.com/problems/flood-fill/)
- [Wikipedia — Flood Fill](https://en.wikipedia.org/wiki/Flood_fill)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
