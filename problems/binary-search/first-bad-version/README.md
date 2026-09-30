# First Bad Version

> Difficulty: **Easy**
> Topic: **Binary Search, Template 2 — Left Boundary on Boolean Space**
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

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have `n` versions `[1, 2, ..., n]` and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API `bool isBadVersion(version)` which returns whether `version` is bad.

Implement a function to find the **first bad version**. Minimise the number of calls to the API.

---

# Examples

## Example 1

Input

```text
n = 5, bad = 4
```

Output

```text
4
```

Explanation

```
isBadVersion(3) → false
isBadVersion(5) → true
isBadVersion(4) → true

First bad version is 4.
```

---

## Example 2

Input

```text
n = 1, bad = 1
```

Output

```text
1
```

---

# Constraints

```
1 <= bad <= n <= 2^31 - 1
```

---

# Understanding the Problem

The versions look like this:

```
Version:  1   2   3   4   5
Status:   G   G   G   B   B    (G=Good, B=Bad)
```

There is a **single transition** from Good to Bad. The sequence is monotonically non-decreasing in "badness". This is the perfect shape for Template 2 — left boundary.

We want the **first index where `isBadVersion(v)` is true**.

---

# Brute Force

Call `isBadVersion` on every version starting from `1`:

```
for v in range(1, n+1):
    if isBadVersion(v):
        return v
```

Time: O(n) API calls. For `n = 2^31 - 1`, this is ~2 billion calls.

---

# Optimized Approach — Left Boundary Binary Search

Apply Template 2 directly. The condition is `isBadVersion(mid)`.

```
left  = 1
right = n          ← versions are 1-indexed

while left < right:
    mid = left + (right - left) // 2

    if isBadVersion(mid):
        right = mid        ← mid could be first bad; preserve it
    else:
        left = mid + 1     ← mid is good; first bad is to the right

return left
```

**Number of API calls**: O(log n). For `n = 2^31 - 1`, approximately 31 calls.

---

# Dry Run

```
n = 5, bad = 4

Versions: G G G B B
          1 2 3 4 5

left=1, right=5
  mid=3, isBadVersion(3)=false → left=4

left=4, right=5
  mid=4, isBadVersion(4)=true  → right=4

left=4, right=4 → return 4  ✓
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(log n) |
| Space | O(1) |
| API calls | O(log n) — ~31 for n = 2³¹-1 |

---

# Edge Cases

## First version is bad

```
n = 5, bad = 1

left=1, right=5, mid=3, bad(3)=true → right=3
left=1, right=3, mid=2, bad(2)=true → right=2
left=1, right=2, mid=1, bad(1)=true → right=1
left=1, right=1 → return 1  ✓
```

## Last version is bad

```
n = 5, bad = 5

left=1, right=5, mid=3, bad(3)=false → left=4
left=4, right=5, mid=4, bad(4)=false → left=5
left=5, right=5 → return 5  ✓
```

## Only one version

```
n = 1, bad = 1 → return 1
```

---

# Interview Discussion

### Why not check `isBadVersion(n)` first?

You could — but in general you don't know in advance whether the last version is bad. The problem guarantees at least one bad version exists, but you don't know its position.

### What is the minimum and maximum number of API calls?

- Minimum: 1 call (when the bad version is exactly at the midpoint every time — rare).
- Maximum: `⌊log₂ n⌋ + 1` ≈ 31 for `n = 2^31 - 1`.

### Why `right = mid` instead of `right = mid - 1`?

Because `mid` might be the first bad version. Setting `right = mid - 1` would exclude the answer. We must keep mid in the window.

---

# Common Mistakes

## Setting `right = mid - 1` when condition is true

This discards the potential answer. Use `right = mid` to keep it.

## Overflow in mid when n approaches 2³¹ - 1

```
Wrong:
mid = (left + right) / 2    ← overflows for large left + right

Correct:
mid = left + (right - left) / 2
```

---

# Key Takeaways

- This is Template 2 in its purest form: no array values, just a boolean condition `isBadVersion`.
- The structure is identical to Search Insert Position — find the leftmost `True`.
- `right = mid` preserves the candidate; `left = mid + 1` eliminates a confirmed good version.
- O(log n) calls — crucial when the API call is expensive (e.g., runs a test suite).

---

# Next Problem

➡ **Search in Rotated Sorted Array**

Extends binary search to a non-trivially sorted array by determining which half is sorted at each step.
