# Benchmark

> Problem: **Diameter of Binary Tree**
> Topic: **Binary Tree, DFS, Postorder, Running Maximum**

---

# Algorithm

```text
max_diameter = 0

depth(node):
    if node is null: return 0
    left  = depth(node.left)
    right = depth(node.right)
    max_diameter = max(max_diameter, left + right)
    return 1 + max(left, right)

depth(root)
return max_diameter
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(h) |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

---

# References

- LeetCode Problem 543 — Diameter of Binary Tree

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
