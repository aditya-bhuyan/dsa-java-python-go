# Minimum Window Substring

## Problem Statement

Given two strings `s` and `t` of lengths `m` and `n`, return the **minimum window substring** of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

**LeetCode:** [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)  
**Difficulty:** Hard  
**Topic Tags:** Hash Table, String, Sliding Window

---

## Examples

### Example 1
```
Input:  s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window is "BANC" which contains A, B, and C.
```

### Example 2
```
Input:  s = "a", t = "a"
Output: "a"
```

### Example 3
```
Input:  s = "a", t = "aa"
Output: ""
Explanation: Both 'a's of t must be in the window. Only one 'a' is in s.
```

---

## Constraints

- `m == s.length`
- `n == t.length`
- `1 <= m, n <= 10⁵`
- `s` and `t` consist of uppercase and lowercase English letters.

---

## Understanding the Problem

We need the **smallest contiguous window** in `s` that contains all characters (with correct frequencies) from `t`.

### Key Observations
1. Maintain a frequency map `need` (from `t`) and `have` (current window).
2. Track `matches` = number of characters in `t` whose required count is fully satisfied in the current window.
3. Expand right until `matches == required` (all of `t` is covered).
4. Then shrink left as much as possible while still maintaining `matches == required`.
5. Record the smallest such window.

### Visual
```
s = "ADOBECODEBANC"
t = "ABC"

Expand → "ADOBEC"  (matches A,B,C → valid)
Shrink ← remove 'A' → invalid, expand again
...
Minimum valid window found: "BANC"
```

---

## Approach 1: Brute Force

**Idea:** Check every substring of `s`. For each one, verify it contains all of `t`.

```
best = ""
for i = 0 to m-1:
    for j = i to m-1:
        if contains_all(s[i..j], t):
            if best == "" or j-i+1 < len(best):
                best = s[i..j]
return best
```

**Time:** O(m² × (m + n))  **Space:** O(∣Σ∣)  — far too slow.

---

## Approach 2: Sliding Window + Match Counter (Optimal)

**Idea:**
1. Build `need[c]` = frequency of each character in `t`.
2. `required` = number of *distinct* characters in `t`.
3. Expand `right`; when `have[s[right]] == need[s[right]]`, increment `matches`.
4. When `matches == required`, try to shrink `left`:
   - Update answer if this window is smaller.
   - Remove `s[left]`: if `have[s[left]]` drops below `need[s[left]]`, decrement `matches`.
   - Advance `left`.
5. Repeat until `right` reaches the end.

### Algorithm
```
need = freq map of t
required = len(set(t))
have = {}
matches = 0
left = 0
best = (∞, 0, 0)   # (length, left, right)

for right = 0 to m-1:
    c = s[right]
    have[c] = have.get(c, 0) + 1
    if c in need and have[c] == need[c]:
        matches++
    while matches == required:
        if right - left + 1 < best[0]:
            best = (right - left + 1, left, right)
        have[s[left]] -= 1
        if s[left] in need and have[s[left]] < need[s[left]]:
            matches--
        left++

return s[best[1]:best[2]+1] if best[0] != ∞ else ""
```

### Dry Run
```
s = "ADOBECODEBANC",  t = "ABC"
need = {'A':1,'B':1,'C':1},  required = 3

right=0 'A': have={'A':1}, matches=1
right=1 'D': have+D,  matches=1
right=2 'O': have+O,  matches=1
right=3 'B': have={'A':1,'B':1,...}, matches=2
right=4 'E': matches=2
right=5 'C': have['C']=1==need['C'] → matches=3

  Window "ADOBEC" (len=6) → best=(6,0,5)
  Shrink: remove s[0]='A' → have['A']=0 < need['A']=1 → matches=2, left=1

right=6 'O': matches=2
right=7 'D': matches=2
right=8 'E': matches=2
right=9 'B': have['B']=2, matches stays 2 (already had 1)

  Wait... have['B'] was 1, now 2; need['B']=1, have['B']==need → already counted

right=10 'A': have['A']=1==need → matches=3

  Window s[1..10]="DOBECODEBA" (len=10) → not better than 6

  Shrink: remove 'D', have['D']=0, 'D' not in need → matches=3, left=2
    Window s[2..10]="OBECODEBA" (len=9) → best still (6,0,5)
  Shrink: remove 'O' → matches=3, left=3
    ...continue shrinking until A or B or C would be lost...
  Remove 'B' (at left=3): have['B']=2→1, 1>=need['B']=1 → matches stays 3, left=4
  Remove 'E' → matches=3, left=5
  Remove 'C': have['C']=1→0 < need['C']=1 → matches=2, left=6

right=11 'N': matches=2
right=12 'C': have['C']=1 → matches=3

  Window s[6..12]="ODEBANC" (len=7) → not better than 6

  Shrink: remove 'O','D','E' → matches=3 still
  Remove 'B': have['B']=1→0 < need['B']=1 → matches=2, left=10

  Window at shrink-stop: s[9..12]="BANC" (len=4) → best=(4,9,12) ✓

End → return s[9:13] = "BANC" ✓
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | O(m² × (m+n)) | O(∣Σ∣) |
| Sliding Window | O(m + n) | O(∣Σ∣) |

---

## Edge Cases

| Case | Expected |
|---|---|
| `t` longer than `s` | `""` |
| `s == t` | `s` (the whole string) |
| `t` has duplicates (`"AAB"`) | Window must contain ≥2 A's and ≥1 B |
| No valid window exists | `""` |
| Multiple equally short windows | Return any one (first found is fine) |

---

## Interview Discussion Points

1. **Why track `matches` instead of comparing full maps?** — Full map comparison is O(∣Σ∣) per step, making the whole algorithm O(n × ∣Σ∣). The match counter reduces this to O(1) per step.
2. **Why `required = len(set(t))` not `len(t)`?** — We only need each *distinct* character's count to be satisfied, not each individual copy.
3. **Why shrink inside a `while` loop?** — We may be able to remove multiple left characters while still keeping all of `t` covered — each shrink step might reveal a new minimum.
4. **Follow-up: minimum window containing all distinct characters of `s`?** → Set `t = set(s)`.

---

## Common Mistakes

- Decrementing `matches` when removing a character that is not in `t` at all.
- Updating `best` *after* shrinking instead of *before* — misses the optimal window.
- Forgetting to check `s[left] in need` before decrementing `matches`.
- Using `len(t)` as `required` instead of `len(set(t))` when `t` has duplicate characters.

---

## Key Takeaways

- The **match counter pattern** converts an O(∣Σ∣) window-validity check into an O(1) check — essential for hard sliding window problems.
- Shrink aggressively inside the `while matches == required` loop; record answer before each shrink.
- This exact template (need / have / matches / required) solves Permutation in String, Find All Anagrams, and Sliding Window Maximum with minor modifications.

---

## Next Problem

➡️ [Permutation in String](../permutation-in-string/README.md)
