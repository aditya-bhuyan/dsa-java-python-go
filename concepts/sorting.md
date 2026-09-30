# Sorting Algorithms

## Table of Contents

1. [Introduction](#introduction)
2. [Comparison-Based Sorting](#comparison-based-sorting)
3. [Non-Comparison Sorting](#non-comparison-sorting)
4. [Algorithm Deep Dives](#algorithm-deep-dives)
5. [Stability](#stability)
6. [Choosing the Right Algorithm](#choosing-the-right-algorithm)
7. [Complexity Summary](#complexity-summary)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

Sorting rearranges a collection into a specific order (ascending or descending). It is one of the most fundamental algorithmic problems and underlies many other algorithms (binary search, greedy by value, etc.).

**Lower bound for comparison-based sorting:** Ω(n log n) — proven via decision tree argument. Non-comparison sorts can beat this with assumptions on input.

---

## Comparison-Based Sorting

| Algorithm | Best | Average | Worst | Space | Stable? |
|---|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | ❌ |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |
| Tim Sort | O(n) | O(n log n) | O(n log n) | O(n) | ✅ |

---

## Non-Comparison Sorting

| Algorithm | Time | Space | Constraint |
|---|---|---|---|
| Counting Sort | O(n + k) | O(k) | Integer keys in range [0, k) |
| Radix Sort | O(d × (n + k)) | O(n + k) | d digits, base k |
| Bucket Sort | O(n + k) avg | O(n + k) | Uniformly distributed data |

---

## Algorithm Deep Dives

### Bubble Sort

Repeatedly swap adjacent elements that are out of order. Each pass bubbles the largest unsorted element to its correct position.

```
for pass = 0 to n-2:
    for i = 0 to n-2-pass:
        if arr[i] > arr[i+1]:
            swap(arr[i], arr[i+1])
```

```
[5, 3, 8, 1]  →  pass 1: [3, 5, 1, 8]  →  pass 2: [3, 1, 5, 8]  →  [1, 3, 5, 8]
```

Optimisation: if no swaps in a pass → already sorted → exit early (best case O(n)).

---

### Insertion Sort

Build the sorted portion one element at a time by inserting each new element into its correct position.

```
for i = 1 to n-1:
    key = arr[i]
    j = i - 1
    while j >= 0 and arr[j] > key:
        arr[j+1] = arr[j]
        j--
    arr[j+1] = key
```

```
[5, 3, 8, 1]
 Step 1: [3, 5, 8, 1]    insert 3
 Step 2: [3, 5, 8, 1]    8 already in place
 Step 3: [1, 3, 5, 8]    insert 1
```

**Best for:** Small arrays or nearly sorted data. Used internally by Tim Sort for small runs.

---

### Merge Sort

Divide the array in half, recursively sort each half, then merge.

```
merge_sort(arr):
    if len(arr) <= 1: return arr
    mid = len(arr) // 2
    left  = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)
```

```
[5,3,8,1] → [5,3],[8,1] → [5],[3],[8],[1] → [3,5],[1,8] → [1,3,5,8]
```

**Guarantees O(n log n).** Stable. Preferred for linked lists (no random access needed). Uses O(n) extra space.

---

### Quick Sort

Choose a pivot, partition the array around it, recursively sort each partition.

```
quick_sort(arr, lo, hi):
    if lo >= hi: return
    p = partition(arr, lo, hi)
    quick_sort(arr, lo, p-1)
    quick_sort(arr, p+1, hi)

partition (Lomuto scheme):
    pivot = arr[hi]; i = lo - 1
    for j = lo to hi-1:
        if arr[j] <= pivot: i++; swap(arr[i], arr[j])
    swap(arr[i+1], arr[hi])
    return i+1
```

```
[5,3,8,1], pivot=1 → [1|5,3,8] → [1,3,5,8]
```

**In-place** (O(log n) stack). Average O(n log n). Worst O(n²) on sorted/reverse-sorted with naive pivot.  
**Mitigation:** Randomised pivot, median-of-three.

---

### Counting Sort

For integers in range `[0, k)`:

```
count = [0] * (k+1)
for x in arr: count[x]++
# cumulative
for i = 1 to k: count[i] += count[i-1]
# build output (stable — iterate in reverse)
output = [0] * n
for x in reversed(arr):
    count[x] -= 1
    output[count[x]] = x
```

```
arr = [4,2,2,8,3,3,1], k=8
count = [0,1,2,2,1,0,0,0,1]
After cumulative → output = [1,2,2,3,3,4,8] ✓
```

---

### Radix Sort

Sort integers digit by digit from least significant to most significant using counting sort as the subroutine:

```
for d = 0 to (max_digits - 1):
    stable_sort_by_digit(arr, d)
```

Stable sort per digit preserves relative order of previously sorted digits.

---

## Stability

A sort is **stable** if elements with equal keys retain their original relative order.

```
Input:  [(A,2), (B,1), (C,2)]   (sort by number)
Stable:   [(B,1), (A,2), (C,2)]  ← A before C (original order preserved)
Unstable: [(B,1), (C,2), (A,2)]  ← C before A (original order changed)
```

**Why it matters:** When sorting by multiple criteria (e.g., sort by last name, then by first name), stability ensures the secondary sort is preserved.

---

## Choosing the Right Algorithm

| Scenario | Recommendation |
|---|---|
| General purpose | Tim Sort (built into Python, Java) |
| Small n or nearly sorted | Insertion Sort |
| Need guaranteed O(n log n) | Merge Sort |
| In-place + fast in practice | Quick Sort (randomised pivot) |
| Integer keys in small range | Counting Sort |
| Large integers, many digits | Radix Sort |
| Large dataset on external storage | Merge Sort (sequential access) |
| Need stable + in-place | Tim Sort or Merge Sort |

---

## Complexity Summary

| Algorithm | Best | Average | Worst | Space | Stable |
|---|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |
| Counting Sort | O(n+k) | O(n+k) | O(n+k) | O(k) | ✅ |
| Radix Sort | O(d(n+k)) | O(d(n+k)) | O(d(n+k)) | O(n+k) | ✅ |

---

## Language Implementations

### Go

```go
import "sort"

// Built-in sort (intro-sort: quick + heap + insertion)
sort.Ints(arr)                   // ascending
sort.Sort(sort.Reverse(sort.IntSlice(arr)))   // descending

// Custom comparator
sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })

// Merge sort (manual)
func mergeSort(arr []int) []int { /* see divide-and-conquer.md */ }
```

### Java

```java
// Arrays.sort — dual-pivot quicksort for primitives, Tim Sort for objects
Arrays.sort(arr);
Arrays.sort(arr, 0, n);                  // range sort
Arrays.sort(arr, Comparator.reverseOrder()); // descending (boxed)

// Collections.sort — Tim Sort, stable
Collections.sort(list);
list.sort((a, b) -> b - a);             // descending

// Custom: sort by second element
Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
```

### Python

```python
# Tim Sort — built-in, stable, O(n log n)
arr.sort()                          # in-place
sorted_arr = sorted(arr)            # returns new list
arr.sort(reverse=True)              # descending
arr.sort(key=lambda x: x[1])       # custom key

# Manual merge sort
def merge_sort(arr): ...            # see divide-and-conquer.md
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Using bubble sort for large n | Use built-in (Tim Sort) for O(n log n) |
| Quick sort on nearly-sorted data without random pivot | Always randomise the pivot |
| Assuming `Arrays.sort` on objects is stable | It is (Tim Sort); primitive arrays use quicksort (not stable) |
| Forgetting that `sort.Ints` in Go modifies in place | It is in-place; no return value |
| Using `==` to compare after sort instead of `!=` 0 for custom comparator | Custom comparator returns negative/0/positive, not bool |

---

## Problems Covered

| Problem | Sorting Technique | LeetCode |
|---|---|---|
| Sort an Array | Merge / Quick Sort | #912 |
| Sort Colors (Dutch Flag) | 3-way partition | #75 |
| Meeting Rooms | Sort intervals by start | #252 |
| Merge Intervals | Sort + scan | #56 |
| Largest Number | Custom string comparator | #179 |
