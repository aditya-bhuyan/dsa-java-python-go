# Valid Anagram

## Problem Statement

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An **anagram** is a word or phrase formed by rearranging the letters of another, typically using all the original letters exactly once.

## Problem Details

- **Input:** Two strings s and t
- **Output:** Boolean indicating if t is an anagram of s
- **Time Complexity:** O(n) - optimal approach
- **Space Complexity:** O(1) or O(k) depending on approach

## Examples

### Example 1
```
Input: s = "anagram", t = "nagaram"
Output: true
Explanation: Both have same characters with same frequencies
```

### Example 2
```
Input: s = "rat", t = "car"
Output: false
Explanation: Different characters
```

### Example 3
```
Input: s = "abc", t = "def"
Output: false
Explanation: No common characters
```

## Algorithm Approaches

### Approach 1: Sorting (Simple but less optimal)
- **Algorithm:** Sort both strings and compare
- **Time:** O(n log n)
- **Space:** O(1) or O(n) depending on sort implementation

### Approach 2: HashMap/Frequency Count (Optimal)
- **Algorithm:** Count character frequencies and compare
- **Time:** O(n) - single pass
- **Space:** O(1) or O(k) where k = unique characters (max 26 for lowercase)
- **Recommended:** ✅ Best practice

### Approach 3: Fixed Array for Lowercase Letters
- **Algorithm:** Use array of size 26 for character frequencies
- **Time:** O(n)
- **Space:** O(1) - fixed array size
- **Note:** Most efficient for limited character set

## Key Observations

1. If lengths differ, cannot be anagrams
2. Character frequency must be identical
3. Order doesn't matter, only character counts
4. Case sensitivity matters (unless specified otherwise)

## Edge Cases

- Empty strings: true (both empty)
- Single character: true if same, false otherwise
- Different lengths: always false
- Case sensitivity: 'A' ≠ 'a' by default
- Special characters and spaces count
