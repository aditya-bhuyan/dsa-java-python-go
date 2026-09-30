// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Gas Station — Go tests

package main

import "testing"

func TestCanCompleteCircuit(t *testing.T) {
	solver := GasStation{}

	tests := []struct {
		name     string
		gas      []int
		cost     []int
		expected int
	}{
		{"example1", []int{1, 2, 3, 4, 5}, []int{3, 4, 5, 1, 2}, 3},
		{"example2 no solution", []int{2, 3, 4}, []int{3, 4, 3}, -1},
		{"single station enough", []int{5}, []int{4}, 0},
		{"single station exact", []int{1}, []int{1}, 0},
		{"single station not enough", []int{1}, []int{2}, -1},
		{"start at last", []int{1, 1, 1, 4}, []int{2, 2, 2, 1}, 3},
		{"start at 0", []int{3, 1, 1}, []int{1, 2, 2}, 0},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.CanCompleteCircuit(tt.gas, tt.cost)
			if got != tt.expected {
				t.Errorf("CanCompleteCircuit(%v, %v) = %d, want %d",
					tt.gas, tt.cost, got, tt.expected)
			}
		})
	}
}
