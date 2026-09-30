/*
===============================================================================
Problem: Coin Change (LeetCode 322)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Given coins[] and amount, return the fewest coins to make up amount,
or -1 if impossible. Coins may be reused (unbounded).

Algorithm
---------
Bottom-up unbounded knapsack DP.
dp[0] = 0; dp[1..amount] = amount+1 (sentinel ∞)

For each amount a from 1 to amount:
  for each coin c:
    if c <= a and dp[a-c]+1 < dp[a]:
      dp[a] = dp[a-c] + 1

return dp[amount] if < amount+1 else -1

Dry Run (coins=[1,2,5], amount=11)
----------------------------------
dp[0]=0
dp[1]=1  (1 coin: 1)
dp[2]=1  (1 coin: 2)
dp[3]=2  (2+1)
dp[4]=2  (2+2)
dp[5]=1  (5)
dp[6]=2  (5+1)
dp[7]=2  (5+2)
dp[8]=3  (5+2+1)
dp[9]=3  (5+2+2)
dp[10]=2 (5+5)
dp[11]=3 (5+5+1) ✓

Complexity
----------
Time:  O(amount × len(coins))
Space: O(amount)
===============================================================================
*/

package main

import "fmt"

// CoinChange holds the solution logic.
type CoinChange struct{}

// CoinChange returns the minimum number of coins needed to make up amount,
// or -1 if it is impossible.
// Time: O(amount * k)  Space: O(amount)  where k = len(coins)
func (c CoinChange) CoinChange(coins []int, amount int) int {
	dp := make([]int, amount+1)
	inf := amount + 1
	for i := 1; i <= amount; i++ {
		dp[i] = inf
	}
	// dp[0] = 0 by default

	for a := 1; a <= amount; a++ {
		for _, coin := range coins {
			if coin <= a && dp[a-coin]+1 < dp[a] {
				dp[a] = dp[a-coin] + 1
			}
		}
	}

	if dp[amount] > amount {
		return -1
	}
	return dp[amount]
}

func main() {
	solver := CoinChange{}

	tests := []struct {
		coins    []int
		amount   int
		expected int
	}{
		{[]int{1, 5, 10, 25}, 36, 3},
		{[]int{1, 2, 5}, 11, 3},
		{[]int{2}, 3, -1},
		{[]int{1}, 0, 0},
		{[]int{1}, 1, 1},
		{[]int{1, 2, 5}, 0, 0},
		{[]int{2, 5, 10, 1}, 27, 4},
		{[]int{1, 3, 4}, 6, 2},
	}

	fmt.Println("============================================================")
	fmt.Println("Coin Change")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.CoinChange(tt.coins, tt.amount)
		ok := result == tt.expected
		fmt.Printf("Test %d: coins=%-15v amount=%-4d → %d (expected %d) %s\n",
			i+1, tt.coins, tt.amount, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
