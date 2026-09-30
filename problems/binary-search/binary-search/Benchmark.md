# Benchmark

> Problem: **Binary Search**
> Topic: **Binary Search — Template 1 Exact Match**

---

# Algorithm

```text
left=0, right=n-1
while left <= right:
    mid = left + (right-left)/2
    if nums[mid] == target: return mid
    elif nums[mid] < target: left  = mid+1
    else:                    right = mid-1
return -1
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(log n) |
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

- LeetCode Problem 704 — Binary Search

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
