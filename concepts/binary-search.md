# Binary Search

> Category: **Algorithm**
> Difficulty: **Foundational → Intermediate**

---

# Table of Contents

1. What is Binary Search?
2. The Core Idea
3. Loop Invariant
4. Template 1 — Classic Binary Search
5. Template 2 — Left Boundary (Find First True)
6. Template 3 — Right Boundary (Find Last True)
7. Off-by-One Analysis
8. Binary Search on Answer Space
9. Complexity Analysis
10. Language Idioms
11. Common Mistakes
12. Key Takeaways

---

# What is Binary Search?

**Binary Search** is an algorithm that finds a target in a **sorted** collection by repeatedly halving the search space.

Instead of checking every element (O(n)), binary search eliminates half the remaining candidates each step, achieving **O(log n)**.

```
Sorted array: [2, 5, 8, 12, 16, 23, 38, 42, 56, 72]

Search for: 23

Step 1: mid = 16 → 23 > 16 → search right half
Step 2: mid = 42 → 23 < 42 → search left half
Step 3: mid = 23 → found!
```

3 steps instead of 10.

---

## When Can You Use Binary Search?

Binary search applies whenever the search space has a **monotonic property**:

- A sorted array where we search for a value.
- A condition that changes from `false` to `true` (or `true` to `false`) at some threshold.
- A problem where you can ask "is `x` a valid answer?" and eliminate half the range.

---

# The Core Idea

Maintain a search window defined by two pointers:

```
left  = first valid index
right = last valid index
```

Repeatedly compute the midpoint, evaluate the condition, and shrink the window:

```
mid = left + (right - left) / 2    ← avoids integer overflow

if target == array[mid]:
    found!
elif target > array[mid]:
    left = mid + 1      ← discard left half including mid
else:
    right = mid - 1     ← discard right half including mid
```

The key question in every variant: **what happens to `left`, `right`, and `mid` at each step?**

---

# Loop Invariant

A loop invariant is a property that holds before and after every iteration.

For binary search:

> The answer (if it exists) is always within `[left, right]`.

Every update to `left` or `right` must maintain this invariant. If `left = mid + 1` discards mid, it means mid was definitely not the answer. If `right = mid - 1` discards mid, same reason.

---

# Template 1 — Classic Binary Search

**Goal**: find exact target in sorted array. Returns index or -1.

```
left  = 0
right = n - 1

while left <= right:
    mid = left + (right - left) // 2

    if array[mid] == target:
        return mid
    elif array[mid] < target:
        left = mid + 1
    else:
        right = mid - 1

return -1
```

**Loop condition**: `left <= right` — the window is non-empty.  
**Termination**: when `left > right`, every element has been eliminated.

---

## Example

```
array  = [1, 3, 5, 7, 9, 11]
target = 7

left=0, right=5
  mid=2 → array[2]=5 < 7 → left=3

left=3, right=5
  mid=4 → array[4]=9 > 7 → right=3

left=3, right=3
  mid=3 → array[3]=7 == 7 → return 3
```

---

# Template 2 — Left Boundary (Find First True)

**Goal**: find the **leftmost** index where a condition becomes `true`.

Used when: find first occurrence, find insertion point, find first element ≥ target.

```
left  = 0
right = n     ← n is one past the last index (open right bound)

while left < right:
    mid = left + (right - left) // 2

    if condition(mid):
        right = mid        ← mid might be the answer; keep it
    else:
        left = mid + 1     ← mid is definitely not the answer

return left    ← left == right == first True position
```

**Loop condition**: `left < right` — stops when the window collapses to a single point.  
**Termination**: `left == right` is the first position where condition is true. If no position satisfies the condition, `left == n`.

---

## Visualisation

```
Condition array (False = 0, True = 1):

index:  0  1  2  3  4  5  6
cond:   F  F  F  T  T  T  T

Goal: find leftmost T (index 3)

Initial: left=0, right=7

mid=3 → T → right=3
  left=0, right=3

mid=1 → F → left=2
  left=2, right=3

mid=2 → F → left=3
  left=3, right=3 → done

return left = 3  ✓
```

---

# Template 3 — Right Boundary (Find Last True)

**Goal**: find the **rightmost** index where a condition is `true`.

```
left  = -1    ← one before the first index
right = n - 1

while left < right:
    mid = left + (right - left + 1) // 2    ← ceiling to avoid infinite loop

    if condition(mid):
        left = mid         ← mid might be the answer; keep it
    else:
        right = mid - 1    ← mid is definitely not the answer

return left    ← last True position (-1 if none)
```

**Why ceiling?**  
When `left + 1 == right`, floor mid equals `left`. If condition(mid) is true, we'd set `left = mid = left` — an infinite loop. Ceiling ensures `mid = right` in this case, advancing the window.

---

# Off-by-One Analysis

The most common source of bugs. Use this checklist:

| Question | Answer |
|----------|--------|
| Is `right` inclusive or exclusive? | Inclusive → `right = n-1`, loop `<= `; Exclusive → `right = n`, loop `<` |
| Does `mid` get included in next window? | If yes, use `left = mid` or `right = mid`; if no, use `left = mid+1` or `right = mid-1` |
| Which mid formula avoids overflow? | `left + (right - left) / 2` always |
| Why ceiling for right-boundary? | Prevents infinite loop when window is size 2 |

---

## Quick Reference

| Template | Condition | left update | right update | Returns |
|----------|-----------|-------------|--------------|---------|
| Classic exact match | `left <= right` | `mid + 1` | `mid - 1` | index or -1 |
| First True (left boundary) | `left < right` | `mid + 1` | `mid` | left |
| Last True (right boundary) | `left < right` | `mid` | `mid - 1` | left |

---

# Binary Search on Answer Space

Binary search is not limited to arrays. It applies to any **monotonically ordered answer space**.

**Pattern**: instead of searching an array, search the range of **possible answers** `[lo, hi]`. For each candidate answer `mid`, check feasibility.

```
lo  = minimum_possible_answer
hi  = maximum_possible_answer

while lo < hi:
    mid = lo + (hi - lo) // 2

    if feasible(mid):
        hi = mid       ← mid works; try smaller
    else:
        lo = mid + 1   ← mid doesn't work; need bigger

return lo
```

**Examples**:

| Problem | Answer Space | Feasibility Check |
|---------|-------------|------------------|
| Koko Eating Bananas | eating speed [1, max_pile] | can finish all piles in H hours? |
| Minimum Time to Complete Trips | time [1, max_time] | enough trips completed? |
| Capacity to Ship Packages | capacity [max_weight, total_weight] | can ship all in D days? |

---

# Complexity Analysis

| Metric | Value |
|--------|-------|
| Time | O(log n) |
| Space | O(1) |

**Why O(log n)?**  
Each iteration halves the search window. Starting with `n` elements, after `k` iterations there are `n / 2^k` elements left. When this reaches 1: `k = log₂ n`.

---

## Comparison with Linear Search

| Input Size n | Linear Search O(n) | Binary Search O(log n) |
|-------------|--------------------|-----------------------|
| 1,000 | 1,000 ops | ~10 ops |
| 1,000,000 | 1,000,000 ops | ~20 ops |
| 1,000,000,000 | 1,000,000,000 ops | ~30 ops |

---

# Language Idioms

## Java

```java
// Classic binary search
int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if      (nums[mid] == target) return mid;
        else if (nums[mid] <  target) left  = mid + 1;
        else                          right = mid - 1;
    }
    return -1;
}

// Java standard library — Arrays.binarySearch(arr, target)
// Returns index if found; -(insertion point) - 1 if not found.
```

---

## Python

```python
# Classic binary search
def binary_search(nums, target):
    left, right = 0, len(nums) - 1
    while left <= right:
        mid = left + (right - left) // 2
        if   nums[mid] == target: return mid
        elif nums[mid] <  target: left  = mid + 1
        else:                     right = mid - 1
    return -1

# Python standard library
import bisect
bisect.bisect_left(nums, target)   # leftmost insertion point
bisect.bisect_right(nums, target)  # rightmost insertion point
```

---

## Go

```go
// Classic binary search
func binarySearch(nums []int, target int) int {
    left, right := 0, len(nums)-1
    for left <= right {
        mid := left + (right-left)/2
        if      nums[mid] == target { return mid }
        else if nums[mid] <  target { left  = mid + 1 }
        else                        { right = mid - 1 }
    }
    return -1
}

// Go standard library — sort.SearchInts(a, x)
// Returns the smallest index i such that a[i] >= x.
```

---

# Common Mistakes

## Integer Overflow in Mid Calculation

```
Wrong:
mid = (left + right) / 2    ← left + right overflows int32 for large indices

Correct:
mid = left + (right - left) / 2
```

---

## Wrong Loop Condition

```
Template 1 needs: left <= right
Template 2/3 need: left < right

Using < instead of <= in Template 1 misses the case when the target is at the single remaining element.
```

---

## Updating the Wrong Pointer

```
Wrong — infinite loop when condition(mid) is true:
if condition(mid): left = mid    ← left never advances if mid == left

This only affects Template 3 (right boundary). Fix: use ceiling mid.
```

---

## Not Returning the Correct Value After the Loop

```
Template 1: return -1 (not found)
Template 2: return left (first True, or n if none)
Template 3: return left (last True, or -1 if none)
```

---

## Assuming the Array Must Be Strictly Sorted

Binary search works on **non-strictly** sorted arrays (duplicates allowed) when searching for boundaries. It does NOT work if the array is unsorted in a non-monotonic way.

---

# Key Takeaways

After studying this concept, you should understand:

- Binary search requires a **monotonic** search space — sorted array or a boolean condition with a single transition.
- Three templates: classic exact match, left boundary (first True), right boundary (last True).
- Always use `mid = left + (right - left) / 2` to prevent overflow.
- The loop condition and pointer update rules must be consistent — use the checklist.
- Binary search on answer space extends the technique beyond arrays to any feasibility problem.

---

# Problems Covered

| Problem | Template | Key Insight |
|---------|----------|-------------|
| Binary Search | Template 1 — exact match | Direct application |
| Search Insert Position | Template 2 — left boundary | Find first index ≥ target |
| First Bad Version | Template 2 — left boundary | Find first `true` in monotonic boolean array |
| Search in Rotated Sorted Array | Template 1 variant | Determine which half is sorted, then narrow |
