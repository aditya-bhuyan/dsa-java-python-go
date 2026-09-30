// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Maximum Average Subarray I — Go tests

package main

import (
	"math"
	"testing"
)

func TestFindMaxAverage(t *testing.T) {
	solver := MaximumAverage{}

	tests := []struct {
		name     string
		nums     []int
		k        int
		expected float64
	}{
		{"example1", []int{1, 12, -5, -6, 50, 3}, 4, 12.75},
		{"single element k=1", []int{5}, 1, 5.0},
		{"all negatives", []int{-3, -2, -5, -1}, 2, -1.5},
		{"k equals n", []int{4, 0, 4}, 3, float64(8) / 3},
		{"k=1 max value", []int{0, 4, 0, 3, 2}, 1, 4.0},
		{"all same", []int{2, 2, 2, 2}, 2, 2.0},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.FindMaxAverage(tt.nums, tt.k)
			if math.Abs(got-tt.expected) > 1e-5 {
				t.Errorf("FindMaxAverage(%v, %d) = %.5f, want %.5f",
					tt.nums, tt.k, got, tt.expected)
			}
		})
	}
}
