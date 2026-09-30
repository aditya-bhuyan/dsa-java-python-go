# Benchmark

> Problem: **Maximum Depth of Binary Tree**
> Topic: **Binary Tree, DFS, Recursion**

---

# Algorithm

```text
maxDepth(node):
    if node is null: return 0
    return 1 + max(maxDepth(node.left), maxDepth(node.right))
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(h) — h = tree height |

---

# Expected Relative Performance

| Rank | Language | Notes |
|------|----------|-------|
| 1 | Go | Native compilation, lightweight struct pointers |
| 2 | Java | JIT-optimised virtual calls |
| 3 | Python | Interpreter + dynamic dispatch overhead |

---

# Benchmark Environment

| Component | Value |
|-----------|-------|
| Java | JDK 21+ |
| Python | Python 3.12+ |
| Go | Go 1.24+ |

---

# References

- LeetCode Problem 104 — Maximum Depth of Binary Tree

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
