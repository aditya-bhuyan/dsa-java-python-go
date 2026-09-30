# Longest Substring Without Repeating Characters

## Problem Statement

Given a string `s`, find the length of the **longest substring** without repeating characters.

**LeetCode:** [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)  
**Difficulty:** Medium  
**Topic Tags:** Hash Table, String, Sliding Window

---

## Examples

### Example 1
```
Input:  s = "abcabcbb"
Output: 3
Explanation: The answer is "abc" with length 3.
```

### Example 2
```
Input:  s = "bbbbb"
Output: 1
Explanation: The answer is "b" with length 1.
```

### Example 3
```
Input:  s = "pwwkew"
Output: 3
Explanation: The answer is "wke" with length 3.
Note: "pwke" is a subsequence — not a substring.
```

---

## Constraints

- `0 <= s.length <= 5 × 10⁴`
- `s` consists of English letters, digits, symbols and spaces.

---

## Understanding the Problem

We need the longest **contiguous** portion of `s` where every character appears exactly once.

### Key Observations
1. Maintain a window `[left, right]` where all characters are unique.
2. When `s[right]` already exists in the window, the window is invalid — shrink from the left until the duplicate is gone.
3. **Optimisation:** Instead of shrinking one step at a time, jump `left` directly past the previous occurrence of the duplicate character using an index map.

---

## Approach 1: Brute Force

**Idea:** For every pair `(i, j)`, check if `s[i..j]` has all unique characters.

```
best = 0
for i = 0 to n-1:
    for j = i to n-1:
        if allUnique(s[i..j]):
            best = max(best, j-i+1)
return best
```

**Time:** O(n²) to O(n³)  **Space:** O(min(n, ∣Σ∣))

---

## Approach 2: Sliding Window with Set

**Idea:** Use a set to track characters in the current window. When a duplicate is found, remove from the left.

```
left = 0, seen = {}
for right = 0 to n-1:
    while s[right] in seen:
        seen.remove(s[left])
        left++
    seen.add(s[right])
    best = max(best, right - left + 1)
```

**Time:** O(n) amortised — each character enters/leaves set at most once.  
**Space:** O(min(n, ∣Σ∣))

---

## Approach 3: Sliding Window with Index Map (Optimal)

**Idea:** Replace the set with a map `char → last-seen index`. When a duplicate is found, jump `left` directly to `seen[char] + 1` — no inner while loop needed.

```
seen = {}
left = best = 0
for right = 0 to n-1:
    if s[right] in seen AND seen[s[right]] >= left:
        left = seen[s[right]] + 1
    seen[s[right]] = right
    best = max(best, right - left + 1)
return best
```

**Critical guard:** `seen[s[right]] >= left` — if the previous occurrence is already outside the window, no need to shrink.

### Dry Run
```
s = "abcabcbb"
seen = {},  left = 0,  best = 0

right=0 'a': not seen → seen={'a':0}, best=1
right=1 'b': not seen → seen={..,'b':1}, best=2
right=2 'c': not seen → seen={..,'c':2}, best=3
right=3 'a': seen['a']=0 >= left=0 → left=1
             seen['a']=3, window="bca", best=3
right=4 'b': seen['b']=1 >= left=1 → left=2
             seen['b']=4, window="cab", best=3
right=5 'c': seen['c']=2 >= left=2 → left=3
             seen['c']=5, window="abc", best=3
right=6 'b': seen['b']=4 >= left=3 → left=5
             seen['b']=6, window="cb", best=3
right=7 'b': seen['b']=6 >= left=5 → left=7
             seen['b']=7, window="b", best=3

Answer: 3 ✓
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²)–O(n³) | O(min(n, ∣Σ∣)) |
| Sliding + Set | O(n) | O(min(n, ∣Σ∣)) |
| Sliding + Index Map | O(n) | O(min(n, ∣Σ∣)) |

---

## Edge Cases

| Case | Expected |
|---|---|
| Empty string `""` | 0 |
| Single character `"a"` | 1 |
| All same characters `"aaaa"` | 1 |
| All unique characters `"abcd"` | n |
| Space and special characters | Handled — map works for any char |

---

## Interview Discussion Points

1. **Set vs index map?** — Index map avoids the inner shrink loop; both are O(n) but index map is faster in practice.
2. **Why `seen[ch] >= left`?** — Without this guard, a character seen before the current window would incorrectly push `left` backwards (or past `right`).
3. **What is the alphabet size?** — For lowercase only: 26. For all ASCII: 128. For Unicode: use a hash map.
4. **Follow-up: at most k distinct characters?** — Change constraint; use a count map and shrink when distinct > k.

---

## Common Mistakes

- Forgetting the `>= left` guard on the index map → incorrect `left` jumps.
- Initialising `best = 1` instead of `0` → fails for empty string.
- Using `s[right - left + 1]` instead of `right - left + 1` for window length calculation.

---

## Key Takeaways

- Variable-size sliding window: expand right, shrink left only when the constraint (all unique) is violated.
- The index map jump is the canonical O(n) trick for "no-repeat" substring problems.
- Pattern reuse: same skeleton powers "longest substring with at most k distinct chars", "longest repeating char replacement", etc.

---

## Next Problem

➡️ [Minimum Window Substring](../minimum-window/README.md)
