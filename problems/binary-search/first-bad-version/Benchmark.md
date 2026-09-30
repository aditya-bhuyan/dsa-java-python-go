# Benchmark

> Problem: **First Bad Version**
> Topic: **Binary Search — Template 2 on Boolean Space**

---

# Algorithm

```text
left=1, right=n
while left < right:
    mid = left + (right-left)/2
    if isBadVersion(mid): right = mid
    else:                 left  = mid+1
return left
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(log n) |
| API calls | O(log n) ≈ 31 for n=2³¹-1 |
| Space | O(1) |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

---

# References

- LeetCode Problem 278 — First Bad Version

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
