package main

import (
	"reflect"
	"testing"
)

func TestNextGreaterElement(t *testing.T) {

	solver := NextGreaterElement{}

	tests := []struct {
		name     string
		nums1    []int
		nums2    []int
		expected []int
	}{
		{
			"Example 1",
			[]int{4, 1, 2},
			[]int{1, 3, 4, 2},
			[]int{-1, 3, -1},
		},
		{
			"Example 2",
			[]int{2, 4},
			[]int{1, 2, 3, 4},
			[]int{3, -1},
		},
		{
			"All increasing in nums2",
			[]int{1, 2},
			[]int{1, 2, 3},
			[]int{2, 3},
		},
		{
			"All decreasing in nums2",
			[]int{3, 1},
			[]int{3, 2, 1},
			[]int{-1, -1},
		},
		{
			"Single element query",
			[]int{5},
			[]int{5, 4, 3, 2, 1},
			[]int{-1},
		},
		{
			"Last element of nums2",
			[]int{1},
			[]int{2, 1},
			[]int{-1},
		},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			result := solver.FindNextGreater(tc.nums1, tc.nums2)
			if !reflect.DeepEqual(result, tc.expected) {
				t.Fatalf("expected %v, got %v", tc.expected, result)
			}
		})
	}
}
