# Benchmark

> Problem: **Symmetric Tree**
> Topic: **Binary Tree, DFS, Mirror Recursion**

---

# Algorithm

```text
isSymmetric(root):
    return isMirror(root.left, root.right)

isMirror(left, right):
    if left is null and right is null: return true
    if left is null or  right is null: return false
    if left.val != right.val:          return false
    return isMirror(left.left,  right.right)
       and isMirror(left.right, right.left)
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

- LeetCode Problem 101 — Symmetric Tree

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
