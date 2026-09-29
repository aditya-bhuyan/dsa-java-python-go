# Isomorphic Strings

## Problem Statement
Given two strings `s` and `t`, determine if they are **isomorphic**.

Two strings `s` and `t` are isomorphic if the characters in `s` can be replaced to get `t`.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

## Examples

### Example 1
```
Input: s = "egg", t = "add"
Output: true
Explanation: The occurrences of "e", "g", and "g" in "s" can be replaced by "a", "d", and "d" respectively.
```

### Example 2
```
Input: s = "foo", t = "bar"
Output: false
Explanation: The first "o" maps to "a" and the second "o" maps to "r" - contradiction.
```

### Example 3
```
Input: s = "badc", t = "baba"
Output: false
Explanation: "a" should map to "a" but also to "b" - contradiction.
```

## Constraints
- 1 ≤ s.length ≤ 5 × 10^4
- t.length == s.length
- s and t consist of any valid ASCII character

## Algorithms

### 1. HashMap Mapping (Optimal)
**Time:** O(n) | **Space:** O(1)

Create two maps to track character mappings:
- Map from s to t: ensures each char in s maps to exactly one char in t
- Map from t to s: ensures each char in t maps to exactly one char in s

**Advantage:** Clear logic, handles bijection requirement
**When to use:** Most readable solution

### 2. Pattern Transformation
**Time:** O(n) | **Space:** O(1)

Transform both strings to pattern form using first occurrence tracking.

**Advantage:** Single map required
**When to use:** Space optimization needed

### 3. Bidirectional Index Map
**Time:** O(n) | **Space:** O(1)

Track last seen indices of characters to ensure consistent mapping.

**Advantage:** Alternative approach
**When to use:** Different implementation style

## Edge Cases
- Single character: `"a"`, `"b"` → true
- Same string: `"egg"`, `"egg"` → true
- Different lengths handled by problem constraint
- All same characters: `"aaa"`, `"bbb"` → true
- Reverse order: `"ab"`, `"ba"` → true (if mapping is consistent)

## Key Insights
1. **Bijection requirement:** Must be one-to-one mapping in both directions
2. **Order preservation:** Character order must match
3. **HashMap tracking:** Need to track both directions to detect conflicts
4. **ASCII constraint:** Bounded character set

## Related Problems
- Valid Anagram (Strings topic)
- First Unique Character (HashMap-Set topic)
- Group Anagrams (Strings topic)
