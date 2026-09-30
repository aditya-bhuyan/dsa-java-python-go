# Benchmark

> Problem: **Search Insert Position**
> Topic: **Binary Search — Template 2 Left Boundary**

---

# Algorithm

```text
left=0, right=n
while left < right:
    mid = left + (right-left)/2
    if nums[mid] >= target: right = mid
    else:                   left  = mid+1
return left
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

- LeetCode Problem 35 — Search Insert Position

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
