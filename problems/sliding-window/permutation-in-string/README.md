# Permutation in String

## Problem Statement

Given two strings `s1` and `s2`, return `true` if `s2` **contains a permutation** of `s1`, or `false` otherwise.

In other words, return `true` if one of `s1`'s permutations is a substring of `s2`.

**LeetCode:** [567. Permutation in String](https://leetcode.com/problems/permutation-in-string/)  
**Difficulty:** Medium  
**Topic Tags:** Hash Table, Two Pointers, String, Sliding Window

---

## Examples

### Example 1
```
Input:  s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
```

### Example 2
```
Input:  s1 = "ab", s2 = "eidboaoo"
Output: false
```

---

## Constraints

- `1 <= s1.length, s2.length <= 10⁴`
- `s1` and `s2` consist of lowercase English letters.

---

## Understanding the Problem

A permutation of `s1` is any rearrangement of its characters. We need to check whether any contiguous window of length `len(s1)` in `s2` contains exactly the same character frequencies as `s1`.

This is a **fixed-size sliding window** (size = `len(s1)`) combined with a **frequency map comparison**.

### Key Observations
1. All permutations of `s1` have the same character frequency map.
2. Any window of `s2` with length `len(s1)` that matches that frequency map is a permutation.
3. Maintain the frequency map incrementally as the window slides — O(1) per step.
4. Use a **match counter** to check equality in O(1) instead of comparing full maps.

### Visual
```
s1 = "ab",  s2 = "eidbaooo"
need = {'a':1, 'b':1},  window size = 2

Window "ei": {'e':1,'i':1} ≠ need
Window "id": {'i':1,'d':1} ≠ need
Window "db": {'d':1,'b':1} ≠ need
Window "ba": {'b':1,'a':1} == need  → true ✓
```

---

## Approach 1: Brute Force

**Idea:** Generate all permutations of `s1` and check if any is a substring of `s2`.

**Why it fails:** Number of permutations = `len(s1)!` which blows up immediately.

---

## Approach 2: Fixed Window with Full Map Compare

**Idea:** Slide a window of size `len(s1)` over `s2`. At each position, build the window's frequency map and compare with `s1`'s map.

```
need = freq(s1)
for i = 0 to len(s2)-len(s1):
    if freq(s2[i..i+len(s1)-1]) == need:
        return true
return false
```

**Time:** O(m × 26) where m = len(s2) — rebuilding the map each step is wasteful.

---

## Approach 3: Sliding Window + Match Counter (Optimal)

**Idea:**
1. Build `need[c]` = freq of `s1`. `required` = number of distinct chars in `s1`.
2. Slide a fixed window of size `k = len(s1)` over `s2`.
3. Maintain `have[c]` (freq in window) and `matches` (chars with `have[c] == need[c]`).
4. When adding `s2[right]`: if the count just reached the required amount, `matches++`.
5. When the window exceeds size `k`, remove `s2[right - k]`: if the count drops below required, `matches--`.
6. If `matches == required`, a permutation is found.

### Algorithm
```
k = len(s1)
need = freq map of s1
required = number of unique chars in s1
have = {}
matches = 0

for right = 0 to len(s2)-1:
    c = s2[right]
    have[c]++
    if c in need and have[c] == need[c]:
        matches++

    if right >= k:                       # window exceeded size k — remove leftmost
        left_c = s2[right - k]
        if left_c in need and have[left_c] == need[left_c]:
            matches--
        have[left_c]--

    if matches == required:
        return true

return false
```

### Dry Run
```
s1 = "ab",  s2 = "eidbaooo"
need = {'a':1,'b':1},  required = 2,  k = 2

right=0 'e': have={'e':1}, 'e' not in need, matches=0
right=1 'i': have+='i',    matches=0
  right(1) >= k(2)? No
right=2 'd': have+='d',    matches=0
  right(2) >= k: remove s2[0]='e' → have['e']=0, 'e' not in need
right=3 'b': have['b']=1==need['b'] → matches=1
  right(3) >= k: remove s2[1]='i' → 'i' not in need
right=4 'a': have['a']=1==need['a'] → matches=2
  right(4) >= k: remove s2[2]='d' → 'd' not in need
  matches(2) == required(2) → return true ✓
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force (permutations) | O(k! × m) | O(k) |
| Fixed window map compare | O(m × 26) | O(26) |
| Sliding window + match counter | O(m + n) | O(26) = O(1) |

Since the alphabet is fixed at 26 lowercase letters, space is effectively O(1).

---

## Edge Cases

| Case | Expected |
|---|---|
| `s1` longer than `s2` | `false` |
| `s1 == s2` | `true` |
| `s1` is a single character | Check if that character appears in `s2` |
| Repeated characters in `s1` ("aab") | Window must have ≥2 'a's and ≥1 'b' |
| `s2` is a single character | Only possible if `len(s1) == 1` and `s1 == s2` |

---

## Interview Discussion Points

1. **Why fixed-size window here but variable in Longest Substring?** — Permutation requires exactly `len(s1)` characters; adding more would no longer be a permutation.
2. **Why remove at `right - k` not `left`?** — We track only the right pointer; `right - k` is always the element that just left the window of size `k`.
3. **Could you compare the two frequency arrays directly?** — Yes, but that's O(26) per step instead of O(1) via match counter. Both are technically O(1) for fixed alphabet but the match counter is cleaner.
4. **Follow-up: find all starting indices of permutations?** → LeetCode 438 — same algorithm, collect all positions where `matches == required`.

---

## Common Mistakes

- Decrementing `matches` when removing a character not in `need` → never required.
- Off-by-one: using `right >= k` vs `right > k - 1` — they are equivalent but easy to confuse.
- Not checking `have[c] == need[c]` *after* incrementing (not before) when determining if `matches` should increase.
- Checking `matches == required` before removing the outgoing character.

---

## Key Takeaways

- Permutation in String = **fixed-size sliding window** + **frequency match counter**.
- The match counter converts map-equality from O(∣Σ∣) to O(1) per step.
- Direct sibling problems: Find All Anagrams in a String (LeetCode 438), Minimum Window Substring (LeetCode 76).
- The template here is the **cleaner sibling** of Minimum Window — same idea but fixed size means no inner shrink loop.

---

## Next Problem

➡️ [Back to Sliding Window Concepts](../../concepts/sliding-window.md)
