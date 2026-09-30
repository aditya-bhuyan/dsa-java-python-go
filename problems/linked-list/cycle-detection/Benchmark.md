# Benchmark

> Problem: **Linked List Cycle Detection**
> Topic: **Linked List, Fast and Slow Pointer, Floyd's Algorithm**

---

# Objective

Compare the performance characteristics of the Java, Python, and Go implementations of **Linked List Cycle Detection**.

Two approaches:

1. **Hash Set** — O(n) time, O(n) space.
2. **Floyd's Tortoise and Hare** — O(n) time, O(1) space.

All language implementations use Floyd's algorithm.

---

# Algorithm

```text
slow = head
fast = head

while fast != null and fast.next != null:
    slow = slow.next
    fast = fast.next.next

    if slow == fast:
        return true

return false
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

Where:

- **n** = Number of nodes in the linked list.

---

# Benchmark Environment

| Component | Value |
|-----------|-------|
| Operating System | Linux / macOS / Windows |
| Processor | Modern x86_64 or ARM64 CPU |
| Java | JDK 21+ |
| Python | Python 3.12+ |
| Go | Go 1.24+ |

---

# Test Dataset

| Dataset | Number of Nodes | Cycle Position |
|---------|----------------:|----------------|
| No-cycle, Small | 100 | None |
| No-cycle, Large | 1,000,000 | None |
| Cycle at tail | 100,000 | Last node → head |
| Cycle in middle | 100,000 | Last node → node 50,000 |
| Self-loop | 1 | Node → itself |

---

# Expected Performance

| Language | Time Complexity | Space Complexity |
|----------|-----------------|------------------|
| Java | O(n) | O(1) |
| Python | O(n) | O(1) |
| Go | O(n) | O(1) |

---

# Language Comparison

| Feature | Java | Python | Go |
|---------|------|--------|----|
| Reference Equality | `==` on objects | `is` keyword | `==` on pointers |
| Null Equivalent | null | None | nil |
| Compilation | JIT | Interpreted | Native |
| Runtime Speed | High | Moderate | Very High |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

The bottleneck here is pointer dereferences, which Go handles most efficiently with native structs.

---

# Scalability

| Input Size | Hash Set | Floyd's Algorithm |
|------------|----------|--------------------|
| 100 | Fast | Fast |
| 10,000 | Fast | Fast |
| 100,000 | Slower (memory allocation) | Fast |
| 1,000,000 | Memory pressure | Scales well |

Floyd's algorithm has a significant practical advantage for large lists because it allocates no additional memory.

---

# Conclusion

The optimal algorithm for cycle detection is **Floyd's Tortoise and Hare**:

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **No auxiliary data structures required.**

---

# References

- Introduction to Algorithms (CLRS)
- LeetCode Problem 141 — Linked List Cycle
- Floyd, R. W. (1967). Nondeterministic Algorithms.
- Effective Go

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
