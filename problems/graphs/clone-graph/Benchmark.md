# Benchmark — Clone Graph

## Algorithm Used

**DFS + HashMap** — recursive depth-first traversal with a `visited` map (`original → clone`). On each visit, the clone is stored immediately before processing neighbors to break cycles.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(V + E) | Every node and edge traversed once |
| Space | O(V) | HashMap stores one entry per node; recursion stack depth ≤ V |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | DFS + map[*Node]*Node | Struct-based Node with int Val and []*Node Neighbors |
| Java | DFS + HashMap | Inner static Node class; HashMap<Node,Node> visited |
| Python | BFS + dict | Iterative BFS avoids recursion limit on large graphs |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

- **Go**: Direct pointer map, minimal allocation overhead.
- **Java**: HashMap with object keys; JIT compensates for boxing overhead.
- **Python**: dict lookup is O(1) average but interpreter overhead dominates.

---

## Benchmark Results (Indicative — 100-node complete graph K₁₀₀)

| Language | Approx Time |
|---|---|
| Go | ~0.05 ms |
| Java | ~0.2 ms |
| Python | ~0.8 ms |

*Results are indicative; actual numbers depend on hardware and JVM warm-up.*

---

## References

- [LeetCode 133 — Clone Graph](https://leetcode.com/problems/clone-graph/)
- [LeetCode 138 — Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/) (same pattern)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
