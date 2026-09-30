// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Jump Game — Go tests

package main

import "testing"

func TestCanJump(t *testing.T) {
	solver := JumpGame{}

	tests := []struct {
		name     string
		nums     []int
		expected bool
	}{
		{"example1 reachable", []int{2, 3, 1, 1, 4}, true},
		{"example2 stuck at zero", []int{3, 2, 1, 0, 4}, false},
		{"single element zero", []int{0}, true},
		{"single element nonzero", []int{5}, true},
		{"two elements can jump", []int{1, 0}, true},
		{"two elements cant jump", []int{0, 1}, false},
		{"all ones", []int{1, 1, 1, 1}, true},
		{"large jump from start", []int{5, 0, 0, 0, 0}, true},
		{"last element zero reachable", []int{2, 0, 0}, true},
		{"all zeros n>1", []int{0, 0, 0}, false},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.CanJump(tt.nums)
			if got != tt.expected {
				t.Errorf("CanJump(%v) = %v, want %v", tt.nums, got, tt.expected)
			}
		})
	}
}
