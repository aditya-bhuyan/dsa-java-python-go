package main

import "testing"

func TestSearchRotatedArray(t *testing.T) {
	s := SearchRotatedArray{}

	tests := []struct {
		nums     []int
		target   int
		expected int
	}{
		{[]int{4, 5, 6, 7, 0, 1, 2}, 0, 4},
		{[]int{4, 5, 6, 7, 0, 1, 2}, 3, -1},
		{[]int{1}, 0, -1},
		{[]int{1}, 1, 0},
		{[]int{1, 3}, 3, 1},
		{[]int{3, 1}, 1, 1},
		{[]int{3, 1}, 3, 0},
		{[]int{5, 1, 3}, 5, 0},
		{[]int{4, 5, 6, 7, 0, 1, 2}, 4, 0}, // target at rotation point
		{[]int{4, 5, 6, 7, 0, 1, 2}, 2, 6}, // target at end
		{[]int{0, 1, 2, 4, 5, 6, 7}, 0, 0}, // no rotation
	}

	for _, tc := range tests {
		got := s.Search(tc.nums, tc.target)
		if got != tc.expected {
			t.Errorf("Search(%v, %d) = %d, want %d", tc.nums, tc.target, got, tc.expected)
		}
	}
}
