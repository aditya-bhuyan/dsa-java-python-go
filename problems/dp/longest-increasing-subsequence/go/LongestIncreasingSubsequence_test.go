package main

// =============================================================================
// File    : LongestIncreasingSubsequence_test.go
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Longest Increasing Subsequence (LeetCode #300)
// =============================================================================

import "testing"

func TestLengthOfLIS(t *testing.T) {
	lis := LongestIncreasingSubsequence{}

	tests := []struct {
		name     string
		nums     []int
		expected int
	}{
		{"example1 [10,9,2,5,3,7,101,18]", []int{10, 9, 2, 5, 3, 7, 101, 18}, 4},
		{"example2 [0,1,0,3,2,3]", []int{0, 1, 0, 3, 2, 3}, 4},
		{"example3 [7,7,7,7,7,7,7]", []int{7, 7, 7, 7, 7, 7, 7}, 1},
		{"single element", []int{5}, 1},
		{"strictly increasing", []int{1, 2, 3, 4, 5}, 5},
		{"strictly decreasing", []int{5, 4, 3, 2, 1}, 1},
		{"all duplicates", []int{3, 3, 3}, 1},
		{"mixed", []int{3, 10, 2, 1, 20}, 3},
		{"empty", []int{}, 0},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := lis.lengthOfLIS(tc.nums)
			if got != tc.expected {
				t.Errorf("lengthOfLIS(%v) = %d; want %d", tc.nums, got, tc.expected)
			}
		})
	}
}

func TestLengthOfLISDP(t *testing.T) {
	lis := LongestIncreasingSubsequence{}

	tests := []struct {
		name     string
		nums     []int
		expected int
	}{
		{"example1", []int{10, 9, 2, 5, 3, 7, 101, 18}, 4},
		{"example2", []int{0, 1, 0, 3, 2, 3}, 4},
		{"all same", []int{7, 7, 7, 7}, 1},
		{"empty", []int{}, 0},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := lis.lengthOfLISDP(tc.nums)
			if got != tc.expected {
				t.Errorf("lengthOfLISDP(%v) = %d; want %d", tc.nums, got, tc.expected)
			}
		})
	}
}
