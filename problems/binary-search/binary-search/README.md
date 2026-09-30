# Binary Search

> Difficulty: **Easy**
> Topic: **Binary Search, Template 1 — Exact Match**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force
6. Optimized Approach — Binary Search
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

Given a sorted array of integers `nums` and an integer `target`, return the **index** of `target` if it is in the array. Otherwise, return `-1`.

You must write an algorithm with **O(log n)** runtime complexity.

---

# Examples

## Example 1

Input

```text
nums   = [-1, 0, 3, 5, 9, 12]
target = 9
```

Output

```text
4
```

---

## Example 2

Input

```text
nums   = [-1, 0, 3, 5, 9, 12]
target = 2
```

Output

```text
-1
```

---

# Constraints

```
1 <= nums.length <= 10^4
-10^4 < nums[i], target < 10^4
All integers in nums are unique.
nums is sorted in ascending order.
```

---

# Understanding the Problem

The array is sorted and all elements are unique. We need to find the exact position of `target`, or confirm it is absent.

A linear scan works (O(n)) but fails the O(log n) requirement. Binary search is the canonical solution.

---

# Brute Force — Linear Scan

```
for i in range(n):
    if nums[i] == target:
        return i
return -1
```

Time: O(n). Correct but does not satisfy the constraint.

---

# Optimized Approach — Binary Search (Template 1)

Maintain a window `[left, right]` (both inclusive). At each step:

1. Compute `mid = left + (right - left) / 2`.
2. If `nums[mid] == target` → found, return `mid`.
3. If `nums[mid] < target` → target is in the right half, set `left = mid + 1`.
4. If `nums[mid] > target` → target is in the left half, set `right = mid - 1`.
5. If `left > right` → not found, return `-1`.

### Algorithm

```
left  = 0
right = n - 1

while left <= right:
    mid = left + (right - left) // 2

    if nums[mid] == target:   return mid
    elif nums[mid] < target:  left  = mid + 1
    else:                     right = mid - 1

return -1
```

---

# Dry Run

Input

```
nums   = [-1, 0, 3, 5, 9, 12]
target = 9
```

---

```
left=0, right=5
  mid = 0 + (5-0)//2 = 2
  nums[2] = 3 < 9 → left = 3

left=3, right=5
  mid = 3 + (5-3)//2 = 4
  nums[4] = 9 == 9 → return 4
```

---

Input (not found)

```
nums   = [-1, 0, 3, 5, 9, 12]
target = 2
```

```
left=0, right=5, mid=2, nums[2]=3 > 2 → right=1
left=0, right=1, mid=0, nums[0]=-1 < 2 → left=1
left=1, right=1, mid=1, nums[1]=0 < 2 → left=2
left=2 > right=1 → return -1
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(log n) |
| Space | O(1) |

---

# Edge Cases

## Single Element, Found

```
nums = [5], target = 5 → 0
```

## Single Element, Not Found

```
nums = [5], target = 3 → -1
```

## Target Smaller than All

```
nums = [3, 5, 7], target = 1 → -1
```

## Target Larger than All

```
nums = [3, 5, 7], target = 9 → -1
```

---

# Interview Discussion

### Why `mid = left + (right - left) / 2` not `(left + right) / 2`?

When `left` and `right` are both large (near `INT_MAX`), their sum overflows a 32-bit integer. `left + (right - left) / 2` is mathematically equivalent but safe.

### What if the array has duplicates?

Template 1 still finds **an** occurrence. If you need the **first** or **last** occurrence, use Template 2 (left boundary) or Template 3 (right boundary) from the concept file.

---

# Common Mistakes

## Using `<` instead of `<=` in the loop condition

`while left < right` misses the element when the window collapses to a single index.

## Forgetting to update both `left` and `right`

Only updating one pointer creates an infinite loop.

---

# Key Takeaways

- Template 1: `left <= right`, `left = mid+1`, `right = mid-1`, return `-1` on exit.
- Overflow-safe mid: `left + (right - left) / 2`.
- O(log n) time, O(1) space.
- This is the foundation for every other binary search variant.

---

# Next Problem

➡ **Search Insert Position**

Applies Template 2 (left boundary) to find where a target would be inserted in a sorted array.
