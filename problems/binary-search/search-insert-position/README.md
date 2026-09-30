# Search Insert Position

> Difficulty: **Easy**
> Topic: **Binary Search, Template 2 — Left Boundary**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force
6. Optimized Approach — Binary Search Left Boundary
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

Given a sorted array of **distinct** integers `nums` and a `target` value, return the **index** if `target` is found. If not, return the index where it **would be inserted** in order.

You must write an algorithm with **O(log n)** runtime complexity.

---

# Examples

## Example 1

Input

```text
nums = [1, 3, 5, 6], target = 5
```

Output

```text
2
```

---

## Example 2

Input

```text
nums = [1, 3, 5, 6], target = 2
```

Output

```text
1
```

Explanation: `2` would be inserted between `1` (index 0) and `3` (index 1).

---

## Example 3

Input

```text
nums = [1, 3, 5, 6], target = 7
```

Output

```text
4
```

Explanation: `7` would be inserted after `6` (index 3), so insert at index 4.

---

## Example 4

Input

```text
nums = [1, 3, 5, 6], target = 0
```

Output

```text
0
```

---

# Constraints

```
1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums contains distinct values sorted in ascending order.
-10^4 <= target <= 10^4
```

---

# Understanding the Problem

This is asking for the **leftmost index** where `nums[i] >= target`.

- If `target` exists: that index is where it sits.
- If `target` does not exist: that index is where it belongs.

Both cases reduce to: **find the first index where `nums[i] >= target`**.

This is exactly **Template 2 — Left Boundary**.

---

# Brute Force

Linear scan from left, return the first index where `nums[i] >= target`:

```
for i in range(n):
    if nums[i] >= target:
        return i
return n
```

Time: O(n).

---

# Optimized Approach — Left Boundary Binary Search

Set `right = n` (one past the last index) to handle the case where `target` is larger than all elements.

```
left  = 0
right = n          ← n means "insert at end"

while left < right:
    mid = left + (right - left) // 2

    if nums[mid] >= target:
        right = mid        ← mid could be the answer
    else:
        left = mid + 1     ← mid is too small

return left
```

When the loop ends, `left == right` is the first index where `nums[left] >= target` (or `n` if none).

---

# Dry Run

## Target present

```
nums = [1, 3, 5, 6], target = 5

left=0, right=4
  mid=2, nums[2]=5 >= 5 → right=2

left=0, right=2
  mid=1, nums[1]=3 < 5  → left=2

left=2, right=2 → return 2  ✓
```

---

## Target absent — insert in middle

```
nums = [1, 3, 5, 6], target = 2

left=0, right=4
  mid=2, nums[2]=5 >= 2 → right=2

left=0, right=2
  mid=1, nums[1]=3 >= 2 → right=1

left=0, right=1
  mid=0, nums[0]=1 < 2  → left=1

left=1, right=1 → return 1  ✓ (insert between index 0 and 1)
```

---

## Target larger than all

```
nums = [1, 3, 5, 6], target = 7

left=0, right=4
  mid=2, nums[2]=5 < 7  → left=3

left=3, right=4
  mid=3, nums[3]=6 < 7  → left=4

left=4, right=4 → return 4  ✓ (insert at end)
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(log n) |
| Space | O(1) |

---

# Edge Cases

## Single element, target equals it

```
nums = [3], target = 3 → 0
```

## Single element, target smaller

```
nums = [3], target = 1 → 0
```

## Single element, target larger

```
nums = [3], target = 5 → 1
```

---

# Interview Discussion

### Why `right = n` instead of `right = n-1`?

If `target` is larger than every element, the answer is `n` (insert at the end). With `right = n-1` and a standard Template 1 loop, the loop would exit without returning `n`. Setting `right = n` lets the left-boundary template handle this naturally.

### How does this differ from Template 1?

Template 1 looks for an exact match and returns `-1` if not found. This problem never returns `-1` — there is always a valid insertion position. Template 2 collapses the window to a single answer point instead.

### Is this the same as `lower_bound` in C++?

Yes. Python's `bisect.bisect_left(nums, target)` returns the same value.

---

# Common Mistakes

## Using `right = n - 1`

Misses the case where `target > nums[n-1]`, causing the function to return the wrong index.

## Returning `right` instead of `left`

After the loop `left == right`. Either is fine, but be consistent. Most implementations return `left`.

---

# Key Takeaways

- This problem is "find the leftmost index where `nums[i] >= target`" — Template 2.
- Set `right = n` to handle insertion at the end.
- Loop condition `left < right`, update `right = mid` (not `mid-1`) to preserve the candidate.
- Equivalent to `bisect_left` in Python, `lower_bound` in C++.

---

# Next Problem

➡ **First Bad Version**

The same left-boundary template applied to a pure boolean condition — no array values needed.
