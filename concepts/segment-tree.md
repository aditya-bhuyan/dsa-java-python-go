# Segment Tree

## Table of Contents

1. [Introduction](#introduction)
2. [Structure & Representation](#structure--representation)
3. [Build](#build)
4. [Query (Range Query)](#query-range-query)
5. [Update (Point Update)](#update-point-update)
6. [Lazy Propagation (Range Update)](#lazy-propagation-range-update)
7. [Comparison with Fenwick Tree](#comparison-with-fenwick-tree)
8. [Complexity Analysis](#complexity-analysis)
9. [Language Implementations](#language-implementations)
10. [Common Mistakes](#common-mistakes)
11. [Problems Covered](#problems-covered)

---

## Introduction

A **Segment Tree** is a binary tree built over an array that enables efficient **range queries** and **point updates** in O(log n) time. It is more general than a Fenwick (Binary Indexed) Tree and can handle:
- Range sum, range min, range max, range GCD, range XOR
- Point updates and (with lazy propagation) range updates

---

## Structure & Representation

For an array of `n` elements, the segment tree has at most `4n` nodes.

### Tree Structure

```
Array: [1, 3, 5, 7, 9, 11]   (0-indexed)

Segment tree (range sum):
                   [0..5] = 36
                  /              \
         [0..2] = 9          [3..5] = 27
         /      \            /         \
    [0..1]=4   [2]=5    [3..4]=16    [5]=11
    /    \               /    \
  [0]=1  [1]=3        [3]=7   [4]=9
```

### Array-Based (1-indexed, root at index 1)

```
node i → left child: 2*i,  right child: 2*i+1,  parent: i//2

tree[1] = entire range
tree[2] = left half,  tree[3] = right half
...
```

Using 1-based indexing with size `4*n`:

```
tree = [0] * (4 * n)
```

---

## Build

```
def build(tree, arr, node, start, end):
    if start == end:
        tree[node] = arr[start]
        return
    mid = (start + end) // 2
    build(tree, arr, 2*node,   start, mid)
    build(tree, arr, 2*node+1, mid+1, end)
    tree[node] = tree[2*node] + tree[2*node+1]   # combine: sum, min, max, etc.
```

**Time:** O(n) — visits each node once.

---

## Query (Range Query)

Find the sum (or min/max) of `arr[l..r]`:

```
def query(tree, node, start, end, l, r):
    if r < start or end < l:
        return 0                           # out of range — identity for sum
    if l <= start and end <= r:
        return tree[node]                  # completely within range
    mid = (start + end) // 2
    left  = query(tree, 2*node,   start, mid, l, r)
    right = query(tree, 2*node+1, mid+1, end, l, r)
    return left + right                    # combine results
```

### Dry Run

```
Array: [1, 3, 5, 7, 9, 11],  query sum [1..4]

query(node=1, [0..5], l=1, r=4):
  not fully inside, split
  query(node=2, [0..2], l=1, r=4):
    not fully inside, split
    query(node=4, [0..1], l=1, r=4):
      not fully inside, split
      query(node=8, [0..0], l=1, r=4): 0 < 1, OOB → return 0
      query(node=9, [1..1], l=1, r=4): fully inside → return 3
      return 3
    query(node=5, [2..2], l=1, r=4): fully inside → return 5
    return 3+5=8
  query(node=3, [3..5], l=1, r=4):
    not fully inside, split
    query(node=6, [3..4], l=1, r=4): fully inside → return 16
    query(node=7, [5..5], l=1, r=4): 5 > 4, OOB → return 0
    return 16
  return 8+16=24 ✓  (3+5+7+9=24)
```

---

## Update (Point Update)

Update `arr[idx]` to `val`:

```
def update(tree, node, start, end, idx, val):
    if start == end:
        tree[node] = val
        return
    mid = (start + end) // 2
    if idx <= mid:
        update(tree, 2*node,   start, mid, idx, val)
    else:
        update(tree, 2*node+1, mid+1, end, idx, val)
    tree[node] = tree[2*node] + tree[2*node+1]   # recompute internal node
```

**Time:** O(log n) — updates one path from leaf to root.

---

## Lazy Propagation (Range Update)

For **range updates** (e.g., add `delta` to all elements in `arr[l..r]`), naively updating each element is O(n). Lazy propagation defers updates until needed.

### Lazy Array

```
lazy[node] = pending update to push down to children
```

### Update with Lazy

```
def range_update(tree, lazy, node, start, end, l, r, delta):
    if lazy[node] != 0:
        tree[node] += (end - start + 1) * lazy[node]
        if start != end:
            lazy[2*node]   += lazy[node]
            lazy[2*node+1] += lazy[node]
        lazy[node] = 0

    if r < start or end < l: return
    if l <= start and end <= r:
        tree[node] += (end - start + 1) * delta
        if start != end:
            lazy[2*node]   += delta
            lazy[2*node+1] += delta
        return
    mid = (start + end) // 2
    range_update(tree, lazy, 2*node,   start, mid, l, r, delta)
    range_update(tree, lazy, 2*node+1, mid+1, end, l, r, delta)
    tree[node] = tree[2*node] + tree[2*node+1]
```

---

## Comparison with Fenwick Tree

| Feature | Segment Tree | Fenwick Tree (BIT) |
|---|---|---|
| Range query | Any associative op | Prefix sum only (invertible ops) |
| Point update | ✅ O(log n) | ✅ O(log n) |
| Range update | ✅ O(log n) with lazy | ✅ with difference array trick |
| Space | O(4n) | O(n) |
| Code complexity | Higher | Lower |
| Use when | General range ops, min/max | Sum/prefix queries (simpler code) |

---

## Complexity Analysis

| Operation | Time | Space |
|---|---|---|
| Build | O(n) | O(4n) |
| Point query | O(log n) | — |
| Range query | O(log n) | — |
| Point update | O(log n) | — |
| Range update (lazy) | O(log n) | O(4n) for lazy array |

---

## Language Implementations

### Go

```go
type SegTree struct {
    tree []int
    n    int
}

func NewSegTree(arr []int) *SegTree {
    n := len(arr)
    t := &SegTree{tree: make([]int, 4*n), n: n}
    t.build(arr, 1, 0, n-1)
    return t
}

func (t *SegTree) build(arr []int, node, start, end int) {
    if start == end { t.tree[node] = arr[start]; return }
    mid := (start + end) / 2
    t.build(arr, 2*node, start, mid)
    t.build(arr, 2*node+1, mid+1, end)
    t.tree[node] = t.tree[2*node] + t.tree[2*node+1]
}

func (t *SegTree) Query(l, r int) int { return t.query(1, 0, t.n-1, l, r) }
func (t *SegTree) query(node, start, end, l, r int) int {
    if r < start || end < l { return 0 }
    if l <= start && end <= r { return t.tree[node] }
    mid := (start + end) / 2
    return t.query(2*node, start, mid, l, r) + t.query(2*node+1, mid+1, end, l, r)
}
```

### Java

```java
class SegmentTree {
    int[] tree;
    int n;
    SegmentTree(int[] arr) {
        n = arr.length; tree = new int[4 * n];
        build(arr, 1, 0, n - 1);
    }
    void build(int[] arr, int node, int s, int e) {
        if (s == e) { tree[node] = arr[s]; return; }
        int m = (s + e) / 2;
        build(arr, 2*node, s, m); build(arr, 2*node+1, m+1, e);
        tree[node] = tree[2*node] + tree[2*node+1];
    }
    int query(int node, int s, int e, int l, int r) {
        if (r < s || e < l) return 0;
        if (l <= s && e <= r) return tree[node];
        int m = (s + e) / 2;
        return query(2*node, s, m, l, r) + query(2*node+1, m+1, e, l, r);
    }
}
```

### Python

```python
class SegmentTree:
    def __init__(self, arr: list[int]):
        self.n = len(arr)
        self.tree = [0] * (4 * self.n)
        self._build(arr, 1, 0, self.n - 1)

    def _build(self, arr, node, s, e):
        if s == e: self.tree[node] = arr[s]; return
        m = (s + e) // 2
        self._build(arr, 2*node, s, m)
        self._build(arr, 2*node+1, m+1, e)
        self.tree[node] = self.tree[2*node] + self.tree[2*node+1]

    def query(self, l, r): return self._query(1, 0, self.n-1, l, r)
    def _query(self, node, s, e, l, r):
        if r < s or e < l: return 0
        if l <= s <= e <= r: return self.tree[node]
        m = (s + e) // 2
        return self._query(2*node, s, m, l, r) + self._query(2*node+1, m+1, e, l, r)
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Allocating `2*n` instead of `4*n` nodes | Use `4*n` to safely accommodate all tree nodes |
| Using 0-indexed root (conflicts with `2*0=0`) | Use 1-indexed root — root at index 1 |
| Returning wrong identity for out-of-range | Sum → 0, Min → +∞, Max → -∞, GCD → 0 |
| Forgetting to propagate lazy before recursing | Always flush `lazy[node]` before accessing children |
| Mixing 0-indexed and 1-indexed in the same code | Decide on one convention and stick to it |

---

## Problems Covered

| Problem | Segment Tree Operation | LeetCode |
|---|---|---|
| Range Sum Query — Mutable | Point update + range sum | #307 |
| Count of Smaller Numbers After Self | Coordinate compression + segment tree | #315 |
| Range Minimum Query | Range min query | Classic |
| My Calendar III | Range update + range max | #732 |
