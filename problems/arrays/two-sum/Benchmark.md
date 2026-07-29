# Benchmark

> Problem: **Two Sum**  
> Topic: **Arrays, Hash Map**

---

# Objective

The purpose of this benchmark is to compare the performance characteristics of the Java, Python, and Go implementations of the **Two Sum** problem.

The algorithm is identical in all three languages:

- Traverse the array once.
- Use a Hash Map (Dictionary / Map) to store previously visited elements.
- Look up the complement in constant time.
- Return the indices when a match is found.

Since the algorithm remains the same, all implementations have the same theoretical time and space complexity. The differences arise from language runtime, memory management, and standard library implementations.

---

# Algorithm

```text
Create an empty Hash Map.

For each element in the array:

    complement = target - currentNumber

    if complement exists in Hash Map

        return indices

    else

        store currentNumber and its index

Return error if no solution exists.
```

---

# Complexity Analysis

| Metric | Complexity |
|----------|------------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

Where:

- **n** = Number of elements in the input array.

---

# Benchmark Environment

The following environment is recommended for reproducible benchmarking.

| Component | Value |
|-----------|-------|
| Operating System | Linux / macOS / Windows |
| Processor | Modern x86_64 or ARM64 CPU |
| Java | JDK 21+ |
| Python | Python 3.12+ |
| Go | Go 1.24+ |

---

# Test Dataset

| Dataset | Number of Elements |
|----------|-------------------:|
| Small | 100 |
| Medium | 10,000 |
| Large | 100,000 |
| Very Large | 1,000,000 |

Each dataset contains exactly one valid solution.

---

# Expected Performance

| Language | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| Java | O(n) | O(n) |
| Python | O(n) | O(n) |
| Go | O(n) | O(n) |

---

# Language Comparison

| Feature | Java | Python | Go |
|---------|------|---------|----|
| Hash Table | HashMap | dict | map |
| Memory Management | Garbage Collector | Garbage Collector | Garbage Collector |
| Compilation | JIT | Interpreted (Bytecode) | Native |
| Startup Time | Medium | Fast | Very Fast |
| Runtime Speed | High | Moderate | Very High |
| Memory Usage | Moderate | Higher | Low |
| Readability | High | Excellent | High |

---

# Expected Relative Performance

The exact numbers depend on hardware and software versions, but the expected order is:

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

Why?

- **Go** compiles directly to native machine code and has a lightweight runtime.
- **Java** benefits from the JVM's Just-In-Time (JIT) compiler, which can optimize long-running code.
- **Python** has higher interpreter overhead and dynamically typed objects, making it slower for CPU-bound tasks.

Despite these runtime differences, all three implementations scale linearly because they use the same O(n) algorithm.

---

# Memory Consumption

Approximate characteristics:

| Language | Relative Memory Usage |
|-----------|----------------------|
| Go | Lowest |
| Java | Medium |
| Python | Highest |

Reasons:

- Python objects carry additional metadata and dynamic typing information.
- Java uses object wrappers and JVM-managed memory.
- Go uses compact native data structures.

---

# Scalability

| Input Size | Brute Force | Hash Map |
|------------|-------------|-----------|
| 100 | Fast | Fast |
| 10,000 | Slow | Fast |
| 100,000 | Very Slow | Fast |
| 1,000,000 | Impractical | Scales Well |

The Hash Map approach significantly outperforms the Brute Force solution as the input size increases.

---

# Practical Considerations

## Java

### Advantages

- Excellent JVM optimizations.
- Mature standard library.
- Strong type safety.
- Widely used in enterprise applications.

### Considerations

- Slightly higher startup time due to JVM initialization.

---

## Python

### Advantages

- Concise and highly readable code.
- Ideal for learning algorithms.
- Rapid development.

### Considerations

- Higher interpreter overhead.
- Less suitable for CPU-intensive workloads.

---

## Go

### Advantages

- Native compilation.
- Fast execution.
- Efficient memory usage.
- Excellent for cloud-native and systems programming.

### Considerations

- Smaller standard library compared to Java.
- More explicit syntax than Python.

---

# Conclusion

The optimal algorithm for the Two Sum problem is the Hash Map approach with:

- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

While Java, Python, and Go all implement the same algorithm, they differ in runtime characteristics:

- **Go** offers the fastest execution and lowest memory footprint.
- **Java** provides excellent performance with JVM optimizations and strong ecosystem support.
- **Python** prioritizes readability and developer productivity, making it ideal for learning and prototyping.

Choose the language based on your use case rather than micro-benchmark differences, as the algorithm itself has the greatest impact on performance.

---

# References

- Introduction to Algorithms (CLRS)
- Effective Java (Joshua Bloch)
- Effective Go
- Python Documentation
- Java Documentation
- Go Documentation

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |