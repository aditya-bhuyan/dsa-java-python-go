package main

// =============================================================================
// File    : HouseRobber_test.go
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : House Robber (LeetCode #198)
// =============================================================================

import "testing"

func TestRob(t *testing.T) {
	hr := HouseRobber{}

	tests := []struct {
		name     string
		nums     []int
		expected int
	}{
		{"example1 [1,2,3,1]", []int{1, 2, 3, 1}, 4},
		{"example2 [2,7,9,3,1]", []int{2, 7, 9, 3, 1}, 12},
		{"single house", []int{5}, 5},
		{"two houses", []int{3, 10}, 10},
		{"all equal", []int{4, 4, 4, 4}, 8},
		{"descending", []int{9, 5, 3, 1}, 12},
		{"alternating", []int{1, 9, 1, 9, 1}, 27},
		{"empty slice", []int{}, 0},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := hr.rob(tc.nums)
			if got != tc.expected {
				t.Errorf("rob(%v) = %d; want %d", tc.nums, got, tc.expected)
			}
		})
	}
}
