package main

import "testing"

func TestCycleDetection(t *testing.T) {

	solver := CycleDetection{}

	tests := []struct {
		name        string
		values      []int
		cycleIndex  int
		expected    bool
	}{
		{
			"No cycle — four nodes",
			[]int{1, 2, 3, 4},
			-1,
			false,
		},
		{
			"No cycle — single node",
			[]int{1},
			-1,
			false,
		},
		{
			"No cycle — empty list",
			[]int{},
			-1,
			false,
		},
		{
			"Cycle — tail to head",
			[]int{1, 2, 3},
			0,
			true,
		},
		{
			"Cycle — tail to middle",
			[]int{3, 2, 0, -4},
			1,
			true,
		},
		{
			"Cycle — self loop",
			[]int{1},
			0,
			true,
		},
		{
			"Cycle — two nodes, tail to head",
			[]int{1, 2},
			0,
			true,
		},
		{
			"No cycle — two nodes",
			[]int{1, 2},
			-1,
			false,
		},
	}

	for _, tc := range tests {

		t.Run(tc.name, func(t *testing.T) {

			var head *ListNode
			if len(tc.values) > 0 {
				head = buildList(tc.values, tc.cycleIndex)
			}

			result := solver.HasCycle(head)

			if result != tc.expected {
				t.Fatalf(
					"expected %v but got %v",
					tc.expected,
					result,
				)
			}
		})
	}
}
