# Priority Queue

> Category: **Data Structure**
> Difficulty: **Intermediate**

---

# Table of Contents

1. What is a Priority Queue?
2. How It Works — The Heap
3. Min Heap vs Max Heap
4. Core Operations
5. Internal Structure — Complete Binary Tree
6. Heapify
7. Language Idioms
8. Complexity Summary
9. When to Use a Priority Queue
10. Common Mistakes
11. Key Takeaways

---

# What is a Priority Queue?

A **Priority Queue** is an abstract data structure where each element has an associated **priority**. Elements are dequeued in **priority order**, not insertion order.

```
Regular Queue (FIFO)         Priority Queue (highest priority first)

Insert: 3, 1, 4, 1, 5       Insert: 3, 1, 4, 1, 5
Remove order: 3 1 4 1 5      Remove order: 5 4 3 1 1
```

A priority queue answers the question:

> "What is the most important element right now?"

---

## Key Distinction from Queue

| Property | Queue | Priority Queue |
|----------|-------|----------------|
| Remove order | Insertion order (FIFO) | Priority order |
| Use case | BFS, scheduling | Top-K, Dijkstra, merge K lists |

---

# How It Works — The Heap

The most common and efficient implementation of a priority queue is a **binary heap**.

A **binary heap** is a complete binary tree with the **heap property**:

- **Min Heap**: every parent is **≤** both children. The minimum element is always at the root.
- **Max Heap**: every parent is **≥** both children. The maximum element is always at the root.

---

# Min Heap vs Max Heap

## Min Heap

```
        1
       / \
      3   2
     / \ / \
    7  4 5  6
```

Root is always the smallest. `poll()` removes the minimum.

---

## Max Heap

```
        9
       / \
      7   8
     / \ / \
    3  4 5  6
```

Root is always the largest. `poll()` removes the maximum.

---

## Choosing Between Them

| Goal | Use |
|------|-----|
| Always access the minimum | Min Heap |
| Always access the maximum | Max Heap |
| Top-K largest elements | Min Heap of size K (evict the smallest) |
| Top-K smallest elements | Max Heap of size K (evict the largest) |

---

# Core Operations

## Insert (offer / push)

Add the new element at the end of the heap, then **bubble up** (sift up) until the heap property is restored.

```
Insert 2 into:        After sift-up:
        5                   2
       / \                 / \
      7   6               5   6
     /                   /
    2 ← new             7
```

Time: O(log n)

---

## Poll / Pop (remove top)

Remove the root. Move the last element to the root, then **bubble down** (sift down) until the heap property is restored.

```
Poll from:            After sift-down:
        1                   2
       / \                 / \
      3   2               3   5
     / \   \             /
    7   4   5           7
```

Time: O(log n)

---

## Peek

Return the root element without removing it. The minimum (min heap) or maximum (max heap) is always at index 0.

Time: O(1)

---

## Heapify (Build Heap from Array)

Convert an unsorted array into a heap in O(n) time by calling sift-down from the last non-leaf node up to the root.

```
Array [4, 3, 8, 1, 2]

After heapify (min heap):

        1
       / \
      2   8
     / \
    4   3
```

Time: O(n) — better than inserting n elements one by one which would be O(n log n).

---

# Internal Structure — Complete Binary Tree

A binary heap stores its complete binary tree in a **flat array**, exploiting the tree's structure:

```
Index:   0   1   2   3   4   5   6
Array: [ 1 | 3 | 2 | 7 | 4 | 5 | 6 ]

Tree:
        1           (index 0)
       / \
      3   2         (index 1, 2)
     / \ / \
    7  4 5  6       (index 3, 4, 5, 6)
```

For any node at index `i`:

```
Parent       = (i - 1) / 2
Left child   = 2 * i + 1
Right child  = 2 * i + 2
```

No pointer overhead — the tree is implicit in the array layout.

---

# Heapify

**Sift Up** (used after insert):

```
while i > 0 and heap[parent(i)] > heap[i]:
    swap(heap[i], heap[parent(i)])
    i = parent(i)
```

**Sift Down** (used after poll):

```
while left_child(i) < n:
    smallest = i

    if heap[left] < heap[smallest]:  smallest = left
    if heap[right] < heap[smallest]: smallest = right

    if smallest == i: break

    swap(heap[i], heap[smallest])
    i = smallest
```

---

# Language Idioms

## Java

Java's `PriorityQueue` is a **min heap** by default.

```java
// Min heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(3);
minHeap.offer(1);
minHeap.offer(2);
minHeap.poll();  // returns 1 (minimum)

// Max heap — reverse the comparator
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(3);
maxHeap.poll();  // returns 3 (maximum)
```

---

## Python

Python's `heapq` module is a **min heap**.

```python
import heapq

heap = []
heapq.heappush(heap, 3)
heapq.heappush(heap, 1)
heapq.heappush(heap, 2)
heapq.heappop(heap)   # returns 1

# Max heap — negate all values
heapq.heappush(heap, -3)
-heapq.heappop(heap)  # returns 3
```

**Build heap from existing list:**

```python
nums = [3, 1, 4, 1, 5]
heapq.heapify(nums)   # O(n) in-place
```

---

## Go

Go has no built-in priority queue. Use `container/heap` with the `heap.Interface`:

```go
import "container/heap"

type MinHeap []int

func (h MinHeap) Len() int            { return len(h) }
func (h MinHeap) Less(i, j int) bool  { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x any)         { *h = append(*h, x.(int)) }
func (h *MinHeap) Pop() any {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[:n-1]
    return x
}

h := &MinHeap{3, 1, 2}
heap.Init(h)
heap.Push(h, 0)
top := heap.Pop(h)   // returns 0
```

---

# Complexity Summary

| Operation | Time | Space |
|-----------|------|-------|
| Insert (offer) | O(log n) | O(1) |
| Poll (remove top) | O(log n) | O(1) |
| Peek | O(1) | O(1) |
| Build heap (heapify) | O(n) | O(1) |
| Search | O(n) | O(1) |

---

# When to Use a Priority Queue

Use a priority queue when:

- You need **repeated access to the minimum or maximum** element.
- **Top-K problems** — find the K largest or smallest elements in a stream or array.
- **Dijkstra's shortest path** — always process the nearest unvisited node.
- **Merge K sorted lists** — always take the smallest current head.
- **Task scheduling** — always execute the highest-priority task.
- **Median of a data stream** — maintain two heaps (max heap for lower half, min heap for upper half).

---

# Common Mistakes

## Confusing Min Heap and Max Heap Default Behaviour

- Python `heapq` → **min heap** by default. Negate values for max heap.
- Java `PriorityQueue` → **min heap** by default. Pass `Collections.reverseOrder()` for max heap.

---

## Modifying Elements Already in the Heap

Once an element is inserted, changing it externally does not automatically fix the heap property. You must remove and re-insert, or use a decrease-key operation (not available in standard library heaps).

---

## Using a Priority Queue When a Sorted Array Suffices

If the input is fixed and you only need K elements, sorting and slicing is simpler:

```python
# Top-3 largest (static input)
sorted(nums)[-3:]     # O(n log n) but simple
```

A heap is preferred when elements **arrive dynamically** or when you need K << n.

---

## Forgetting to Negate for Max Heap in Python

```python
# Wrong — this is still a min heap
heapq.heappush(heap, 5)
heapq.heappush(heap, 3)
heapq.heappop(heap)   # returns 3, not 5

# Correct — negate to simulate max heap
heapq.heappush(heap, -5)
heapq.heappush(heap, -3)
-heapq.heappop(heap)  # returns 5
```

---

# Key Takeaways

After studying this concept, you should understand:

- A priority queue always gives you the minimum (or maximum) in O(1) and removes it in O(log n).
- It is backed by a binary heap — a complete binary tree stored as a flat array.
- The parent-child index formulas `(i-1)/2`, `2i+1`, `2i+2` are fundamental.
- Heapify builds a heap from an unsorted array in O(n) — better than n inserts.
- Python uses negation for max heaps; Java uses a reversed comparator.
- The canonical use cases: Top-K, Dijkstra, merge K lists, sliding window median.

---

# Problems Covered

Priority queue patterns appear in:

| Problem | Heap Usage |
|---------|------------|
| Kth Largest Element | Min heap of size K |
| Merge K Sorted Lists | Min heap on list heads |
| Task Scheduler | Max heap on task frequencies |
| Dijkstra's Algorithm | Min heap on (distance, node) pairs |
