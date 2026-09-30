# Benchmark

> Problem: **Min Stack**
> Topic: **Stack, Design**

---

# Objective

Compare the performance of Java, Python, and Go implementations of **Min Stack**.

All three maintain two parallel stacks — one for values and one for running minimums — to guarantee O(1) for every operation.

---

# Algorithm

```text
push(val):
    main.push(val)
    minStack.push( min(val, minStack.top()) )   ← or val if minStack is empty

pop():
    main.pop()
    minStack.pop()

top():
    return main.top()

getMin():
    return minStack.top()
```

---

# Complexity Analysis

| Operation | Time | Space |
|-----------|------|-------|
| push | O(1) | O(1) |
| pop | O(1) | O(1) |
| top | O(1) | O(1) |
| getMin | O(1) | O(1) |
| Total space | — | O(n) |

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

All operations are O(1). Performance differences reflect per-operation overhead: function call cost, dynamic typing overhead (Python), and JIT warm-up (Java).

---

# References

- LeetCode Problem 155 — Min Stack
- Effective Go

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
