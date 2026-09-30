package main

// =============================================================================
// File    : ClimbingStairs_test.go
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Climbing Stairs (LeetCode #70)
// =============================================================================

import "testing"

func TestClimbStairs(t *testing.T) {
	cs := ClimbingStairs{}

	tests := []struct {
		name     string
		n        int
		expected int
	}{
		{"n=1", 1, 1},
		{"n=2", 2, 2},
		{"n=3", 3, 3},
		{"n=4", 4, 5},
		{"n=5", 5, 8},
		{"n=10", 10, 89},
		{"n=20", 20, 10946},
		{"n=45", 45, 1836311903},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := cs.climbStairs(tc.n)
			if got != tc.expected {
				t.Errorf("climbStairs(%d) = %d; want %d", tc.n, got, tc.expected)
			}
		})
	}
}
