package main

import (
	"reflect"
	"testing"
)

func TestMergeLists(t *testing.T) {

	solver := MergeLists{}

	tests := []struct {
		name     string
		list1    []int
		list2    []int
		expected []int
	}{
		{
			"Basic example",
			[]int{1, 2, 4},
			[]int{1, 3, 4},
			[]int{1, 1, 2, 3, 4, 4},
		},
		{
			"Both empty",
			[]int{},
			[]int{},
			[]int{},
		},
		{
			"First empty",
			[]int{},
			[]int{0},
			[]int{0},
		},
		{
			"Second empty",
			[]int{1, 3},
			[]int{},
			[]int{1, 3},
		},
		{
			"Different lengths",
			[]int{1, 3, 5, 7},
			[]int{2, 4},
			[]int{1, 2, 3, 4, 5, 7},
		},
		{
			"All same values",
			[]int{1, 1, 1},
			[]int{1, 1},
			[]int{1, 1, 1, 1, 1},
		},
		{
			"Interleaved",
			[]int{1, 5, 9},
			[]int{2, 3, 10},
			[]int{1, 2, 3, 5, 9, 10},
		},
	}

	for _, tc := range tests {

		t.Run(tc.name, func(t *testing.T) {

			l1 := buildList(tc.list1)
			l2 := buildList(tc.list2)
			merged := solver.Merge(l1, l2)
			got := listToSlice(merged)

			if !reflect.DeepEqual(got, tc.expected) {
				t.Fatalf(
					"expected %v but got %v",
					tc.expected,
					got,
				)
			}
		})
	}
}

// listToSlice converts a linked list to a slice for easy comparison.
func listToSlice(head *ListNode) []int {
	result := []int{}
	for head != nil {
		result = append(result, head.Val)
		head = head.Next
	}
	return result
}
