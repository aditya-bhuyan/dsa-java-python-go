package main

import (
	"reflect"
	"testing"
)

func TestReverseLinkedList(t *testing.T) {

	solver := ReverseLinkedList{}

	tests := []struct {
		name     string
		input    []int
		expected []int
	}{
		{
			"Basic Example",
			[]int{1, 2, 3, 4, 5},
			[]int{5, 4, 3, 2, 1},
		},
		{
			"Two Nodes",
			[]int{1, 2},
			[]int{2, 1},
		},
		{
			"Single Node",
			[]int{1},
			[]int{1},
		},
		{
			"Empty List",
			[]int{},
			[]int{},
		},
		{
			"Already Reversed",
			[]int{5, 4, 3, 2, 1},
			[]int{1, 2, 3, 4, 5},
		},
	}

	for _, tc := range tests {

		t.Run(tc.name, func(t *testing.T) {

			head := buildList(tc.input)
			result := solver.Reverse(head)
			got := listToSlice(result)

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
