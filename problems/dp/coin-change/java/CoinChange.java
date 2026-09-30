package dp.coinchange;

// =============================================================================
// File    : CoinChange.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Coin Change (LeetCode #322)
//
// Approach : Bottom-Up DP (Unbounded Knapsack)
// ----------
//   dp[i] = minimum coins to make amount i
//   dp[0] = 0
//   dp[i] = min(dp[i], dp[i - coin] + 1)  for each coin ≤ i
//   Sentinel: dp[i] = amount + 1  (impossible marker)
//
// Dry Run  : coins=[1,2,5], amount=11
//   dp[0]=0, dp[1]=1, dp[2]=1, dp[3]=2, dp[4]=2,
//   dp[5]=1, dp[6]=2, dp[7]=2, dp[8]=3, dp[9]=3,
//   dp[10]=2, dp[11]=3
//   return 3  ✓
//
// Complexity:
//   Time  : O(amount × coins)
//   Space : O(amount)
// =============================================================================

import java.util.Arrays;

public class CoinChange {

    /**
     * Returns the fewest number of coins needed to make up the amount,
     * or -1 if it is not possible.
     *
     * @param coins  distinct coin denominations (each ≥ 1)
     * @param amount target amount (≥ 0)
     * @return minimum coin count, or -1
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        CoinChange solver = new CoinChange();
        System.out.println("=== Coin Change ===");
        System.out.println(solver.coinChange(new int[]{1, 2, 5}, 11));  // 3
        System.out.println(solver.coinChange(new int[]{2}, 3));         // -1
        System.out.println(solver.coinChange(new int[]{1}, 0));         // 0
        System.out.println(solver.coinChange(new int[]{186, 419, 83, 408}, 6249)); // 20
    }
}
