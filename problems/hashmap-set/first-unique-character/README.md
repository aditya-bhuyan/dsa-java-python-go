# First Unique Character in a String

## Problem Statement
Given a string `s`, find the **first non-repeating character** in it and return its **index**. If the string does not contain any non-repeating character, return `-1`.

## Examples

### Example 1
```
Input: s = "leetcode"
Output: 0
Explanation: 'l' appears only once in "leetcode", and it appears first at index 0.
```

### Example 2
```
Input: s = "loveleetcode"
Output: 2
Explanation: 'l' appears at index 0, 'o' appears at index 1, 'v' appears at index 2 - 'v' is the first unique.
```

### Example 3
```
Input: s = "aabb"
Output: -1
Explanation: No character appears only once.
```

## Constraints
- 1 ≤ s.length ≤ 10^5
- s consists of only lowercase English letters

## Algorithms

### 1. HashMap Count + Index (Optimal)
**Time:** O(n) | **Space:** O(1)

First pass: Count frequency of each character in a HashMap.
Second pass: Iterate through string again and return index of first character with count = 1.

**Advantage:** Simple two-pass solution
**When to use:** Most common, clear logic

### 2. LinkedHashMap (Insertion Order)
**Time:** O(n) | **Space:** O(1)

Use a LinkedHashMap (Python 3.7+ dict) that maintains insertion order. Remove characters as we find duplicates.

**Advantage:** Maintains order automatically
**When to use:** Language supports ordered maps

### 3. Early Termination with Array
**Time:** O(n) | **Space:** O(1)

Use a fixed array of 26 elements (lowercase letters). Mark indices when seeing repeated characters.

**Advantage:** Ultra-fast, no HashMap overhead
**When to use:** Lowercase English letters only

## Edge Cases
- All unique: `"abc"` → 0 (first char)
- All duplicates: `"aabb"` → -1
- Single character: `"a"` → 0
- Two different chars: `"ab"` → 0
- Unique at end: `"aabbc"` → 4 ('c')

## Key Insights
1. **Two-pass approach:** Count first, then find
2. **Order preservation:** Need to track first occurrence
3. **HashMap/Array trade-off:** Array faster but limited to alphabet
4. **Character frequency:** Only interested in count = 1

## Related Problems
- Valid Anagram (Strings topic)
- Group Anagrams (Strings topic)
- Contains Duplicate (HashMap-Set topic)
- Isomorphic Strings (HashMap-Set topic)
