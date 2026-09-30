# Sliding Window Problems

> **Week 9** · Core concept: eliminate O(n²) brute-force on subarray/substring problems with an expanding/shrinking window in O(n).

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Maximum Average Subarray I | Easy | [maximum-average/](maximum-average/) |
| 2 | Longest Substring Without Repeating Characters | Medium | [longest-substring/](longest-substring/) |
| 3 | Minimum Window Substring | Hard | [minimum-window/](minimum-window/) |
| 4 | Permutation in String | Medium | [permutation-in-string/](permutation-in-string/) |

## Key Patterns
- **Fixed window** — slide a window of size `k`, subtract left element, add right element
- **Variable window** — expand `r` greedily, shrink `l` when invariant is violated
- **Frequency map** — track character counts inside the window
- **Match counter** — count how many characters have satisfied their required frequency

## Variable window template
```python
freq = defaultdict(int); l = res = 0
for r in range(len(s)):
    freq[s[r]] += 1
    while <window invalid>:
        freq[s[l]] -= 1
        if freq[s[l]] == 0: del freq[s[l]]
        l += 1
    res = max(res, r - l + 1)
```

## Concepts
→ [concepts/sliding-window.md](../../concepts/sliding-window.md)  
→ [concepts/two-pointers.md](../../concepts/two-pointers.md)

← [Back to problems](../README.md)
