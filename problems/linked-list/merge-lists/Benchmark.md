# Benchmark

> Problem: **Merge Two Sorted Lists**
> Topic: **Linked List, Two Pointers**

---

# Objective

Compare the performance characteristics of the Java, Python, and Go implementations of **Merge Two Sorted Lists**.

All three implementations use the iterative two-pointer merge with a dummy head node.

---

# Algorithm

```text
dummy   = new Node(0)
current = dummy

while list1 != null and list2 != null:

    if list1.val <= list2.val:
        current.next = list1
        list1 = list1.next
    else:
        current.next = list2
        list2 = list2.next

    current = current.next

current.next = (list1 != null) ? list1 : list2

return dummy.next
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time Complexity | O(m + n) |
| Space Complexity | O(1) |

Where:

- **m** = length of list1
- **n** = length of list2

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

| Dataset | Nodes per List |
|---------|---------------:|
| Small | 50 |
| Medium | 5,000 |
| Large | 50,000 |
| Very Large | 500,000 |

Each dataset contains two sorted lists of equal length.

---

# Expected Performance

| Language | Time Complexity | Space Complexity |
|----------|-----------------|------------------|
| Java | O(m + n) | O(1) |
| Python | O(m + n) | O(1) |
| Go | O(m + n) | O(1) |

---

# Language Comparison

| Feature | Java | Python | Go |
|---------|------|--------|----|
| Node Representation | Class | Class | Struct |
| Null Equivalent | null | None | nil |
| Compilation | JIT | Interpreted | Native |
| Dummy Node | new ListNode(0) | ListNode(0) | &ListNode{} |
| Runtime Speed | High | Moderate | Very High |

---

# Expected Relative Performance

| Rank | Language |
|------|----------|
| 1 | Go |
| 2 | Java |
| 3 | Python |

---

# Scalability

| Input Size (per list) | Brute Force (sort) | Two-Pointer Merge |
|-----------------------|---------------------|---------------------|
| 50 | Fast | Fast |
| 5,000 | Moderate | Fast |
| 50,000 | Slower | Fast |
| 500,000 | O(n log n) overhead | O(n) — linear |

The two-pointer merge is optimal for pre-sorted inputs. Sorting throws away the ordering information already present in the inputs.

---

# Conclusion

The optimal algorithm for merging two sorted linked lists is the iterative two-pointer merge:

- **Time Complexity:** O(m + n)
- **Space Complexity:** O(1)
- **No new nodes allocated** — existing nodes are rewired.

---

# References

- Introduction to Algorithms (CLRS) — Chapter on merge sort
- LeetCode Problem 21 — Merge Two Sorted Lists
- Effective Go
- Python Documentation

---

# Revision History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | 2026-07-29 | Aditya Bhuyan | Initial benchmark document |
