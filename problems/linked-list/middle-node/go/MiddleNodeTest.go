package main

import "testing"

func TestMiddleNode(t *testing.T) {

	solver := MiddleNode{}

	tests := []struct {
		name          string
		input         []int
		expectedValue int
	}{
		{
			"Odd length — 5 nodes",
			[]int{1, 2, 3, 4, 5},
			3,
		},
		{
			"Even length — 6 nodes (second middle)",
			[]int{1, 2, 3, 4, 5, 6},
			4,
		},
		{
			"Single node",
			[]int{1},
			1,
		},
		{
			"Two nodes — second middle",
			[]int{1, 2},
			2,
		},
		{
			"Four nodes",
			[]int{1, 2, 3, 4},
			3,
		},
	}

	for _, tc := range tests {

		t.Run(tc.name, func(t *testing.T) {

			head := buildList(tc.input)
			result := solver.FindMiddle(head)

			if result == nil {
				t.Fatalf("expected node with value %d but got nil", tc.expectedValue)
			}

			if result.Val != tc.expectedValue {
				t.Fatalf(
					"expected middle value %d but got %d",
					tc.expectedValue,
					result.Val,
				)
			}
		})
	}
}
