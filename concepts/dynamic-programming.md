# Dynamic Programming

## Table of Contents

1. [Introduction](#introduction)
2. [Two Conditions for DP](#two-conditions-for-dp)
3. [Top-Down vs Bottom-Up](#top-down-vs-bottom-up)
4. [Classic DP Patterns](#classic-dp-patterns)
5. [State Design](#state-design)
6. [Space Optimisation](#space-optimisation)
7. [Complexity Analysis](#complexity-analysis)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

**Dynamic Programming (DP)** solves problems by breaking them into **overlapping subproblems**, solving each subproblem once, and storing the result (**memoization** or **tabulation**) to avoid redundant computation.

The word "dynamic" here is a historical term from operations research (Richard Bellman, 1950s), not a programming language feature.

### Why DP?

Without DP, naive recursion re-computes the same sub-answers exponentially:

```
Fibonacci (naive):
fib(5) calls fib(4) and fib(3)
fib(4) calls fib(3) and fib(2)
fib(3) is computed TWICE — and exponentially more for large n
Time: O(2^n)

With DP (memoize or tabulate): Time O(n), Space O(n)
```

---

## Two Conditions for DP

A problem is amenable to DP if it has **both**:

### 1. Optimal Substructure
The optimal solution to the whole problem is built from optimal solutions to its subproblems.

> "The shortest path from A to C through B = shortest(A→B) + shortest(B→C)"

### 2. Overlapping Subproblems
The recursive solution solves the same subproblem more than once.

> fib(5) depends on fib(4) and fib(3); fib(4) also depends on fib(3).

**Contrast with Divide & Conquer:** Also uses optimal substructure, but subproblems are disjoint (Merge Sort never revisits the same sub-array).

---

## Top-Down vs Bottom-Up

### Top-Down (Memoization)

Start with the original problem, recurse, cache results:

```python
memo = {}
def fib(n):
    if n <= 1: return n
    if n in memo: return memo[n]      # cache hit
    memo[n] = fib(n-1) + fib(n-2)
    return memo[n]
```

✅ Natural recursion structure  
✅ Only computes subproblems that are actually needed  
❌ Function call overhead; risk of stack overflow for large n

### Bottom-Up (Tabulation)

Build the table from the smallest subproblems upward:

```python
def fib(n):
    if n <= 1: return n
    dp = [0] * (n + 1)
    dp[1] = 1
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]
```

✅ No recursion overhead  
✅ Easier to space-optimise  
❌ Must compute all subproblems even if some aren't needed

---

## Classic DP Patterns

### Pattern 1 — Linear DP (1D state)

State depends only on the previous few values.

```
# Fibonacci / Climbing Stairs
dp[i] = dp[i-1] + dp[i-2]

# House Robber
dp[i] = max(dp[i-1], dp[i-2] + nums[i])

# Maximum Subarray (Kadane's)
dp[i] = max(nums[i], dp[i-1] + nums[i])
```

---

### Pattern 2 — 2D DP (grid / two-sequence)

```
# Unique Paths in a grid
dp[i][j] = dp[i-1][j] + dp[i][j-1]

# Longest Common Subsequence
dp[i][j] = dp[i-1][j-1] + 1            if s1[i] == s2[j]
dp[i][j] = max(dp[i-1][j], dp[i][j-1]) otherwise

# Edit Distance
dp[i][j] = dp[i-1][j-1]                if s1[i] == s2[j]
dp[i][j] = 1 + min(dp[i-1][j],         insert
                   dp[i][j-1],          delete
                   dp[i-1][j-1])        replace
```

---

### Pattern 3 — Knapsack

**0/1 Knapsack:** Each item used at most once.
```
dp[i][w] = max(dp[i-1][w],             # skip item i
               dp[i-1][w-wt[i]] + val[i])  # take item i (if w >= wt[i])
```

**Unbounded Knapsack:** Each item usable any number of times.
```
dp[w] = max(dp[w], dp[w - wt[i]] + val[i])   # inner loop over items
```

**Coin Change** (unbounded variant):
```
dp[amount] = min coins to make 'amount'
dp[0] = 0
for coin in coins:
    for w = coin to amount:
        dp[w] = min(dp[w], dp[w - coin] + 1)
```

---

### Pattern 4 — Interval DP

State is a sub-interval `[i, j]`.

```
# Matrix Chain Multiplication / Burst Balloons
dp[i][j] = optimal cost for the sub-problem on interval [i, j]

for length = 2 to n:
    for i = 0 to n-length:
        j = i + length - 1
        for k = i to j-1:
            dp[i][j] = min/max(dp[i][j], dp[i][k] + dp[k+1][j] + cost(i,k,j))
```

---

### Pattern 5 — Bitmask DP

State is a bitmask representing a set of chosen elements.

```
# Traveling Salesman Problem (TSP)
dp[mask][i] = min cost to visit exactly the nodes in 'mask', ending at node i

dp[1<<start][start] = 0
for mask in range(1 << n):
    for i in range(n):
        if mask & (1<<i):
            for j in range(n):
                if not (mask & (1<<j)):
                    dp[mask|(1<<j)][j] = min(dp[mask|(1<<j)][j],
                                             dp[mask][i] + dist[i][j])
```

---

### Pattern 6 — DP on Trees

DP state is defined per subtree:
```
dp[node][0] = best answer for subtree rooted at node, NOT including node
dp[node][1] = best answer for subtree rooted at node, INCLUDING node
```

---

## State Design

The key to DP is defining the **state** clearly:

1. **What does `dp[i]` represent?** Write it as a sentence:  
   *"`dp[i]` = the minimum number of coins needed to make amount `i`"*

2. **What is the base case?** The simplest input for which we know the answer directly.

3. **What is the transition?** How does `dp[i]` depend on smaller states?

4. **What is the final answer?** `dp[n]`, `dp[m][n]`, `max(dp)`, etc.

---

## Space Optimisation

Many 2D DP problems can be reduced to O(n) or O(1) space:

```
# 2D LCS (O(m*n) space) → 1D rolling array (O(n) space)
prev = [0] * (n + 1)
for i in range(1, m + 1):
    curr = [0] * (n + 1)
    for j in range(1, n + 1):
        if s1[i-1] == s2[j-1]: curr[j] = prev[j-1] + 1
        else:                   curr[j] = max(prev[j], curr[j-1])
    prev = curr

# Fibonacci: O(n) → O(1) with two variables
a, b = 0, 1
for _ in range(n): a, b = b, a + b
return a
```

---

## Complexity Analysis

| Pattern | Time | Space (full) | Space (optimised) |
|---|---|---|---|
| 1D Linear DP | O(n) | O(n) | O(1) (2 variables) |
| 2D Grid/Sequence | O(m × n) | O(m × n) | O(n) (rolling row) |
| 0/1 Knapsack | O(n × W) | O(n × W) | O(W) |
| Interval DP | O(n³) | O(n²) | — |
| Bitmask DP | O(n² × 2ⁿ) | O(n × 2ⁿ) | — |

---

## Language Implementations

### Go — Coin Change

```go
func coinChange(coins []int, amount int) int {
    dp := make([]int, amount+1)
    for i := 1; i <= amount; i++ { dp[i] = amount + 1 } // ∞
    dp[0] = 0
    for _, c := range coins {
        for w := c; w <= amount; w++ {
            if dp[w-c]+1 < dp[w] { dp[w] = dp[w-c] + 1 }
        }
    }
    if dp[amount] > amount { return -1 }
    return dp[amount]
}
```

### Java — Longest Common Subsequence

```java
public int lcs(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m+1][n+1];
    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++)
            dp[i][j] = s1.charAt(i-1) == s2.charAt(j-1)
                ? dp[i-1][j-1] + 1
                : Math.max(dp[i-1][j], dp[i][j-1]);
    return dp[m][n];
}
```

### Python — 0/1 Knapsack

```python
def knapsack(weights: list[int], values: list[int], capacity: int) -> int:
    dp = [0] * (capacity + 1)
    for w, v in zip(weights, values):
        for cap in range(capacity, w - 1, -1):   # reverse to ensure 0/1
            dp[cap] = max(dp[cap], dp[cap - w] + v)
    return dp[capacity]
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Defining state ambiguously | Write out "`dp[i]` = ..." as a full sentence before coding |
| Wrong base case (e.g., `dp[0] = 0` when it should be impossible) | Think about what "empty" input means for your problem |
| Wrong traversal order in 0/1 knapsack (forward instead of reverse) | Reverse inner loop prevents using same item twice |
| Forgetting to handle "impossible" states (initialise to ∞ or -1) | Use `float('inf')` or `amount+1` as sentinel |
| Confusing top-down memo key types | Use tuples as dict keys in Python; arrays are not hashable |
| Stack overflow in top-down for large n | Use bottom-up tabulation or increase recursion limit |

---

## Problems Covered

| Problem | DP Pattern | LeetCode |
|---|---|---|
| Climbing Stairs | Linear DP | #70 |
| House Robber | Linear DP | #198 |
| Coin Change | Unbounded knapsack | #322 |
| Longest Common Subsequence | 2D DP | #1143 |
| Edit Distance | 2D DP | #72 |
| Unique Paths | 2D grid DP | #62 |
| Longest Increasing Subsequence | 1D DP + binary search | #300 |
| Partition Equal Subset Sum | 0/1 Knapsack | #416 |
| Word Break | Linear DP | #139 |
