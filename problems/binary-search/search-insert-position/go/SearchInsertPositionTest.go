package main

import "testing"

func TestSearchInsertPosition(t *testing.T) {
	s := SearchInsertPosition{}
	nums := []int{1, 3, 5, 6}

	tests := []struct{ target, want int }{
		{5, 2}, {2, 1}, {7, 4}, {0, 0},
		{1, 0}, {6, 3},
	}
	for _, tc := range tests {
		got := s.SearchInsert(nums, tc.target)
		if got != tc.want {
			t.Errorf("SearchInsert(target=%d) = %d, want %d", tc.target, got, tc.want)
		}
	}

	// single-element cases
	if s.SearchInsert([]int{3}, 3) != 0 {
		t.Error("single equal: expected 0")
	}
	if s.SearchInsert([]int{3}, 1) != 0 {
		t.Error("single smaller: expected 0")
	}
	if s.SearchInsert([]int{3}, 5) != 1 {
		t.Error("single larger: expected 1")
	}
}
