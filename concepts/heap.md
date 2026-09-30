# Heap (Priority Queue)

## Table of Contents

1. [Introduction](#introduction)
2. [Heap Property](#heap-property)
3. [Binary Heap Structure](#binary-heap-structure)
4. [Core Operations](#core-operations)
5. [Heap Sort](#heap-sort)
6. [Top-K Pattern](#top-k-pattern)
7. [Two-Heap Pattern (Median)](#two-heap-pattern-median)
8. [Complexity Analysis](#complexity-analysis)
9. [Language Implementations](#language-implementations)
10. [Common Mistakes](#common-mistakes)
11. [Problems Covered](#problems-covered)

---

## Introduction

A **heap** is a complete binary tree that satisfies the **heap property**. It is the most efficient data structure for repeatedly extracting the minimum (or maximum) element, making it the backbone of priority queues, heap sort, Dijkstra's algorithm, and "top-k" problems.

**Min-Heap:** The smallest element is always at the root.  
**Max-Heap:** The largest element is always at the root.

---

## Heap Property

```
Min-Heap: parent ≤ both children
Max-Heap: parent ≥ both children
```

### Example Min-Heap

```
         1
        / \
       3   2
      / \ / \
     7  4 5  6
```

- Parent of index `i` is at `(i-1)//2`
- Left child of index `i` is at `2*i+1`
- Right child of index `i` is at `2*i+2`

### Array Representation

```
Heap:   [1, 3, 2, 7, 4, 5, 6]
Index:   0  1  2  3  4  5  6

          1 (idx 0)
         / \
        3   2  (idx 1, 2)
       / \ / \
      7  4 5  6  (idx 3, 4, 5, 6)
```

A heap is stored as an array — no explicit tree structure needed.

---

## Core Operations

### Heapify-Up (Bubble-Up / Sift-Up)

Used after **insert** — move the new element up until the heap property is restored:

```
insert(heap, val):
    heap.append(val)
    i = len(heap) - 1
    while i > 0:
        parent = (i - 1) // 2
        if heap[i] < heap[parent]:         # min-heap: swap if smaller than parent
            swap(heap[i], heap[parent])
            i = parent
        else:
            break
```

**Time:** O(log n) — at most height traversals.

---

### Heapify-Down (Bubble-Down / Sift-Down)

Used after **extract-min** — move the root down until the heap property is restored:

```
extract_min(heap):
    min_val = heap[0]
    heap[0] = heap.pop()              # move last element to root
    i = 0
    n = len(heap)
    while True:
        left, right = 2*i+1, 2*i+2
        smallest = i
        if left < n and heap[left] < heap[smallest]:   smallest = left
        if right < n and heap[right] < heap[smallest]: smallest = right
        if smallest == i: break
        swap(heap[i], heap[smallest])
        i = smallest
    return min_val
```

**Time:** O(log n).

---

### Build Heap (Heapify)

Convert an arbitrary array to a heap in O(n) — not O(n log n):

```
# Start from last non-leaf and heapify-down each node
for i in range(len(heap)//2 - 1, -1, -1):
    sift_down(heap, i)
```

**Why O(n)?** Most nodes are near the bottom and travel only a short distance.

---

### Peek

Return the minimum/maximum without removing it:
```
heap[0]   → O(1)
```

---

## Heap Sort

1. Build a max-heap from the array: O(n).
2. Repeatedly extract-max (swap root to end, heapify-down remaining): O(n log n).

```
def heap_sort(arr):
    build_max_heap(arr)              # O(n)
    for i in range(len(arr)-1, 0, -1):
        arr[0], arr[i] = arr[i], arr[0]   # move max to sorted end
        sift_down(arr, 0, i)              # restore heap on [0..i-1]
```

**Time:** O(n log n), **Space:** O(1) in-place. Not stable.

---

## Top-K Pattern

**Find the k largest elements in an array:**

```python
import heapq

def top_k_largest(nums, k):
    # Use a min-heap of size k
    # The smallest of the k largest = heap[0]
    heap = nums[:k]
    heapq.heapify(heap)                    # O(k)
    for num in nums[k:]:
        if num > heap[0]:                   # larger than the current min
            heapq.heapreplace(heap, num)    # O(log k)
    return heap                            # smallest-to-largest order
```

**Time:** O(n log k) — much better than O(n log n) sort when k << n.

**Find the k smallest elements:** Use a max-heap of size k with negated values, or use `heapq.nsmallest`.

---

## Two-Heap Pattern (Median)

Maintain the **running median** of a stream using two heaps:

```
max_heap (left half):  stores the smaller half as negated values in Python
min_heap (right half): stores the larger half

Invariant:
  len(max_heap) == len(min_heap)  OR  len(max_heap) == len(min_heap) + 1
  max_heap[0] (negated) <= min_heap[0]

Median:
  if equal sizes: (max_heap_top + min_heap_top) / 2
  else:           max_heap_top
```

```python
def add_num(num, lo, hi):       # lo=max_heap, hi=min_heap
    heapq.heappush(lo, -num)    # push to max_heap
    if lo and hi and (-lo[0] > hi[0]):
        heapq.heappush(hi, -heapq.heappop(lo))
    if len(lo) > len(hi) + 1:
        heapq.heappush(hi, -heapq.heappop(lo))
    if len(hi) > len(lo):
        heapq.heappush(lo, -heapq.heappop(hi))
```

---

## Complexity Analysis

| Operation | Time | Notes |
|---|---|---|
| Insert | O(log n) | Sift-up |
| Extract-Min/Max | O(log n) | Sift-down |
| Peek (min/max) | O(1) | Just `heap[0]` |
| Build Heap | O(n) | Bottom-up heapify |
| Heap Sort | O(n log n) | O(1) space |
| Top-K | O(n log k) | Maintain heap of size k |
| Space | O(n) | Array storage |

---

## Language Implementations

### Go

```go
import "container/heap"

// Implement heap.Interface for a min-heap
type MinHeap []int
func (h MinHeap) Len() int            { return len(h) }
func (h MinHeap) Less(i, j int) bool  { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x interface{}) { *h = append(*h, x.(int)) }
func (h *MinHeap) Pop() interface{}   {
    old := *h; n := len(old); x := old[n-1]; *h = old[:n-1]; return x
}

h := &MinHeap{3, 1, 4, 1, 5}
heap.Init(h)
heap.Push(h, 2)
min := heap.Pop(h).(int)
```

### Java

```java
// Min-heap (natural ordering)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(3); minHeap.offer(1); minHeap.offer(4);
int min = minHeap.poll();   // 1

// Max-heap (reversed comparator)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Custom comparator (e.g., sort by second element)
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
```

### Python

```python
import heapq

# Min-heap (Python heapq is always min-heap)
h = [3, 1, 4, 1, 5]
heapq.heapify(h)          # O(n) in-place
heapq.heappush(h, 2)
min_val = heapq.heappop(h)

# Max-heap: negate values
max_h = [-3, -1, -4]
heapq.heapify(max_h)
max_val = -heapq.heappop(max_h)

# Top-K largest
top_k = heapq.nlargest(k, nums)    # O(n log k)

# Top-K smallest
bot_k = heapq.nsmallest(k, nums)   # O(n log k)
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Python heapq is always min-heap; using it directly for max-heap | Negate values when pushing/popping: `heappush(h, -val)` |
| Forgetting `heapq.heapify()` when converting a list to a heap | Always call `heapify` before using a list as a heap |
| Java `PriorityQueue.peek()` vs `poll()` — forgetting that `poll()` removes | `peek()` = look, `poll()` = extract |
| Building a heap with repeated insertions O(n log n) instead of `heapify` O(n) | Use `heapq.heapify(arr)` or `heap.Init(&h)` for bulk construction |
| Go heap: calling `heap.Push` before `heap.Init` | Always call `heap.Init` first |

---

## Problems Covered

| Problem | Heap Pattern | LeetCode |
|---|---|---|
| Kth Largest Element | Min-heap of size k | #215 |
| Top K Frequent Elements | Max-heap / bucket sort | #347 |
| Find Median from Data Stream | Two-heap (lo/hi) | #295 |
| Merge K Sorted Lists | Min-heap of list heads | #23 |
| Task Scheduler | Max-heap + greedy | #621 |
