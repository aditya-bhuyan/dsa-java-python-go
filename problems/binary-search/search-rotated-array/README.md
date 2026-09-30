# Search in Rotated Sorted Array

> Difficulty: **Medium**
> Topic: **Binary Search, Rotated Array**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force
6. Optimized Approach — Modified Binary Search
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

There is an integer array `nums` sorted in ascending order (with **distinct** values).

Prior to being passed to your function, `nums` is possibly **rotated** at an unknown pivot index `k` such that the resulting array is:

```
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
```

For example, `[0,1,2,4,5,6,7]` might be rotated at pivot 3 to become `[4,5,6,7,0,1,2]`.

Given the array `nums` and an integer `target`, return the **index** of `target` if it is in `nums`, or **-1** if it is not.

You must write an algorithm with **O(log n)** runtime complexity.

---

# Examples

## Example 1

Input

```text
nums = [4, 5, 6, 7, 0, 1, 2], target = 0
```

Output

```text
4
```

---

## Example 2

Input

```text
nums = [4, 5, 6, 7, 0, 1, 2], target = 3
```

Output

```text
-1
```

---

## Example 3

Input

```text
nums = [1], target = 0
```

Output

```text
-1
```

---

# Constraints

```
1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending sorted array possibly rotated.
-10^4 <= target <= 10^4
```

---

# Understanding the Problem

A rotated sorted array looks like two sorted halves joined at a rotation point:

```
Original: [0, 1, 2, 4, 5, 6, 7]
Rotated:  [4, 5, 6, 7, 0, 1, 2]
                   ↑
               rotation pivot
```

At any split point `mid`, **one of the two halves is always sorted**. We use this to determine which half contains `target`.

Key insight:

- If `nums[left] <= nums[mid]`: the **left half** is sorted.
  - If `target` falls within `[nums[left], nums[mid])`: search left.
  - Otherwise: search right.
- Else: the **right half** is sorted.
  - If `target` falls within `(nums[mid], nums[right]]`: search right.
  - Otherwise: search left.

---

# Brute Force

Linear scan:

```
for i in range(n):
    if nums[i] == target:
        return i
return -1
```

Time: O(n).

---

# Optimized Approach — Modified Binary Search

```
left  = 0
right = n - 1

while left <= right:
    mid = left + (right - left) // 2

    if nums[mid] == target:
        return mid

    if nums[left] <= nums[mid]:       ← left half is sorted

        if nums[left] <= target < nums[mid]:
            right = mid - 1           ← target in left sorted half
        else:
            left = mid + 1            ← target in right half

    else:                             ← right half is sorted

        if nums[mid] < target <= nums[right]:
            left = mid + 1            ← target in right sorted half
        else:
            right = mid - 1           ← target in left half

return -1
```

---

# Dry Run

## Example 1: `nums = [4,5,6,7,0,1,2]`, `target = 0`

```
left=0, right=6
  mid=3, nums[3]=7 ≠ 0
  nums[0]=4 <= nums[3]=7 → left half sorted [4,5,6,7]
  target=0 NOT in [4, 7) → left = 4

left=4, right=6
  mid=5, nums[5]=1 ≠ 0
  nums[4]=0 <= nums[5]=1 → left half sorted [0,1]
  target=0 in [0, 1) → right = 4

left=4, right=4
  mid=4, nums[4]=0 == 0 → return 4  ✓
```

---

## Example 2: `nums = [4,5,6,7,0,1,2]`, `target = 3`

```
left=0, right=6, mid=3 → nums[3]=7 ≠ 3
  left sorted [4..7], 3 not in [4,7) → left=4

left=4, right=6, mid=5 → nums[5]=1 ≠ 3
  left sorted [0..1], 3 not in [0,1) → right=4

left=4, right=4, mid=4 → nums[4]=0 ≠ 3
  left sorted [0..0], 3 not in [0,0) → left=5

left=5 > right=4 → return -1  ✓
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(log n) |
| Space | O(1) |

The search space halves every iteration despite the rotation.

---

# Edge Cases

## No rotation (already sorted)

```
nums = [1,2,3,4,5], target = 3 → 2
```

Works as standard binary search.

---

## Single element, found

```
nums = [1], target = 1 → 0
```

---

## Single element, not found

```
nums = [1], target = 0 → -1
```

---

## Target at rotation point

```
nums = [4,5,6,7,0,1,2], target = 4 → 0
```

---

# Interview Discussion

### Why does one half always remain sorted?

Rotating a sorted array creates at most one "break" in the ordering. Any midpoint splits the array into two halves; the break can only be in one of them. The other half is therefore fully sorted.

### How do you identify the sorted half?

Compare `nums[left]` to `nums[mid]`:
- `nums[left] <= nums[mid]` → no break in the left half → it is sorted.
- Otherwise → the break is in the left half → the right half is sorted.

### What changes if duplicates are allowed?

When `nums[left] == nums[mid]`, you cannot determine which half is sorted. You must increment `left` by 1 (skip the duplicate). This degrades worst-case to O(n) — covered in LeetCode 81.

---

# Common Mistakes

## Checking `nums[left] < nums[mid]` (strict) instead of `<=`

When `left == mid` (single element window), `nums[left] == nums[mid]`. Using strict `<` misclassifies it.

## Wrong target range check

```
Left half sorted. Target in left half:

Correct:   nums[left] <= target < nums[mid]
Wrong:     nums[left] <= target <= nums[mid]   ← includes mid which was already checked
```

## Not handling `nums[mid] == target` first

Always check the exact match before the sorted-half logic.

---

# Key Takeaways

- A rotated sorted array always has one sorted half at any `mid`.
- Determine the sorted half via `nums[left] <= nums[mid]`.
- Check if `target` falls in the sorted half's range; search accordingly.
- O(log n) time, O(1) space — despite the rotation.
- With duplicates, worst case degrades to O(n).
