# Divide and Conquer

## Table of Contents

1. [Introduction](#introduction)
2. [The Three Steps](#the-three-steps)
3. [Recurrence Relations & Master Theorem](#recurrence-relations--master-theorem)
4. [Classic Algorithms](#classic-algorithms)
5. [Divide and Conquer vs Dynamic Programming](#divide-and-conquer-vs-dynamic-programming)
6. [Complexity Analysis](#complexity-analysis)
7. [Language Implementations](#language-implementations)
8. [Common Mistakes](#common-mistakes)
9. [Problems Covered](#problems-covered)

---

## Introduction

**Divide and Conquer** is a recursive algorithm design paradigm. It solves a problem by:
1. **Dividing** the problem into smaller subproblems of the same type.
2. **Conquering** (solving) each subproblem recursively. When the subproblem is small enough, solve it directly (base case).
3. **Combining** the solutions of subproblems to produce the overall solution.

Classic examples: **Merge Sort**, **Quick Sort**, **Binary Search**, **Fast Fourier Transform**, **Closest Pair of Points**.

---

## The Three Steps

```
divide_and_conquer(problem):
    if base_case(problem):
        return solve_directly(problem)

    sub1, sub2, ... = divide(problem)          # Step 1: Divide
    sol1 = divide_and_conquer(sub1)            # Step 2: Conquer
    sol2 = divide_and_conquer(sub2)
    ...
    return combine(sol1, sol2, ...)            # Step 3: Combine
```

### ASCII Visualization — Merge Sort

```
            [5, 3, 8, 1, 4, 2]
                    |  divide
         [5, 3, 8]      [1, 4, 2]
              |               |
         [5,3]  [8]       [1,4]  [2]
           |                |
         [5][3]           [1][4]
           |  conquer        |
         [3,5]  [8]       [1,4]  [2]
              |  combine       |
           [3, 5, 8]       [1, 2, 4]
                    |  combine
            [1, 2, 3, 4, 5, 8]
```

---

## Recurrence Relations & Master Theorem

Divide and conquer algorithms satisfy a **recurrence relation**:

```
T(n) = a·T(n/b) + f(n)
```

Where:
- `a` = number of subproblems
- `n/b` = size of each subproblem
- `f(n)` = work to divide and combine

### Master Theorem

Given `T(n) = a·T(n/b) + f(n)`, let `c = log_b(a)`:

| Case | Condition | Result |
|---|---|---|
| Case 1 | `f(n) = O(n^(c-ε))` for some ε > 0 | `T(n) = Θ(n^c)` |
| Case 2 | `f(n) = Θ(n^c)` | `T(n) = Θ(n^c · log n)` |
| Case 3 | `f(n) = Ω(n^(c+ε))` and regularity | `T(n) = Θ(f(n))` |

### Examples

| Algorithm | Recurrence | Result |
|---|---|---|
| Merge Sort | T(n) = 2T(n/2) + O(n) | O(n log n) — Case 2 |
| Binary Search | T(n) = T(n/2) + O(1) | O(log n) — Case 2 |
| Quick Sort (avg) | T(n) = 2T(n/2) + O(n) | O(n log n) — Case 2 |
| Strassen Matrix Mult | T(n) = 7T(n/2) + O(n²) | O(n^2.81) — Case 1 |

---

## Classic Algorithms

### Merge Sort

```
merge_sort(arr):
    if len(arr) <= 1: return arr
    mid = len(arr) // 2
    left  = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)

merge(left, right):
    result = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]: result.append(left[i]); i++
        else:                   result.append(right[j]); j++
    return result + left[i:] + right[j:]
```

**Stable**, O(n log n) guaranteed, O(n) extra space.

---

### Quick Sort

```
quick_sort(arr, lo, hi):
    if lo >= hi: return
    pivot_idx = partition(arr, lo, hi)
    quick_sort(arr, lo, pivot_idx - 1)
    quick_sort(arr, pivot_idx + 1, hi)

partition(arr, lo, hi):
    pivot = arr[hi]
    i = lo - 1
    for j = lo to hi-1:
        if arr[j] <= pivot:
            i++; swap(arr[i], arr[j])
    swap(arr[i+1], arr[hi])
    return i + 1
```

**In-place**, O(n log n) average, O(n²) worst case (sorted input with last-element pivot).

---

### Binary Search (Divide & Conquer View)

```
binary_search(arr, lo, hi, target):
    if lo > hi: return -1
    mid = (lo + hi) // 2
    if arr[mid] == target: return mid
    if target < arr[mid]: return binary_search(arr, lo, mid-1, target)
    return binary_search(arr, mid+1, hi, target)
```

T(n) = T(n/2) + O(1) → O(log n)

---

### Maximum Subarray (D&C approach)

```
max_crossing(arr, lo, mid, hi):
    left_sum = -∞; total = 0
    for i = mid downto lo:
        total += arr[i]; left_sum = max(left_sum, total)
    right_sum = -∞; total = 0
    for i = mid+1 to hi:
        total += arr[i]; right_sum = max(right_sum, total)
    return left_sum + right_sum

max_subarray(arr, lo, hi):
    if lo == hi: return arr[lo]
    mid = (lo + hi) // 2
    return max(max_subarray(arr, lo, mid),
               max_subarray(arr, mid+1, hi),
               max_crossing(arr, lo, mid, hi))
```

T(n) = 2T(n/2) + O(n) → O(n log n). *Note: Kadane's is O(n) — D&C is not optimal here.*

---

### Count Inversions

An inversion is a pair `(i, j)` where `i < j` but `arr[i] > arr[j]`. Count using a modified merge sort:

```
During merge: when right[j] < left[i],
    inversions += (mid - i + 1)   ← all remaining left elements are > right[j]
```

T(n) = 2T(n/2) + O(n) → O(n log n) vs O(n²) brute force.

---

## Divide and Conquer vs Dynamic Programming

| Feature | Divide & Conquer | Dynamic Programming |
|---|---|---|
| Subproblem overlap | **No** — subproblems are independent | **Yes** — subproblems reused |
| Memoization needed | No | Yes |
| Example | Merge Sort, Quick Sort | Fibonacci, Knapsack |
| Why D&C fails on DP problems | Re-solves same subproblems exponentially | DP stores results → polynomial |

**Rule of thumb:** If sub-problems are disjoint → D&C. If they overlap → DP.

---

## Complexity Analysis

| Algorithm | Time (avg) | Time (worst) | Space |
|---|---|---|---|
| Merge Sort | O(n log n) | O(n log n) | O(n) |
| Quick Sort | O(n log n) | O(n²) | O(log n) stack |
| Binary Search | O(log n) | O(log n) | O(1) iterative |
| Count Inversions | O(n log n) | O(n log n) | O(n) |
| Closest Pair | O(n log n) | O(n log n) | O(n) |

---

## Language Implementations

### Go — Merge Sort

```go
func mergeSort(arr []int) []int {
    if len(arr) <= 1 { return arr }
    mid := len(arr) / 2
    left := mergeSort(arr[:mid])
    right := mergeSort(arr[mid:])
    return merge(left, right)
}
func merge(l, r []int) []int {
    res := make([]int, 0, len(l)+len(r))
    i, j := 0, 0
    for i < len(l) && j < len(r) {
        if l[i] <= r[j] { res = append(res, l[i]); i++ } else { res = append(res, r[j]); j++ }
    }
    return append(append(res, l[i:]...), r[j:]...)
}
```

### Java — Quick Sort

```java
void quickSort(int[] arr, int lo, int hi) {
    if (lo >= hi) return;
    int p = partition(arr, lo, hi);
    quickSort(arr, lo, p - 1);
    quickSort(arr, p + 1, hi);
}
int partition(int[] arr, int lo, int hi) {
    int pivot = arr[hi], i = lo - 1;
    for (int j = lo; j < hi; j++)
        if (arr[j] <= pivot) { i++; int t=arr[i]; arr[i]=arr[j]; arr[j]=t; }
    int t=arr[i+1]; arr[i+1]=arr[hi]; arr[hi]=t;
    return i + 1;
}
```

### Python — Merge Sort

```python
def merge_sort(arr: list[int]) -> list[int]:
    if len(arr) <= 1: return arr
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)

def merge(l: list[int], r: list[int]) -> list[int]:
    res, i, j = [], 0, 0
    while i < len(l) and j < len(r):
        if l[i] <= r[j]: res.append(l[i]); i += 1
        else:             res.append(r[j]); j += 1
    return res + l[i:] + r[j:]
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Quick sort with last-element pivot on sorted input → O(n²) | Use random pivot or median-of-three |
| Off-by-one in `mid` calculation causing integer overflow | Use `mid = lo + (hi - lo) / 2` not `(lo + hi) / 2` |
| Forgetting base case → infinite recursion | Always handle `n <= 1` (or `lo >= hi`) |
| Applying D&C where DP is needed (overlapping subproblems) | Memoize if you see the same sub-problem called twice |
| Merge sort mutating input slice in Go | Pass copies or use index-based merge |

---

## Problems Covered

| Problem | D&C Technique | LeetCode |
|---|---|---|
| Sort an Array | Merge Sort / Quick Sort | #912 |
| Kth Largest Element | Quick Select | #215 |
| Maximum Subarray | D&C crossing sum | #53 |
| Count of Smaller Numbers | Modified merge sort | #315 |
| Median of Two Sorted Arrays | Binary search halving | #4 |
