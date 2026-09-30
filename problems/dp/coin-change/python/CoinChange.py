"""
=============================================================================
File    : CoinChange.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : Coin Change (LeetCode #322)

Approach : Bottom-Up DP (Unbounded Knapsack)
----------
  dp[i] = minimum coins to make amount i
  dp[0] = 0
  dp[i] = min(dp[i], dp[i - coin] + 1)  for each coin <= i
  Sentinel: dp[i] = float('inf')  (impossible marker)

Dry Run  : coins=[1,2,5], amount=11
  dp[0]=0, dp[1]=1, dp[2]=1, dp[3]=2, dp[4]=2,
  dp[5]=1, dp[6]=2, dp[7]=2, dp[8]=3, dp[9]=3,
  dp[10]=2, dp[11]=3
  return 3  ✓

Complexity:
  Time  : O(amount × len(coins))
  Space : O(amount)
=============================================================================
"""

from typing import List


class CoinChange:
    """Finds the minimum number of coins to make a given amount."""

    def coin_change(self, coins: List[int], amount: int) -> int:
        """
        Returns the fewest number of coins needed to make up the amount,
        or -1 if it is not possible.

        Args:
            coins:  list of distinct coin denominations (each >= 1)
            amount: target amount (>= 0)

        Returns:
            minimum coin count, or -1 if impossible
        """
        dp = [float('inf')] * (amount + 1)
        dp[0] = 0
        for i in range(1, amount + 1):
            for coin in coins:
                if coin <= i and dp[i - coin] + 1 < dp[i]:
                    dp[i] = dp[i - coin] + 1
        return dp[amount] if dp[amount] != float('inf') else -1


# ─────────────────────────────────────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────────────────────────────────────
def main() -> None:
    solver = CoinChange()
    print("=== Coin Change ===")
    print(solver.coin_change([1, 2, 5], 11))              # 3
    print(solver.coin_change([2], 3))                     # -1
    print(solver.coin_change([1], 0))                     # 0
    print(solver.coin_change([186, 419, 83, 408], 6249))  # 20


if __name__ == "__main__":
    main()
