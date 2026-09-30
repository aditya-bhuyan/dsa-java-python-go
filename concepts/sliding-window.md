# Sliding Window

## Table of Contents

1. [Introduction](#introduction)
2. [Core Idea](#core-idea)
3. [Fixed-Size Window](#fixed-size-window)
4. [Variable-Size Window](#variable-size-window)
5. [Window with Frequency Map](#window-with-frequency-map)
6. [Complexity Analysis](#complexity-analysis)
7. [Language Implementations](#language-implementations)
8. [Common Patterns & Templates](#common-patterns--templates)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

The **Sliding Window** technique is an algorithmic pattern for problems that involve **contiguous subarrays or substrings**. Instead of recomputing results from scratch for every subarray (brute-force O(n²) or worse), a window is maintained that **slides** over the data, adding one element on the right and dropping one on the left — each element enters and leaves the window at most once, yielding O(n) time.

Sliding window is applicable when:
- The problem asks about a **contiguous** subarray/substring.
- There is a constraint (size, sum, character count, distinct elements, …) that can be **incrementally maintained** as the window moves.
- The answer depends on properties of the window (max, min, average, count, …).

---

## Core Idea

```
Array:  [ a  b  c  d  e  f  g ]
          ^-----------^          ← window [left, right]

Expand right → add arr[right]
Shrink left  → remove arr[left], left++
```

Two pointers `left` and `right` define the window `[left, right]`.  
- **Expand** by advancing `right`.  
- **Shrink** by advancing `left` when the window violates the constraint.  
- Each pointer moves forward only → every element is processed at most twice → **O(n)**.

---

## Fixed-Size Window

When the window size `k` is fixed, both pointers advance together after the window reaches size `k`.

### Algorithm Template

```
Initialize window (first k elements)
For right = k to n-1:
    add arr[right] to window
    remove arr[right - k] from window
    update answer
```

### Example — Maximum Average of k consecutive elements

```
nums = [1, 12, -5, -6, 50, 3],  k = 4

Window 1: [1, 12, -5, -6]  sum = 2   avg = 0.5
Window 2: [12, -5, -6, 50] sum = 51  avg = 12.75  ← max
Window 3: [-5, -6, 50, 3]  sum = 42  avg = 10.5

Answer: 12.75
```

### ASCII Visualization

```
Index:  0    1    2    3    4    5
Value:  1   12   -5   -6   50    3
        [←————k=4————→]               step 1
             [←————k=4————→]          step 2
                  [←————k=4————→]     step 3
```

### Time / Space

| | Complexity |
|---|---|
| Time | O(n) — each element added/removed once |
| Space | O(1) — only maintain running sum |

---

## Variable-Size Window

When the window size is not fixed but bounded by a constraint (e.g., "longest substring with at most k distinct characters", "minimum subarray with sum ≥ target"), the window grows and shrinks dynamically.

### Algorithm Template

```
left = 0
state = initial (sum=0, map={}, count=0, ...)
For right = 0 to n-1:
    add arr[right] to state
    while state violates constraint:
        remove arr[left] from state
        left++
    update answer with window [left, right]
```

### Example — Longest Substring Without Repeating Characters

```
s = "abcabcbb"

right=0: add 'a', window="a",    seen={'a':0}
right=1: add 'b', window="ab",   seen={'a':0,'b':1}
right=2: add 'c', window="abc",  seen={...,'c':2}  → len=3
right=3: add 'a', 'a' already in seen at index 0
         shrink: left = max(left, seen['a']+1) = 1
         update seen['a']=3, window="bca", len=3
right=4: add 'b', seen['b']=1 < left=1... left = max(1, 2) = 2
         window="cab", len=3
...

Answer: 3
```

### Key Subtlety — Two shrink strategies

**Strategy A (set/count):** Remove elements one by one from the left until the constraint is restored.  
**Strategy B (index map):** For no-repeating-character problems, jump `left` directly to `seen[char]+1` instead of loop-shrinking — O(n) with no inner loop overhead.

---

## Window with Frequency Map

Many string sliding window problems require tracking **character frequencies** in the window. A hash map (or fixed-size array for lowercase letters) stores `char → count`.

### Canonical Operations

```
# Expand: add s[right]
freq[s[right]]++

# Shrink: remove s[left]
freq[s[left]]--
if freq[s[left]] == 0:
    delete freq[s[left]]
left++
```

### Example — Permutation in String

**Question:** Does `s2` contain a permutation of `s1`?

```
s1 = "ab",  s2 = "eidbaooo"
need = {'a':1, 'b':1}

Window of size len(s1)=2:
  "ei" → {'e':1,'i':1} ≠ need
  "id" → {'i':1,'d':1} ≠ need
  "db" → {'d':1,'b':1} ≠ need
  "ba" → {'b':1,'a':1} == need  → true ✓
```

**Optimisation — match counter:**  
Track an integer `matches` = number of characters whose window count equals the required count. When `matches == len(s1_unique)`, the window is a permutation.

---

## Minimum Window Substring

Find the smallest window in `s` that contains all characters of `t`.

### Algorithm

```
need = frequency map of t
have = {}
matches = 0   ← chars fully satisfied in window
required = number of unique chars in t
answer = ""

left = 0
for right in range(len(s)):
    add s[right] to have
    if have[s[right]] == need[s[right]]:
        matches++
    while matches == required:
        update answer if window is smaller
        remove s[left] from have
        if have[s[left]] < need[s[left]]:
            matches--
        left++
```

### Dry Run

```
s = "ADOBECODEBANC",  t = "ABC"
need = {'A':1,'B':1,'C':1},  required = 3

Expand until matches=3:  "ADOBEC" (right=5)
Shrink: remove 'A'→ matches=2, left=1 → "DOBEC"
Expand: right=6 'O'→no help, right=7 'D', right=8 'E', right=9 'B'→
        right=10 'A'→matches=3, window="DOBECODEBA" ... track min
Shrink again: remove 'D'... keep going...
Eventually: "BANC" is the minimum window ✓
```

---

## Complexity Analysis

| Problem Type | Time | Space | Notes |
|---|---|---|---|
| Fixed-size window (sum/avg) | O(n) | O(1) | Sliding sum |
| Variable window (longest/shortest) | O(n) | O(k) | k = alphabet or distinct values |
| Frequency map window | O(n) | O(∣Σ∣) | Σ = alphabet size (26 for lowercase) |
| Minimum Window Substring | O(n + m) | O(∣Σ∣) | n=len(s), m=len(t) |

---

## Language Implementations

### Go

```go
// Fixed window — maximum sum of k elements
func maxSumFixed(nums []int, k int) int {
    sum := 0
    for i := 0; i < k; i++ { sum += nums[i] }
    best := sum
    for i := k; i < len(nums); i++ {
        sum += nums[i] - nums[i-k]
        if sum > best { best = sum }
    }
    return best
}

// Variable window — longest substring without repeating chars
func lengthOfLongestSubstring(s string) int {
    seen := make(map[byte]int)
    left, best := 0, 0
    for right := 0; right < len(s); right++ {
        if idx, ok := seen[s[right]]; ok && idx >= left {
            left = idx + 1
        }
        seen[s[right]] = right
        if right-left+1 > best { best = right-left+1 }
    }
    return best
}
```

### Java

```java
// Fixed window — maximum sum of k elements
public double findMaxAverage(int[] nums, int k) {
    int sum = 0;
    for (int i = 0; i < k; i++) sum += nums[i];
    int maxSum = sum;
    for (int i = k; i < nums.length; i++) {
        sum += nums[i] - nums[i - k];
        maxSum = Math.max(maxSum, sum);
    }
    return (double) maxSum / k;
}

// Variable window — longest substring without repeating chars
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> seen = new HashMap<>();
    int left = 0, best = 0;
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        if (seen.containsKey(c) && seen.get(c) >= left)
            left = seen.get(c) + 1;
        seen.put(c, right);
        best = Math.max(best, right - left + 1);
    }
    return best;
}
```

### Python

```python
# Fixed window — maximum average of k elements
def find_max_average(nums: list[int], k: int) -> float:
    window_sum = sum(nums[:k])
    best = window_sum
    for i in range(k, len(nums)):
        window_sum += nums[i] - nums[i - k]
        best = max(best, window_sum)
    return best / k

# Variable window — longest substring without repeating chars
def length_of_longest_substring(s: str) -> int:
    seen: dict[str, int] = {}
    left = best = 0
    for right, ch in enumerate(s):
        if ch in seen and seen[ch] >= left:
            left = seen[ch] + 1
        seen[ch] = right
        best = max(best, right - left + 1)
    return best
```

---

## Common Patterns & Templates

### Pattern 1 — Fixed Window

```
Initialize window of size k
Slide one step at a time: add right element, remove left element
Update answer each step
```

### Pattern 2 — Variable Window (Expand then Shrink)

```
left = 0
for right in 0..n-1:
    expand: include arr[right] in window state
    while window is invalid:
        shrink: exclude arr[left], left++
    update: record answer from current valid window
```

### Pattern 3 — Frequency Map + Match Counter

```
Build need[] from target string/pattern
matches = 0
for right in 0..n-1:
    add s[right]: freq[s[right]]++
    if freq[s[right]] == need[s[right]]: matches++
    while matches == required:
        record answer
        remove s[left]: freq[s[left]]--
        if freq[s[left]] < need[s[left]]: matches--
        left++
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Using O(n²) nested loop instead of two-pointer slide | Recognise "contiguous subarray" as sliding window trigger |
| Forgetting to shrink when window violates constraint | Always `while invalid: shrink` before recording answer |
| Off-by-one in fixed window removal (`arr[right-k]` vs `arr[right-k-1]`) | Diagram the window on paper; test with k=2 |
| Reinitialising the frequency map inside the loop | Keep it alive across iterations — only add/remove incrementally |
| Not handling the case where `left > right` after jump | Use `max(left, seen[ch]+1)` not just `seen[ch]+1` |
| Updating answer inside the shrink loop (misses optimal window) | Update answer *after* shrinking (or inside `while` for minimum window problems) |

---

## Problems Covered

| Problem | Type | Key Technique | LeetCode |
|---|---|---|---|
| Maximum Average Subarray I | Fixed window | Sliding sum | #643 |
| Longest Substring Without Repeating Chars | Variable window | Index map jump | #3 |
| Minimum Window Substring | Variable + freq map | Match counter + shrink | #76 |
| Permutation in String | Fixed window + freq map | Match counter | #567 |
