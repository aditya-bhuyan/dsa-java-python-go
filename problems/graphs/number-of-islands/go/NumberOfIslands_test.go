// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Number of Islands — Go tests

package main

import "testing"

func TestNumIslands(t *testing.T) {
	solver := NumberOfIslands{}

	tests := []struct {
		name     string
		grid     [][]byte
		expected int
	}{
		{
			name: "single large island",
			grid: [][]byte{
				{'1', '1', '1', '1', '0'},
				{'1', '1', '0', '1', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '0', '0', '0'},
			},
			expected: 1,
		},
		{
			name: "three islands",
			grid: [][]byte{
				{'1', '1', '0', '0', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '1', '0', '0'},
				{'0', '0', '0', '1', '1'},
			},
			expected: 3,
		},
		{
			name:     "all water",
			grid:     [][]byte{{'0', '0'}, {'0', '0'}},
			expected: 0,
		},
		{
			name:     "all land",
			grid:     [][]byte{{'1', '1'}, {'1', '1'}},
			expected: 1,
		},
		{
			name:     "single land cell",
			grid:     [][]byte{{'1'}},
			expected: 1,
		},
		{
			name:     "single water cell",
			grid:     [][]byte{{'0'}},
			expected: 0,
		},
		{
			name:     "diagonal lands are separate islands",
			grid:     [][]byte{{'1', '0'}, {'0', '1'}},
			expected: 2,
		},
		{
			name:     "alternating row",
			grid:     [][]byte{{'1', '0', '1', '0', '1'}},
			expected: 3,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.NumIslands(copyGrid(tt.grid))
			if got != tt.expected {
				t.Errorf("NumIslands() = %d, want %d", got, tt.expected)
			}
		})
	}
}
