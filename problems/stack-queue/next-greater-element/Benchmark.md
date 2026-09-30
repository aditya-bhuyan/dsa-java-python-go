# Benchmark

> Problem: **Next Greater Element**
> Topic: **Stack, Monotonic Decreasing Stack, Hash Map**

---

# Objective

Compare the performance of Java, Python, and Go implementations of **Next Greater Element**.

All three precompute next-greater values for all of `nums2` using a monotonic stack in O(n), then answer each `nums1` query in O(1) via a hash map.

---

# Algorithm

```text
stack     = []
nextGreater = {}

for num in nums2:
    while stack not empty and num > stack.top():
        nextGreater[ stack.pop() ] = num
    stack.push(num)

for num in stack:
    nextGreater[num] = -1

return [ nextGreater[x] for x in nums1 ]
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(m + n) |
| Space | O(n) |

Where m = len(nums1), n = len(nums2).

---

# Benchmark Environment

| Component | Value |
|-----------|-------|
| Operating System | Linux / macOS / Windows |
| Java | JDK 21+ |
| Python | Python 3.12+ |
| Go | Go 1.24+ |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

---

# References

- LeetCode Problem 496 — Next Greater Element I
- Effective Go

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
