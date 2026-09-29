# Palindrome

## Problem Statement

Given a string `s`, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

## Examples

```
Input: s = "A man, a plan, a canal: Panama"
Output: true
```

```
Input: s = "race a car"
Output: false
```

```
Input: s = " "
Output: true
```

## Algorithm: Two-Pointer with Filtering

- Iterate from both ends
- Skip non-alphanumeric characters
- Compare characters (case-insensitive)
- Time: O(n), Space: O(1)
