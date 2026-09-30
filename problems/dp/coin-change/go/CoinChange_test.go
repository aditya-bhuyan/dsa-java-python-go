package main

// =============================================================================
// File    : CoinChange_test.go
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Coin Change (LeetCode #322)
// =============================================================================

import "testing"

func TestCoinChange(t *testing.T) {
	cc := CoinChange{}

	tests := []struct {
		name     string
		coins    []int
		amount   int
		expected int
	}{
		{"example1 coins=[1,2,5] amount=11", []int{1, 2, 5}, 11, 3},
		{"example2 coins=[2] amount=3", []int{2}, 3, -1},
		{"example3 amount=0", []int{1}, 0, 0},
		{"single coin exact", []int{5}, 5, 1},
		{"single coin multiple", []int{3}, 9, 3},
		{"impossible", []int{5, 10}, 3, -1},
		{"large amount", []int{1, 5, 10, 25}, 100, 4},
		{"coins=[186,419,83,408] amount=6249", []int{186, 419, 83, 408}, 6249, 20},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := cc.coinChange(tc.coins, tc.amount)
			if got != tc.expected {
				t.Errorf("coinChange(%v, %d) = %d; want %d", tc.coins, tc.amount, got, tc.expected)
			}
		})
	}
}
