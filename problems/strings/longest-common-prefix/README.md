# Longest Common Prefix

## Problem Statement

Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string `""`.

## Examples

```
Input: strs = ["flower","flow","flight"]
Output: "fl"
```

```
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

## Algorithm Approaches

1. **Horizontal Scanning** - Compare strings pairwise: O(S) where S = sum of all chars
2. **Vertical Scanning** - Compare character by character: O(S) average case
3. **Binary Search** - Find shortest string first: O(S log m) where m = min length

## Time Complexity: O(n * m) where n = number of strings, m = min length
