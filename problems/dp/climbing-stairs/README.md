# Climbing Stairs

## Problem Statement

You are climbing a staircase. It takes `n` steps to reach the top. Each time you can either climb **1 or 2 steps**. In how many distinct ways can you climb to the top?

**LeetCode:** [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)  
**Difficulty:** Easy  
**Topic Tags:** Math, Dynamic Programming, Memoization

---

## Examples

### Example 1
```
Input:  n = 2
Output: 2
Explanation: Two ways — (1+1) or (2).
```

### Example 2
```
Input:  n = 3
Output: 3
Explanation: Three ways — (1+1+1), (1+2), (2+1).
```

---

## Constraints

- `1 <= n <= 45`

---

## Understanding the Problem

To reach step `n`, you arrived from either step `n-1` (by taking 1 step) or step `n-2` (by taking 2 steps). Therefore:

```
ways(n) = ways(n-1) + ways(n-2)
```

This is exactly the **Fibonacci recurrence**. The number of ways to climb `n` stairs equals the (n+1)-th Fibonacci number.

### Visual
```
n=4:

Paths ending with "+1" from step 3:   f(3) = 3
  1+1+1+1
  1+2+1
  2+1+1

Paths ending with "+2" from step 2:   f(2) = 2
  1+1+2
  2+2

Total: f(3) + f(2) = 3 + 2 = 5
```

---

## Approach 1: Brute Force Recursion

**Idea:** Recursively compute `f(n) = f(n-1) + f(n-2)` with no caching.

```
f(n):
    if n <= 1: return 1
    return f(n-1) + f(n-2)
```

**Time:** O(2ⁿ) — exponential re-computation of same subproblems.  
**Space:** O(n) recursion stack.

---

## Approach 2: Top-Down DP (Memoization)

Cache results to avoid re-computation:

```
memo = {}
f(n):
    if n <= 1: return 1
    if n in memo: return memo[n]
    memo[n] = f(n-1) + f(n-2)
    return memo[n]
```

**Time:** O(n) — each value computed once.  
**Space:** O(n) — memo table + recursion stack.

### Dry Run (n=5)
```
f(5)
  f(4)
    f(3)
      f(2)
        f(1) = 1
        f(0) = 1
      f(2) = 2    ← memo hit on second call to f(2)
    f(3) = 3
  f(4) = 5       ← built bottom-up via cache
f(5) = 8 ✓
```

---

## Approach 3: Bottom-Up DP (Tabulation)

Build the table from base cases upward:

```
dp[0] = 1   (1 way to stay at ground)
dp[1] = 1   (1 way to reach step 1: one "+1")
for i = 2 to n:
    dp[i] = dp[i-1] + dp[i-2]
return dp[n]
```

### Dry Run (n=5)
```
dp[0] = 1
dp[1] = 1
dp[2] = dp[1] + dp[0] = 1 + 1 = 2
dp[3] = dp[2] + dp[1] = 2 + 1 = 3
dp[4] = dp[3] + dp[2] = 3 + 2 = 5
dp[5] = dp[4] + dp[3] = 5 + 3 = 8

Answer: 8 ✓
```

### DP Table Visualisation
```
Step:  0  1  2  3  4  5
Ways:  1  1  2  3  5  8
           ↑  ↑
        each = sum of previous two
```

---

## Approach 4: Space-Optimised DP (O(1) Space)

We only ever need the last two values — no array needed:

```
prev2 = 1   (dp[i-2])
prev1 = 1   (dp[i-1])
for i = 2 to n:
    curr = prev1 + prev2
    prev2 = prev1
    prev1 = curr
return prev1
```

**Time:** O(n)  **Space:** O(1)

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force Recursion | O(2ⁿ) | O(n) |
| Top-Down (Memoization) | O(n) | O(n) |
| Bottom-Up (Tabulation) | O(n) | O(n) |
| Space-Optimised | O(n) | O(1) |

---

## Edge Cases

| Case | Expected |
|---|---|
| `n = 1` | 1 |
| `n = 2` | 2 |
| `n = 45` | 1,836,311,903 (fits in 32-bit int) |

---

## Interview Discussion Points

1. **Why does this equal Fibonacci?** — The recurrence `f(n) = f(n-1) + f(n-2)` with `f(0)=f(1)=1` is the Fibonacci sequence shifted by one index.
2. **Can you do it in O(log n)?** — Yes: matrix exponentiation `[[1,1],[1,0]]^n` gives Fibonacci in O(log n) multiplications.
3. **What if you can take 1, 2, or 3 steps?** — Generalise: `dp[i] = dp[i-1] + dp[i-2] + dp[i-3]`. Keep last 3 values.
4. **Why space-optimised is preferred in interviews?** — Demonstrates understanding that the full array is unnecessary when only a fixed-size window of previous values is needed.

---

## Common Mistakes

- Using `dp[0] = 0` instead of `dp[0] = 1` — there is 1 way to "be at the bottom" (do nothing).
- Not initialising both `dp[0]` and `dp[1]` before the loop, causing index-out-of-bounds at `i=2`.
- Confusing the problem with Fibonacci indexing (`fib(n)` vs `fib(n+1)`).

---

## Key Takeaways

- Climbing Stairs is the **"Hello World" of DP** — it demonstrates memoization, tabulation, and space optimisation on the simplest possible recurrence.
- Recognising that `f(n) = f(n-1) + f(n-2)` is Fibonacci is the key insight.
- The O(1) space optimisation pattern — "replace array with two rolling variables" — generalises to any fixed-window DP recurrence.

---

## Next Problem

➡️ [House Robber](../house-robber/README.md)
