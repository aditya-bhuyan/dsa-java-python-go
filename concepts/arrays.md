# Arrays

> "Arrays are the foundation of almost every Data Structure and Algorithm."

---

# Table of Contents

1. Introduction
2. What is an Array?
3. Characteristics
4. Memory Representation
5. Types of Arrays
6. Common Operations
7. Time Complexity
8. Advantages
9. Disadvantages
10. Arrays in Java
11. Arrays in Python
12. Arrays in Go
13. Common Interview Questions
14. Best Practices
15. Summary

---

# Introduction

Arrays are one of the most fundamental and widely used data structures in computer science. Almost every algorithm begins with arrays or converts data into an array-like representation.

Understanding arrays is essential because many advanced data structures—including stacks, queues, heaps, hash tables, dynamic arrays, and matrices—are built upon array concepts.

Examples of real-world uses include:

- Student records
- Monthly sales
- Temperature readings
- Pixel values in images
- Audio samples
- Game boards
- Sensor data
- Machine learning datasets

---

# What is an Array?

An array is a collection of elements stored in **contiguous memory locations**, where each element is identified by its index.

Every element has the same data type.

Example:

```
Index

0   1   2   3   4

+---+---+---+---+---+
|10 |20 |30 |40 |50 |
+---+---+---+---+---+
```

Here,

```
arr[0] = 10
arr[1] = 20
arr[2] = 30
arr[3] = 40
arr[4] = 50
```

---

# Characteristics

Arrays have the following characteristics:

- Fixed number of elements (in most languages)
- Stored in contiguous memory
- Constant-time random access
- Homogeneous elements
- Index-based access
- Cache-friendly

---

# Memory Representation

Suppose each integer occupies **4 bytes**.

```
Address

1000
1004
1008
1012
1016
```

Array

```
Index     Address

0         1000

1         1004

2         1008

3         1012

4         1016
```

Address calculation:

```
Address = Base + (Index × SizeOfElement)
```

Example

```
Base = 1000

Index = 3

Size = 4

Address = 1000 + (3 × 4)

Address = 1012
```

This calculation explains why accessing an array element is **O(1)**.

---

# Types of Arrays

## One-Dimensional Array

```
10 20 30 40 50
```

---

## Two-Dimensional Array

```
1 2 3

4 5 6

7 8 9
```

Used for:

- Matrices
- Images
- Chess boards
- Dynamic Programming tables

---

## Multi-Dimensional Array

Example:

```
3D graphics

Scientific simulations

Tensor operations
```

---

## Dynamic Arrays

Unlike fixed arrays, dynamic arrays automatically resize.

Examples:

Java

```
ArrayList
```

Python

```
list
```

Go

```
slice
```

---

# Common Operations

## Access

```
arr[3]
```

Time Complexity

```
O(1)
```

---

## Update

```
arr[2]=100
```

Time Complexity

```
O(1)
```

---

## Traversal

```
for each element
```

Time Complexity

```
O(n)
```

---

## Search

Linear Search

```
O(n)
```

Binary Search

```
O(log n)
```

(Binary Search requires sorted array.)

---

## Insert

Insert at beginning

```
O(n)
```

Insert at middle

```
O(n)
```

Insert at end

```
O(1) amortized for dynamic arrays
```

---

## Delete

Delete first element

```
O(n)
```

Delete middle

```
O(n)
```

Delete last

```
O(1)
```

---

# Time Complexity Summary

| Operation | Complexity |
|------------|------------|
| Access | O(1) |
| Update | O(1) |
| Search | O(n) |
| Binary Search | O(log n) |
| Insert Beginning | O(n) |
| Insert Middle | O(n) |
| Insert End | O(1)* |
| Delete | O(n) |
| Traversal | O(n) |

\* Amortized for dynamic arrays.

---

# Advantages

- Very fast random access
- Simple implementation
- Cache friendly
- Low memory overhead
- Excellent for iteration

---

# Disadvantages

- Fixed size (traditional arrays)
- Expensive insertion
- Expensive deletion
- Possible memory wastage
- Requires contiguous memory

---

# Arrays in Java

```java
int[] numbers = {10,20,30,40,50};

System.out.println(numbers[2]);
```

Output

```
30
```

---

# Arrays in Python

```python
numbers = [10,20,30,40,50]

print(numbers[2])
```

Output

```
30
```

Python lists are dynamic arrays.

---

# Arrays in Go

```go
numbers := []int{10,20,30,40,50}

fmt.Println(numbers[2])
```

Output

```
30
```

Go slices are dynamic views over arrays.

---

# Common Interview Questions

Some of the most frequently asked array problems include:

- Two Sum
- Best Time to Buy and Sell Stock
- Contains Duplicate
- Move Zeroes
- Rotate Array
- Product of Array Except Self
- Maximum Subarray
- Merge Sorted Arrays
- Remove Duplicates
- Majority Element

Mastering these problems introduces important techniques such as:

- Hash Maps
- Two Pointers
- Sliding Window
- Prefix/Suffix Arrays
- Kadane’s Algorithm
- Binary Search

---

# Best Practices

- Validate array indices before access.
- Avoid unnecessary copying of arrays.
- Use enhanced for-loops when modification is not required.
- Prefer slices/lists for dynamic collections.
- Choose the right algorithm before optimizing code.
- Consider memory usage for large datasets.

---

# Key Takeaways

- Arrays are the foundation of most data structures.
- Elements are stored in contiguous memory.
- Random access is O(1).
- Insertions and deletions are generally O(n).
- Arrays are cache-friendly and highly efficient for sequential access.
- Understanding arrays is essential before learning advanced data structures.

---

# What's Next?

Now that you understand arrays, the next step is to solve classic array problems.

We begin with the most popular interview question:

➡ **Two Sum**

This problem introduces the concept of using a **Hash Map** to optimize lookup operations and reduce time complexity from **O(n²)** to **O(n)**.