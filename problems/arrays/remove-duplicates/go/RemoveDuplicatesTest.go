package main

import (
	"testing"
)

func TestRemoveDups(t *testing.T) {
	solver := &RemoveDuplicates{}

	tests := []struct {
		name     string
		nums     []int
		expected int
	}{
		{"Basic case 1", []int{1, 1, 2}, 2},
		{"Basic case 2", []int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5},
		{"Single element", []int{1}, 1},
		{"All duplicates", []int{1, 1, 1, 1}, 1},
		{"No duplicates", []int{1, 2, 3, 4, 5}, 5},
		{"Empty array", []int{}, 0},
		{"Nil array", nil, 0},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := solver.RemoveDups(tt.nums)
			if result != tt.expected {
				t.Errorf("RemoveDups(%v) = %d, want %d", tt.nums, result, tt.expected)
			}
		})
	}
}
