# Binary Search Problems

> **Week 7** · Core concept: halving the search space on a sorted or monotonic input in O(log n).

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Binary Search | Easy | [binary-search/](binary-search/) |
| 2 | Search Insert Position | Easy | [search-insert-position/](search-insert-position/) |
| 3 | First Bad Version | Easy | [first-bad-version/](first-bad-version/) |
| 4 | Search in Rotated Sorted Array | Medium | [search-rotated-array/](search-rotated-array/) |

## Key Patterns
- **Classic binary search** — `lo <= hi`, `mid = lo + (hi-lo)/2`
- **Left-boundary search** — find first index satisfying a condition
- **Rotated array** — check which half is sorted, then decide which side to go
- **Search on answer space** — binary search the answer range, not the array

## Template (all 3 languages)
```java
int lo = 0, hi = arr.length - 1;
while (lo <= hi) { int mid = lo + (hi-lo)/2; ... }
```
```python
lo, hi = 0, len(arr) - 1
while lo <= hi:
    mid = (lo + hi) // 2
    ...
```
```go
lo, hi := 0, len(arr)-1
for lo <= hi { mid := lo + (hi-lo)/2; ... }
```

## Concepts
→ [concepts/binary-search.md](../../concepts/binary-search.md)

← [Back to problems](../README.md)
