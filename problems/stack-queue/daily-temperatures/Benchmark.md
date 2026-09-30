# Benchmark

> Problem: **Daily Temperatures**
> Topic: **Stack, Monotonic Decreasing Stack**

---

# Objective

Compare the performance of Java, Python, and Go implementations of **Daily Temperatures**.

All three use a monotonic decreasing stack that processes each element at most twice (one push, one pop), giving O(n) time.

---

# Algorithm

```text
stack = []      ← indices
answer = [0] * n

for i in range(n):
    while stack not empty and temperatures[i] > temperatures[stack.top()]:
        prev = stack.pop()
        answer[prev] = i - prev
    stack.push(i)

return answer
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(n) |

---

# Benchmark Environment

| Component | Value |
|-----------|-------|
| Operating System | Linux / macOS / Windows |
| Java | JDK 21+ |
| Python | Python 3.12+ |
| Go | Go 1.24+ |

---

# Scalability

| Input Size | Brute Force O(n²) | Monotonic Stack O(n) |
|------------|-------------------|----------------------|
| 100 | Fast | Fast |
| 10,000 | Slow | Fast |
| 100,000 | Very Slow | Fast |
| 1,000,000 | Impractical | Scales well |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

---

# References

- LeetCode Problem 739 — Daily Temperatures
- Effective Go

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
