# Coin Change

## Problem Statement

You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.

Return the **fewest number of coins** that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return `-1`.

You may assume that you have an **infinite number of each kind of coin**.

**LeetCode:** [322. Coin Change](https://leetcode.com/problems/coin-change/)  
**Difficulty:** Medium  
**Topic Tags:** Array, Dynamic Programming, Breadth-First Search

---

## Examples

### Example 1
```
Input:  coins = [1, 5, 10, 25], amount = 36
Output: 3
Explanation: 25 + 10 + 1 = 36 (3 coins).
```

### Example 2
```
Input:  coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 5 + 5 + 1 = 11 (3 coins).
```

### Example 3
```
Input:  coins = [2], amount = 3
Output: -1
Explanation: Cannot make 3 using only denomination 2.
```

---

## Constraints

- `1 <= coins.length <= 12`
- `1 <= coins[i] <= 2³¹ - 1`
- `0 <= amount <= 10⁴`

---

## Understanding the Problem

This is an **unbounded knapsack** variant. Each coin can be used unlimited times. We want to minimise the number of coins to reach `amount`.

### Key Insight

For each amount `a`, the minimum coins needed is:

```
dp[a] = min over all coins c where c <= a:
            dp[a - c] + 1
```

"To make amount `a` using coin `c`, I need whatever it takes to make `a-c`, plus 1 more coin."

### Why Greedy Fails

Greedy (always use the largest coin ≤ remaining amount) fails for arbitrary denominations:

```
coins = [1, 3, 4],  amount = 6
Greedy: 4 + 1 + 1 = 3 coins
Optimal: 3 + 3     = 2 coins ✓
```

---

## Approach 1: Brute Force Recursion

Try every coin at every step, take the minimum:

```
min_coins(amount):
    if amount == 0: return 0
    if amount < 0:  return ∞
    best = ∞
    for coin in coins:
        result = min_coins(amount - coin)
        if result != ∞: best = min(best, result + 1)
    return best
```

**Time:** O(amount^(amount/min_coin)) — exponential re-computation.

---

## Approach 2: Top-Down DP (Memoization)

Cache each amount's result:

```
memo = {0: 0}
min_coins(amount):
    if amount in memo: return memo[amount]
    best = ∞
    for coin in coins:
        if coin <= amount:
            res = min_coins(amount - coin)
            if res != ∞: best = min(best, res + 1)
    memo[amount] = best
    return best
```

**Time:** O(amount × len(coins))  **Space:** O(amount) memo + O(amount) stack.

---

## Approach 3: Bottom-Up DP (Tabulation) — Optimal

**Idea:** Fill `dp[0..amount]` from left to right. `dp[a]` = min coins to make amount `a`.

```
dp = [∞] * (amount + 1)
dp[0] = 0            # base case: 0 coins to make amount 0

for a = 1 to amount:
    for coin in coins:
        if coin <= a and dp[a - coin] + 1 < dp[a]:
            dp[a] = dp[a - coin] + 1

return dp[amount] if dp[amount] != ∞ else -1
```

### Why iterate coins in the inner loop?

For each amount `a`, we try every coin and pick the best. Since coins can be reused (unbounded), we always look back to `dp[a - coin]` which was already computed.

### Dry Run (coins=[1,2,5], amount=11)
```
dp = [0, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞, ∞]
       0   1   2   3   4   5   6   7   8   9  10  11

a=1:  coin=1: dp[0]+1=1 → dp[1]=1
a=2:  coin=1: dp[1]+1=2; coin=2: dp[0]+1=1 → dp[2]=1
a=3:  coin=1: dp[2]+1=2; coin=2: dp[1]+1=2 → dp[3]=2
a=4:  coin=1: dp[3]+1=3; coin=2: dp[2]+1=2 → dp[4]=2
a=5:  coin=1: dp[4]+1=3; coin=2: dp[3]+1=3; coin=5: dp[0]+1=1 → dp[5]=1
a=6:  coin=1: dp[5]+1=2; coin=2: dp[4]+1=3; coin=5: dp[1]+1=2 → dp[6]=2
a=7:  dp[7] = 2   (5+2 or 5+1+1)
a=8:  dp[8] = 3
a=9:  dp[9] = 3
a=10: dp[10]= 2   (5+5)
a=11: coin=1: dp[10]+1=3; coin=2: dp[9]+1=4; coin=5: dp[6]+1=3 → dp[11]=3

Answer: 3 ✓  (5+5+1)
```

### DP Table Visualisation
```
Amount: 0  1  2  3  4  5  6  7  8  9 10 11
Coins:  0  1  1  2  2  1  2  2  3  3  2  3
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | Exponential | O(amount) |
| Top-Down (Memoization) | O(amount × n) | O(amount) |
| Bottom-Up (Tabulation) | O(amount × n) | O(amount) |

`n` = number of coin denominations.

---

## Edge Cases

| Case | Expected |
|---|---|
| `amount = 0` | 0 |
| No combination possible `[2], 3` | -1 |
| Single coin matches exactly `[5], 5` | 1 |
| All coins larger than amount | -1 |
| Coin denomination = 1 (always solvable) | `amount` |

---

## Interview Discussion Points

1. **Why not greedy?** — Greedy works only for canonical coin systems (US denominations). For arbitrary denominations, greedy is incorrect.
2. **Unbounded vs 0/1 knapsack?** — Here each coin is reusable (unbounded). In 0/1 knapsack, each item used at most once. The difference: unbounded iterates inner loop forward; 0/1 iterates backward.
3. **How does BFS solve this?** — Model it as a shortest-path problem: each amount is a node; edges go from `a` to `a + coin`. BFS from 0 finds the shortest path to `amount`.
4. **What is the time complexity really?** — O(amount × k) where k = number of coin types. For the given constraints (amount ≤ 10⁴, coins ≤ 12) this is at most ~120,000 operations.

---

## Common Mistakes

- Initialising `dp[0] = 0` but forgetting to set `dp[1..amount] = ∞` (or `amount+1` as sentinel).
- Returning `dp[amount]` without checking if it's still `∞` (meaning no solution exists).
- Using `0/1 knapsack` order (reverse inner loop) for an unbounded problem — must iterate coins forward.
- Integer overflow when adding 1 to `∞` — use `amount + 1` as infinity, not `int max`.

---

## Key Takeaways

- Coin Change = **unbounded knapsack minimisation** — the most fundamental DP optimisation problem.
- The template `dp[a] = min(dp[a], dp[a-coin] + 1)` appears across dozens of problems.
- Distinguishing **unbounded** (forward inner loop) from **0/1** (reverse inner loop) is essential.
- Sentinel value: use `amount + 1` as "infinity" to safely do `dp[a-coin] + 1` without overflow.

---

## Next Problem

➡️ [Longest Increasing Subsequence](../longest-increasing-subsequence/README.md)
