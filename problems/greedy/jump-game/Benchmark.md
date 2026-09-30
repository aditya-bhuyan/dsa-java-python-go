# Benchmark — Jump Game

## Algorithm Used

**Greedy — Track Maximum Reach** — single left-to-right scan; maintain `maxReach = max index reachable so far`. If the current index exceeds `maxReach`, return `false`. Otherwise update `maxReach = max(maxReach, i + nums[i])`.

---

## Complexity

| Metric | Value | Reason |
|---|---|---|
| Time | O(n) | Single pass, O(1) per step |
| Space | O(1) | Only one integer variable `maxReach` |

---

## Language Implementations

| Language | Strategy | Notes |
|---|---|---|
| Go | Greedy scan | Simple range loop |
| Java | Greedy scan | Standard for-loop |
| Python | Greedy scan | `enumerate` loop |

---

## Expected Relative Performance

```
Go  > Java  > Python
```

All three are O(n) O(1); Go is fastest due to no interpreter overhead.

---

## Benchmark Results (Indicative — n = 10⁴)

| Language | Approx Time |
|---|---|
| Go | ~0.01 ms |
| Java | ~0.05 ms |
| Python | ~0.3 ms |

---

## References

- [LeetCode 55 — Jump Game](https://leetcode.com/problems/jump-game/)
- [LeetCode 45 — Jump Game II](https://leetcode.com/problems/jump-game-ii/) (follow-up: minimum jumps)

---

## Revision History

| Date | Author | Notes |
|---|---|---|
| 2026-07-29 | Aditya Bhuyan | Initial version |
