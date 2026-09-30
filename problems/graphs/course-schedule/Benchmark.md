# Benchmark — Course Schedule

## Algorithm Used

**BFS Kahn's Algorithm (Topological Sort)** — build an adjacency list and in-degree array; enqueue all zero-in-degree nodes; process the queue, decrementing neighbors' in-degrees and enqueuing newly zero-in-degree nodes; compare processed count to `numCourses`.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(V + E) | Each node and edge processed once |
| Space | O(V + E) | Adjacency list + in-degree array + queue |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | BFS Kahn's | slice-based adjacency list, queue via slice |
| Java | BFS Kahn's | ArrayList<List<Integer>> adjacency list, ArrayDeque queue |
| Python | BFS Kahn's | list of lists, collections.deque |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

- **Go**: Low-level slice ops, no boxing.
- **Java**: ArrayDeque is fast; slight overhead from generics.
- **Python**: list-of-lists is efficient; deque operations are O(1).

---

## Benchmark Results (Indicative — 2000 courses, 5000 prerequisites)

| Language | Approx Time |
|---|---|
| Go | ~0.3 ms |
| Java | ~1 ms |
| Python | ~4 ms |

---

## References

- [LeetCode 207 — Course Schedule](https://leetcode.com/problems/course-schedule/)
- [LeetCode 210 — Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) (extend to return order)
- [Kahn's Algorithm — Wikipedia](https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
