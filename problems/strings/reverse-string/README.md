# Reverse String

## Problem Statement

Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this **in-place** with O(1) extra memory.

## Examples

```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

```
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

## Algorithm: Two-Pointer Technique

- Use left pointer at start, right pointer at end
- Swap characters until pointers meet
- Time: O(n), Space: O(1)
