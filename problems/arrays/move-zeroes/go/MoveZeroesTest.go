package main

import (
	"reflect"
	"testing"
)

func TestMoveZeros(t *testing.T) {
	solver := &MoveZeroes{}

	tests := []struct {
		name     string
		input    []int
		expected []int
	}{
		{"Basic case 1", []int{0, 1, 0, 3, 12}, []int{1, 3, 12, 0, 0}},
		{"Single zero", []int{0}, []int{0}},
		{"No zeroes", []int{1, 2, 3}, []int{1, 2, 3}},
		{"All zeroes", []int{0, 0, 0}, []int{0, 0, 0}},
		{"Zeroes at start", []int{0, 0, 1}, []int{1, 0, 0}},
		{"Empty array", []int{}, []int{}},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			input := make([]int, len(tt.input))
			copy(input, tt.input)
			solver.MoveZeros(input)
			if !reflect.DeepEqual(input, tt.expected) {
				t.Errorf("MoveZeros(%v) = %v, want %v", tt.input, input, tt.expected)
			}
		})
	}
}

func TestMoveZerosNil(t *testing.T) {
	solver := &MoveZeroes{}
	solver.MoveZeros(nil) // Should not panic
}
