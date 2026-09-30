# Benchmark

> Problem: **Middle of the Linked List**
> Topic: **Linked List, Fast and Slow Pointer**

---

# Objective

The purpose of this benchmark is to compare the performance characteristics of the Java, Python, and Go implementations of the **Middle of the Linked List** problem.

The algorithm used in all three languages:

- `slow` pointer moves 1 step at a time.
- `fast` pointer moves 2 steps at a time.
- When `fast` reaches the end, `slow` is at the middle.

---

# Algorithm

```text
slow = head
fast = head

while fast != null and fast.next != null:
    slow = slow.next
    fast = fast.next.next

return slow
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

| Dataset | Number of Nodes |
|---------|----------------:|
| Small | 100 |
| Medium | 10,000 |
| Large | 100,000 |
| Very Large | 1,000,000 |

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
| Node Representation | Class | Class | Struct |
| Pointer Model | Object references | Object references | Explicit pointers |
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

The fast/slow traversal involves minimal computation per step — primarily pointer dereferences. Go's native compilation and lightweight struct pointers give it the best throughput here.

---

# Scalability

| Input Size | Count-and-Traverse (2 passes) | Fast/Slow (1 pass) |
|------------|-------------------------------|---------------------|
| 100 | Fast | Fast |
| 10,000 | Fast | Fast |
| 100,000 | Fast | Fast (fewer passes) |
| 1,000,000 | Fast | Fast (fewer passes) |

Both approaches are O(n). The fast/slow pointer reduces constant-factor overhead by making a single pass.

---

# Conclusion

The optimal algorithm is the fast and slow pointer approach:

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)
- **Passes:** 1 (versus 2 for the count-and-traverse approach)

---

# References

- Introduction to Algorithms (CLRS)
- LeetCode Problem 876 — Middle of the Linked List
- Effective Go
- Python Documentation

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
