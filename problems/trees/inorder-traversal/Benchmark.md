# Benchmark

> Problem: **Binary Tree Inorder Traversal**
> Topic: **Binary Tree, DFS, Inorder, Iteration**

---

# Algorithm

**Recursive**

```text
inorder(node, result):
    if node is null: return
    inorder(node.left, result)
    result.append(node.val)
    inorder(node.right, result)
```

**Iterative**

```text
stack = [], current = root
while current or stack:
    while current: push current; current = current.left
    current = stack.pop(); visit; current = current.right
```

---

# Complexity Analysis

| Approach | Time | Space |
|----------|------|-------|
| Recursive | O(n) | O(h) |
| Iterative | O(n) | O(h) |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

---

# References

- LeetCode Problem 94 — Binary Tree Inorder Traversal

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
