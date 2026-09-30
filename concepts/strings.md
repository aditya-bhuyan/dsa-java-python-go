# Strings

## Table of Contents

1. [Introduction](#introduction)
2. [String Immutability & StringBuilder](#string-immutability--stringbuilder)
3. [Character Arrays](#character-arrays)
4. [HashMap for String Problems](#hashmap-for-string-problems)
5. [Frequency Counting](#frequency-counting)
6. [Common String Patterns](#common-string-patterns)
7. [Complexity Analysis](#complexity-analysis)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

Strings are sequences of characters. Most string problems reduce to one of four primitives:
1. **Building** strings efficiently (StringBuilder / character arrays)
2. **Comparing** characters or substrings
3. **Counting** character frequencies (HashMap or fixed-size array)
4. **Searching** for patterns (sliding window, two pointers, hashing)

Understanding how strings are stored and mutated in each language is essential for writing efficient string algorithms.

---

## String Immutability & StringBuilder

### Java

In Java, `String` is **immutable**. Every concatenation creates a new object:

```java
// BAD — O(n²) time: creates n new strings
String result = "";
for (String s : parts) result += s;

// GOOD — O(n) amortized with StringBuilder
StringBuilder sb = new StringBuilder();
for (String s : parts) sb.append(s);
String result = sb.toString();
```

`StringBuilder` maintains a mutable char array and doubles capacity on overflow (amortized O(1) per append).

### Python

Python strings are also **immutable**. Use a list to collect parts, then `"".join(parts)`:

```python
# BAD — O(n²) due to string copies
result = ""
for s in parts: result += s

# GOOD — O(n)
parts_list = []
for s in parts: parts_list.append(s)
result = "".join(parts_list)
```

### Go

Go strings are **immutable byte slices**. Use `strings.Builder` or byte slice conversion:

```go
// strings.Builder (Go 1.10+)
var sb strings.Builder
for _, s := range parts { sb.WriteString(s) }
result := sb.String()

// byte slice
buf := make([]byte, 0, totalLen)
for _, s := range parts { buf = append(buf, s...) }
result := string(buf)
```

---

## Character Arrays

When you need to **mutate** individual characters, convert the string to a character array first:

### Reverse a String

```python
# Python
chars = list(s)
left, right = 0, len(chars) - 1
while left < right:
    chars[left], chars[right] = chars[right], chars[left]
    left += 1; right -= 1
return ''.join(chars)
```

```java
// Java
char[] chars = s.toCharArray();
int left = 0, right = chars.length - 1;
while (left < right) {
    char tmp = chars[left]; chars[left] = chars[right]; chars[right] = tmp;
    left++; right--;
}
return new String(chars);
```

```go
// Go
chars := []byte(s)   // or []rune(s) for Unicode
left, right := 0, len(chars)-1
for left < right {
    chars[left], chars[right] = chars[right], chars[left]
    left++; right--
}
return string(chars)
```

### When to Use Character Arrays

- Reversing a string in-place
- Rotating characters
- Replacing or removing characters without creating intermediate strings
- Checking palindromes (compare from both ends)

---

## HashMap for String Problems

HashMaps are the go-to for string problems involving:
- **Anagram detection** — compare two frequency maps
- **Character mapping** — isomorphic strings, word pattern
- **Substring problems** — need / have counters (minimum window, permutation in string)

### Anagram Check

```python
def is_anagram(s: str, t: str) -> bool:
    return Counter(s) == Counter(t)

# Without Counter:
if len(s) != len(t): return False
freq = {}
for c in s: freq[c] = freq.get(c, 0) + 1
for c in t:
    if freq.get(c, 0) == 0: return False
    freq[c] -= 1
return True
```

### Isomorphic Strings

Two-way mapping: `s → t` and `t → s` must both be consistent:

```python
def is_isomorphic(s: str, t: str) -> bool:
    s_to_t, t_to_s = {}, {}
    for cs, ct in zip(s, t):
        if s_to_t.get(cs, ct) != ct or t_to_s.get(ct, cs) != cs:
            return False
        s_to_t[cs] = ct
        t_to_s[ct] = cs
    return True
```

---

## Frequency Counting

### With a Fixed-Size Array (Lowercase Letters Only)

For problems limited to `'a'`–`'z'` (26 characters), a fixed-size integer array is faster than a HashMap:

```python
# Build frequency array
freq = [0] * 26
for ch in s:
    freq[ord(ch) - ord('a')] += 1

# Compare two frequency arrays
def same_freq(s1: str, s2: str) -> bool:
    if len(s1) != len(s2): return False
    freq = [0] * 26
    for c1, c2 in zip(s1, s2):
        freq[ord(c1) - ord('a')] += 1
        freq[ord(c2) - ord('a')] -= 1
    return all(f == 0 for f in freq)
```

```java
// Java
int[] freq = new int[26];
for (char c : s.toCharArray()) freq[c - 'a']++;
```

```go
// Go
var freq [26]int
for _, c := range s { freq[c-'a']++ }
```

### With Counter / Collections

```python
from collections import Counter

freq = Counter(s)          # {'a': 3, 'b': 1, ...}
freq.most_common(3)        # top 3 most frequent chars
freq['z']                  # 0 if not present (Counter default)
```

---

## Common String Patterns

### Pattern 1 — Two Pointers (Palindrome Check)

```python
def is_palindrome(s: str) -> bool:
    left, right = 0, len(s) - 1
    while left < right:
        if s[left] != s[right]: return False
        left += 1; right -= 1
    return True
```

### Pattern 2 — Sliding Window (Substring Problems)

See [Sliding Window](./sliding-window.md) for full coverage. Key operations on the window's character map:

```python
# Expand: add s[right]
have[s[right]] = have.get(s[right], 0) + 1

# Shrink: remove s[left]
have[s[left]] -= 1
if have[s[left]] == 0: del have[s[left]]
left += 1
```

### Pattern 3 — Encode Canonical Form (Anagram Grouping)

```python
# Sort characters as canonical key
key = tuple(sorted(s))   # "eat" → ('a','e','t')

# Or frequency tuple for O(n) instead of O(n log n)
def canon(s):
    freq = [0] * 26
    for c in s: freq[ord(c)-ord('a')] += 1
    return tuple(freq)
```

### Pattern 4 — Character-at-a-time Comparisons

When processing two strings in parallel:

```python
for i, (c1, c2) in enumerate(zip(s1, s2)):
    if c1 != c2:
        # mismatch at position i
```

---

## Complexity Analysis

| Operation | Time | Notes |
|---|---|---|
| String concatenation (`+`) in loop | O(n²) | Creates new string each time |
| StringBuilder / join | O(n) | Amortized O(1) per append |
| Character access | O(1) | Array/byte access |
| Substring / slice | O(k) | Copies k characters |
| Frequency array (26 chars) | O(n) | One pass over string |
| Frequency HashMap | O(n) | Slightly higher constant than array |
| Sort characters | O(n log n) | For canonical anagram key |

---

## Language Implementations

### Go

```go
// Rune vs byte iteration
for i, ch := range s { /* ch is rune (Unicode) */ }
for i := 0; i < len(s); i++ { b := s[i]; /* b is byte */ }

// String to []byte for mutation
b := []byte(s)
b[0] = 'A'
s = string(b)

// Check character
strings.ContainsRune(s, 'a')
strings.Count(s, "ab")
strings.HasPrefix(s, "hello")

// Common operations
strings.ToLower(s)
strings.TrimSpace(s)
strings.Split(s, ",")
strings.Join(parts, ",")
```

### Java

```java
// Character access
char c = s.charAt(i);
int code = s.codePointAt(i);

// Substring
String sub = s.substring(start, end);   // O(end-start)

// Comparison
s.equals(t);
s.equalsIgnoreCase(t);
s.compareTo(t);   // lexicographic

// Char operations
Character.isLetter(c)
Character.isDigit(c)
Character.toLowerCase(c)
Character.getNumericValue(c)

// Conversion
s.toCharArray()
String.valueOf(charArray)
String.valueOf(42)          // int → String
Integer.parseInt("42")     // String → int
```

### Python

```python
# String as sequence
for ch in s: ...
s[i]                          # O(1) access
s[start:end]                  # O(end-start) slice
s[::-1]                       # reverse

# Char checks
ch.isalpha()
ch.isdigit()
ch.isalnum()
ch.lower()
ch.upper()

# Useful methods
s.split()                     # split by whitespace
s.strip()                     # trim
"".join(parts)                # build from list
s.count("ab")                 # count occurrences
s.replace("a", "b")          # replace (returns new string)
s.find("ab")                  # index or -1
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Mutating a string directly in Java/Python | Convert to `char[]` or `list` first |
| String concatenation in a loop → O(n²) | Use `StringBuilder.append()` or `''.join()` |
| Forgetting that Python slices copy | Slicing `s[i:j]` is O(j-i) — avoid inside tight loops |
| `s[i]` in Java returns `char` — comparing with `==` to String literals | Use `s.charAt(i) == 'a'` (char literal), not `s.charAt(i) == "a"` |
| Off-by-one in substring extraction | `s.substring(start, end)` in Java is [start, end) — end is exclusive |
| Using `ord(c) - ord('a')` without bounds check | Only for lowercase `'a'`–`'z'`; add assertion or `isalpha()` check |

---

## Problems Covered

| Problem | Pattern | LeetCode |
|---|---|---|
| Valid Anagram | Frequency count | #242 |
| Group Anagrams | HashMap + canonical key | #49 |
| Longest Palindromic Substring | Expand around center | #5 |
| Reverse String | Two-pointer in char array | #344 |
| Reverse Words in a String | Split + reverse | #151 |
| First Unique Character | Frequency count | #387 |
| Isomorphic Strings | Bidirectional HashMap | #205 |
| Palindrome Check | Two pointers | #125 |
