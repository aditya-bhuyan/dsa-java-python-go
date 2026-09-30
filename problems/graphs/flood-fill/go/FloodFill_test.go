// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Flood Fill — Go tests

package main

import (
	"reflect"
	"testing"
)

func TestFloodFill(t *testing.T) {
	solver := FloodFill{}

	tests := []struct {
		name     string
		image    [][]int
		sr, sc   int
		color    int
		expected [][]int
	}{
		{
			"basic 3x3",
			[][]int{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2,
			[][]int{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}},
		},
		{
			"same color no-op",
			[][]int{{0, 0, 0}, {0, 0, 0}}, 0, 0, 0,
			[][]int{{0, 0, 0}, {0, 0, 0}},
		},
		{
			"single pixel",
			[][]int{{1}}, 0, 0, 5,
			[][]int{{5}},
		},
		{
			"isolated seed",
			[][]int{{1, 0, 1}}, 0, 0, 3,
			[][]int{{3, 0, 1}},
		},
		{
			"entire grid same color",
			[][]int{{1, 1}, {1, 1}}, 0, 0, 9,
			[][]int{{9, 9}, {9, 9}},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.FloodFill(copyImage(tt.image), tt.sr, tt.sc, tt.color)
			if !reflect.DeepEqual(got, tt.expected) {
				t.Errorf("FloodFill() = %v, want %v", got, tt.expected)
			}
		})
	}
}
