// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Assign Cookies — Go tests

package main

import "testing"

func TestFindContentChildren(t *testing.T) {
	solver := AssignCookies{}

	tests := []struct {
		name     string
		g, s     []int
		expected int
	}{
		{"example1", []int{1, 2, 3}, []int{1, 1}, 1},
		{"example2 all satisfied", []int{1, 2}, []int{1, 2, 3}, 2},
		{"cookies too small", []int{10, 9, 8, 7}, []int{5, 6, 7, 8}, 2},
		{"no cookies", []int{1, 2, 3}, []int{}, 0},
		{"no children", []int{}, []int{1, 2, 3}, 0},
		{"single match", []int{1}, []int{1}, 1},
		{"single no match", []int{2}, []int{1}, 0},
		{"all same size", []int{1, 1, 1}, []int{1, 1, 1}, 3},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Copy inputs so sort doesn't mutate test data
			gCopy := append([]int{}, tt.g...)
			sCopy := append([]int{}, tt.s...)
			got := solver.FindContentChildren(gCopy, sCopy)
			if got != tt.expected {
				t.Errorf("FindContentChildren(%v, %v) = %d, want %d",
					tt.g, tt.s, got, tt.expected)
			}
		})
	}
}
