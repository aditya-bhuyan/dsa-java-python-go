package main

import (
	"reflect"
	"testing"
)

func TestTwoSum(t *testing.T) {

	solver := TwoSum{}

	tests := []struct {
		name     string
		nums     []int
		target   int
		expected []int
		hasError bool
	}{
		{
			"Basic Example",
			[]int{2, 7, 11, 15},
			9,
			[]int{0, 1},
			false,
		},
		{
			"Second Example",
			[]int{3, 2, 4},
			6,
			[]int{1, 2},
			false,
		},
		{
			"Duplicate Values",
			[]int{3, 3},
			6,
			[]int{0, 1},
			false,
		},
		{
			"Negative Numbers",
			[]int{-1, -2, -3, -4, -5},
			-8,
			[]int{2, 4},
			false,
		},
		{
			"Mixed Numbers",
			[]int{-3, 4, 3, 90},
			0,
			[]int{0, 2},
			false,
		},
		{
			"Zero Values",
			[]int{0, 4, 3, 0},
			0,
			[]int{0, 3},
			false,
		},
		{
			"No Solution",
			[]int{1, 2, 3},
			100,
			nil,
			true,
		},
	}

	for _, tc := range tests {

		t.Run(tc.name, func(t *testing.T) {

			result, err := solver.TwoSum(tc.nums, tc.target)

			if tc.hasError {

				if err == nil {
					t.Fatalf("expected error but got nil")
				}

				return
			}

			if err != nil {
				t.Fatalf("unexpected error: %v", err)
			}

			if !reflect.DeepEqual(result, tc.expected) {
				t.Fatalf(
					"expected %v but got %v",
					tc.expected,
					result,
				)
			}
		})
	}
}
