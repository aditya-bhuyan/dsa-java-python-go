package main

import "testing"

func TestBinarySearch(t *testing.T) {
	s := BinarySearch{}

	tests := []struct {
		nums     []int
		target   int
		expected int
	}{
		{[]int{-1, 0, 3, 5, 9, 12}, 9, 4},
		{[]int{-1, 0, 3, 5, 9, 12}, 2, -1},
		{[]int{5}, 5, 0},
		{[]int{5}, 3, -1},
		{[]int{1, 2, 3, 4, 5}, 1, 0},
		{[]int{1, 2, 3, 4, 5}, 5, 4},
		{[]int{1, 2, 3, 4, 5}, 6, -1},
	}

	for _, tc := range tests {
		got := s.Search(tc.nums, tc.target)
		if got != tc.expected {
			t.Errorf("Search(%v, %d) = %d, want %d", tc.nums, tc.target, got, tc.expected)
		}
	}
}
