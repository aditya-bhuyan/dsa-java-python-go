# Benchmark

> Problem: **Reverse Linked List**
> Topic: **Linked List, Iterative Reversal**

---

# Objective

The purpose of this benchmark is to compare the performance characteristics of the Java, Python, and Go implementations of the **Reverse Linked List** problem.

The algorithm is identical in all three languages:

- Traverse the list once with three pointers (`prev`, `current`, `next`).
- Rewire each `next` pointer to point backward.
- Return the new head.

Since the algorithm remains the same, all implementations share the same theoretical time and space complexity. Differences arise from language runtime, memory management, and object allocation overhead.

---

# Algorithm

```text
prev    = null
current = head

while current != null:
    next         = current.next
    current.next = prev
    prev         = current
    current      = next

return prev
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
| Node Representation | Class with fields | Class with attributes | Struct |
| Memory Management | Garbage Collector | Garbage Collector | Garbage Collector |
| Compilation | JIT | Interpreted (Bytecode) | Native |
| Pointer Model | Object references | Object references | Explicit pointers |
| Startup Time | Medium | Fast | Very Fast |
| Runtime Speed | High | Moderate | Very High |
| Memory Usage | Moderate | Higher | Low |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

Why?

- **Go** compiles to native code and uses lightweight struct pointers.
- **Java** benefits from JVM JIT optimization; object references are compact.
- **Python** incurs higher overhead from dynamic typing and object metadata.

All three scale linearly because the algorithm makes exactly one pass through the list.

---

# Memory Consumption

| Language | Relative Memory Usage |
|-----------|-----------------------|
| Go | Lowest |
| Java | Medium |
| Python | Highest |

This benchmark uses O(1) extra space (only three pointer variables). Memory differences here reflect per-node object overhead rather than algorithmic space usage.

---

# Scalability

| Input Size | Brute Force (copy array) | Iterative (three pointers) |
|------------|--------------------------|----------------------------|
| 100 | Fast | Fast |
| 10,000 | Moderate | Fast |
| 100,000 | Slow (memory) | Fast |
| 1,000,000 | High memory use | Scales well |

The three-pointer iterative approach significantly outperforms the copy-and-rebuild approach at large scale due to zero auxiliary memory allocation.

---

# Practical Considerations

## Java

### Advantages

- Object references behave predictably under GC.
- Strong type safety prevents null-pointer oversights at compile time.

### Considerations

- JVM warm-up cost for very short runs.

---

## Python

### Advantages

- Concise and readable code.
- Ideal for prototyping and learning.

### Considerations

- Higher per-object overhead; each `ListNode` carries Python dict and type metadata.

---

## Go

### Advantages

- Structs and pointers map directly to memory — minimal overhead per node.
- Fast compilation and native execution.

### Considerations

- More explicit null (`nil`) checks required compared to Python.

---

# Conclusion

The optimal algorithm for Reverse Linked List is the iterative three-pointer approach:

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

All three languages implement the same algorithm, but Go offers the fastest execution and lowest memory footprint. Java is a strong second choice. Python prioritizes readability.

---

# References

- Introduction to Algorithms (CLRS)
- Effective Java (Joshua Bloch)
- Effective Go
- Python Documentation
- LeetCode Problem 206 — Reverse Linked List

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
