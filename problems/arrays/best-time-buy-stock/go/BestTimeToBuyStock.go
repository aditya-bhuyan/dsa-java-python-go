package main

import (
	"fmt"
)

// BestTimeToBuyStock finds the maximum profit from buying and selling stock once.
//
// Time Complexity: O(n) - single pass through the array
// Space Complexity: O(1) - constant extra space
type BestTimeToBuyStock struct{}

// MaxProfit returns the maximum profit possible from a single buy-sell transaction.
// 
// Greedy approach: track the minimum price seen so far and calculate profit at each point.
//
// @param prices array of integers where prices[i] is the price on day i
// @return maximum profit possible, or 0 if no profit can be achieved
func (b *BestTimeToBuyStock) MaxProfit(prices []int) int {
	if prices == nil || len(prices) < 2 {
		return 0
	}
	
	minPrice := prices[0]
	maxProfit := 0
	
	for i := 1; i < len(prices); i++ {
		profit := prices[i] - minPrice
		if profit > maxProfit {
			maxProfit = profit
		}
		if prices[i] < minPrice {
			minPrice = prices[i]
		}
	}
	
	return maxProfit
}

// MaxProfitBruteForce checks all pairs to find maximum profit (for reference/teaching)
//
// Time Complexity: O(n²)
// Space Complexity: O(1)
func (b *BestTimeToBuyStock) MaxProfitBruteForce(prices []int) int {
	if prices == nil || len(prices) < 2 {
		return 0
	}
	
	maxProfit := 0
	for i := 0; i < len(prices); i++ {
		for j := i + 1; j < len(prices); j++ {
			profit := prices[j] - prices[i]
			if profit > maxProfit {
				maxProfit = profit
			}
		}
	}
	return maxProfit
}

func main() {
	solver := &BestTimeToBuyStock{}
	
	// Test cases
	test1 := []int{7, 1, 5, 3, 6, 4}
	fmt.Printf("Test 1: %d (Expected: 5)\n", solver.MaxProfit(test1))

	test2 := []int{7, 6, 4, 3, 1}
	fmt.Printf("Test 2: %d (Expected: 0)\n", solver.MaxProfit(test2))

	test3 := []int{2, 4, 1}
	fmt.Printf("Test 3: %d (Expected: 2)\n", solver.MaxProfit(test3))

	test4 := []int{1}
	fmt.Printf("Test 4: %d (Expected: 0)\n", solver.MaxProfit(test4))

	test5 := []int{1, 2, 3, 4, 5}
	fmt.Printf("Test 5: %d (Expected: 4)\n", solver.MaxProfit(test5))

	test6 := []int{}
	fmt.Printf("Test 6: %d (Expected: 0)\n", solver.MaxProfit(test6))
}
