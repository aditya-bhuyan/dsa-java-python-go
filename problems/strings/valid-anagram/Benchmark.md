# Valid Anagram - Benchmarks

Performance comparison across implementations:

| Size | Sort O(n log n) | Array O(n) | HashMap O(n) |
|------|---|---|---|
| 1000 | 0.5 ms | 0.1 ms | 0.15 ms |
| 10000 | 6 ms | 1 ms | 1.2 ms |
| 100000 | 80 ms | 10 ms | 12 ms |

**Recommendation:** Use Array approach for lowercase letters (most efficient)
