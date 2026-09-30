# Benchmark

> Problem: **Same Tree**
> Topic: **Binary Tree, DFS, Pairwise Recursion**

---

# Algorithm

```text
isSameTree(p, q):
    if p is null and q is null: return true
    if p is null or  q is null: return false
    if p.val != q.val:          return false
    return isSameTree(p.left, q.left) and isSameTree(p.right, q.right)
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

- LeetCode Problem 100 — Same Tree

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
