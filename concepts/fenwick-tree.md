# Fenwick Tree (Binary Indexed Tree)

> Category: **Advanced Data Structure**
> Difficulty: **Advanced**
> Also known as: **BIT (Binary Indexed Tree)**

---

# Table of Contents

1. What is a Fenwick Tree?
2. The Core Idea — Responsibility Ranges
3. The `lowbit` Operation
4. Building a Fenwick Tree
5. Point Update
6. Prefix Sum Query
7. Range Sum Query
8. Visualisation
9. Language Implementations
10. Complexity Summary
11. When to Use a Fenwick Tree
12. Fenwick Tree vs Segment Tree
13. Common Mistakes
14. Key Takeaways

---

# What is a Fenwick Tree?

A **Fenwick Tree** (or Binary Indexed Tree) is a data structure that efficiently supports two operations on an array:

1. **Point Update** — update the value at a single index.
2. **Prefix Sum Query** — compute the sum of elements from index `1` to `i`.

Both operations run in **O(log n)** time, which is significantly better than O(n) for a naive array approach.

---

## Motivation

Given an array `A` of `n` numbers:

| Operation | Naive Array | Prefix Sum Array | Fenwick Tree |
|-----------|-------------|------------------|--------------|
| Point update | O(1) | O(n) — must rebuild | O(log n) |
| Prefix sum query | O(n) | O(1) | O(log n) |
| Range sum query | O(n) | O(1) | O(log n) |

A Fenwick Tree is the right tool when **both** updates and prefix queries happen frequently.

---

# The Core Idea — Responsibility Ranges

A Fenwick Tree stores a flat array `BIT[1..n]` where each index `i` is responsible for a **range of elements** in the original array.

The size of that range is determined by the **lowest set bit** (LSB) of `i` in binary:

```
i      binary    lowbit   responsible for
1      0001      1        A[1]
2      0010      2        A[1..2]
3      0011      1        A[3]
4      0100      4        A[1..4]
5      0101      1        A[5]
6      0110      2        A[5..6]
7      0111      1        A[7]
8      1000      8        A[1..8]
```

`BIT[i]` stores the sum of `A[i - lowbit(i) + 1 .. i]`.

---

# The `lowbit` Operation

The lowest set bit of `i` is computed with:

```
lowbit(i) = i & (-i)
```

In two's complement, `-i` flips all bits of `i` and adds 1. The AND with `i` isolates the lowest set bit.

```
i = 6  →  binary: 0110
-i = -6 → binary: 1010  (two's complement)
i & (-i) = 0010  → lowbit = 2
```

---

# Building a Fenwick Tree

## Method 1 — Insert One by One: O(n log n)

Start with an all-zero BIT, then update each index.

```
for i in range(1, n+1):
    update(i, A[i])
```

---

## Method 2 — O(n) Construction

More efficient: each `BIT[i]` directly accumulates from its parent.

```
BIT = [0] * (n + 1)
for i in range(1, n+1):
    BIT[i] += A[i]
    parent = i + lowbit(i)
    if parent <= n:
        BIT[parent] += BIT[i]
```

---

# Point Update

To add `delta` to `A[i]`, update all BIT indices that are responsible for index `i`.

Starting at `i`, repeatedly add `lowbit(i)` to move to the next responsible ancestor:

```
update(i, delta):
    while i <= n:
        BIT[i] += delta
        i += lowbit(i)
```

Example: update index 3, n=8

```
i=3  → BIT[3] += delta   (3 = 011, lowbit=1, next=4)
i=4  → BIT[4] += delta   (4 = 100, lowbit=4, next=8)
i=8  → BIT[8] += delta   (8 = 1000, lowbit=8, next=16 > 8, stop)
```

---

# Prefix Sum Query

To compute `sum(A[1..i])`, walk backward by subtracting `lowbit(i)`:

```
query(i):
    total = 0
    while i > 0:
        total += BIT[i]
        i -= lowbit(i)
    return total
```

Example: query prefix sum up to index 7

```
i=7  → add BIT[7]  (7=0111, lowbit=1, next=6)
i=6  → add BIT[6]  (6=0110, lowbit=2, next=4)
i=4  → add BIT[4]  (4=0100, lowbit=4, next=0, stop)

sum = BIT[7] + BIT[6] + BIT[4]
    = A[7] + (A[5]+A[6]) + (A[1]+A[2]+A[3]+A[4])
    = A[1..7]
```

---

# Range Sum Query

A range sum `sum(A[l..r])` is derived from two prefix queries:

```
range_sum(l, r) = query(r) - query(l - 1)
```

---

# Visualisation

Array: `A = [_, 3, 2, 1, 5, 4, 2, 6, 3]`  (1-indexed)

```
BIT Index  Responsible Range   BIT Value
1          A[1]                3
2          A[1..2]             5   (3+2)
3          A[3]                1
4          A[1..4]             11  (3+2+1+5)
5          A[5]                4
6          A[5..6]             6   (4+2)
7          A[7]                6
8          A[1..8]             26  (sum of all)
```

Query prefix sum up to 6:

```
i=6  → BIT[6] = 6     (covers A[5..6])
i=4  → BIT[4] = 11    (covers A[1..4])

Total = 17 = 3+2+1+5+4+2 ✓
```

---

# Language Implementations

## Java

```java
int[] bit = new int[n + 1];

void update(int i, int delta) {
    for (; i <= n; i += i & (-i))
        bit[i] += delta;
}

int query(int i) {
    int sum = 0;
    for (; i > 0; i -= i & (-i))
        sum += bit[i];
    return sum;
}

int rangeSum(int l, int r) {
    return query(r) - query(l - 1);
}
```

---

## Python

```python
class FenwickTree:
    def __init__(self, n):
        self.n = n
        self.bit = [0] * (n + 1)

    def update(self, i, delta):
        while i <= self.n:
            self.bit[i] += delta
            i += i & (-i)

    def query(self, i):
        total = 0
        while i > 0:
            total += self.bit[i]
            i -= i & (-i)
        return total

    def range_sum(self, l, r):
        return self.query(r) - self.query(l - 1)
```

---

## Go

```go
type FenwickTree struct {
    n   int
    bit []int
}

func NewFenwickTree(n int) *FenwickTree {
    return &FenwickTree{n: n, bit: make([]int, n+1)}
}

func (f *FenwickTree) Update(i, delta int) {
    for ; i <= f.n; i += i & (-i) {
        f.bit[i] += delta
    }
}

func (f *FenwickTree) Query(i int) int {
    sum := 0
    for ; i > 0; i -= i & (-i) {
        sum += f.bit[i]
    }
    return sum
}

func (f *FenwickTree) RangeSum(l, r int) int {
    return f.Query(r) - f.Query(l-1)
}
```

---

# Complexity Summary

| Operation | Time | Space |
|-----------|------|-------|
| Build (insert one by one) | O(n log n) | O(n) |
| Build (O(n) method) | O(n) | O(n) |
| Point update | O(log n) | O(1) |
| Prefix sum query | O(log n) | O(1) |
| Range sum query | O(log n) | O(1) |

---

# When to Use a Fenwick Tree

Use a Fenwick Tree when:

- You need **frequent point updates** AND **frequent prefix or range sum queries**.
- Input size is up to ~10^6 (Fenwick Tree is cache-friendly and fast in practice).
- You need **counting inversions** in an array.
- You need **order statistics** (rank of an element, k-th smallest).
- Problems involve **coordinate compression** with range counting.

---

# Fenwick Tree vs Segment Tree

| Feature | Fenwick Tree | Segment Tree |
|---------|-------------|--------------|
| Code complexity | Simple (10–15 lines) | More complex (~40 lines) |
| Supported queries | Sum, XOR, point queries | Any associative operation |
| Range updates | Requires difference array trick | Built-in (lazy propagation) |
| Memory | O(n) | O(4n) |
| Constant factor | Lower | Higher |

**Rule**: prefer Fenwick Tree for sum/count queries with point updates. Use Segment Tree when you need range updates or non-invertible operations (min, max).

---

# Common Mistakes

## Using 0-Based Indexing

Fenwick Trees are **1-indexed** by design. If your input is 0-indexed, add 1 to every index when calling `update` and `query`.

---

## Forgetting `lowbit(i) = i & (-i)`

The `&` operation is bitwise AND. `i & -i` isolates the lowest set bit. This is the entire structural secret of the Fenwick Tree.

---

## Off-by-One in Range Queries

```
range_sum(l, r) = query(r) - query(l - 1)
```

Not `query(l)`. Subtracting `query(l)` excludes `A[l]`.

---

# Key Takeaways

After studying this concept, you should understand:

- A Fenwick Tree gives O(log n) point updates and prefix sum queries simultaneously.
- The `lowbit(i) = i & (-i)` operation is the structural key.
- Update traverses **upward** (add `lowbit`); query traverses **downward** (subtract `lowbit`).
- Range sum = `query(r) - query(l - 1)`.
- Use Fenwick Tree over Segment Tree when operations are sums/counts and no range updates are needed.
- Must be **1-indexed**.
