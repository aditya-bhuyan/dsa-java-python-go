package main

import (
	"reflect"
	"testing"
)

func TestDailyTemperatures(t *testing.T) {

	solver := DailyTemperatures{}

	tests := []struct {
		name     string
		input    []int
		expected []int
	}{
		{
			"Example 1",
			[]int{73, 74, 75, 71, 69, 72, 76, 73},
			[]int{1, 1, 4, 2, 1, 1, 0, 0},
		},
		{
			"All increasing",
			[]int{30, 40, 50, 60},
			[]int{1, 1, 1, 0},
		},
		{
			"All decreasing",
			[]int{60, 50, 40, 30},
			[]int{0, 0, 0, 0},
		},
		{
			"Single element",
			[]int{50},
			[]int{0},
		},
		{
			"All same",
			[]int{50, 50, 50},
			[]int{0, 0, 0},
		},
		{
			"Two increasing",
			[]int{30, 60},
			[]int{1, 0},
		},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			result := solver.WaitDays(tc.input)
			if !reflect.DeepEqual(result, tc.expected) {
				t.Fatalf("expected %v, got %v", tc.expected, result)
			}
		})
	}
}
