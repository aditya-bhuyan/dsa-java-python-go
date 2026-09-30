# Benchmark

> Problem: **Valid Parentheses**
> Topic: **Stack, String Matching**

---

# Objective

Compare the performance of the Java, Python, and Go implementations of the **Valid Parentheses** problem.

All three use the same algorithm: a single-pass stack traversal with O(n) time and O(n) space.

---

# Algorithm

```text
stack = []

for each char in s:

    if char is open bracket:
        push char

    else (close bracket):
        if stack is empty:
            return false

        top = stack.pop()

        if top does not match char:
            return false

return stack is empty
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

Where **n** = length of the input string.

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

| Dataset | String Length |
|---------|-------------:|
| Small | 100 |
| Medium | 10,000 |
| Large | 100,000 |
| Very Large | 1,000,000 |

Each dataset contains an equal mix of valid and invalid strings.

---

# Language Comparison

| Feature | Java | Python | Go |
|---------|------|--------|----|
| Stack Type | Deque\<Character\> | list | []rune |
| Map Type | HashMap | dict | map[rune]rune |
| Compilation | JIT | Interpreted | Native |
| Runtime Speed | High | Moderate | Very High |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

The workload here is character-level iteration with hash map lookups — Go's native compilation gives it the lowest overhead per character.

---

# Scalability

| Input Size | Brute Force (replace pairs) | Stack |
|------------|------------------------------|-------|
| 100 | Fast | Fast |
| 10,000 | Very Slow (O(n²)) | Fast |
| 1,000,000 | Impractical | Scales well |

---

# Conclusion

The optimal algorithm is a single-pass stack traversal:

- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

---

# References

- Introduction to Algorithms (CLRS)
- LeetCode Problem 20 — Valid Parentheses
- Effective Go

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
