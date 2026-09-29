package main

import (
	"reflect"
	"testing"
)

func TestRotate(t *testing.T) {
	solver := &RotateArray{}

	tests := []struct {
		name     string
		input    []int
		k        int
		expected []int
	}{
		{"Basic case 1", []int{1, 2, 3, 4, 5, 6, 7}, 3, []int{5, 6, 7, 1, 2, 3, 4}},
		{"Basic case 2", []int{-1, -100, 3, 99}, 2, []int{3, 99, -1, -100}},
		{"k zero", []int{1}, 0, []int{1}},
		{"k greater than n", []int{1, 2}, 3, []int{2, 1}},
		{"k equals n", []int{1, 2, 3}, 3, []int{1, 2, 3}},
		{"Single element", []int{1}, 5, []int{1}},
		{"Empty array", []int{}, 3, []int{}},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			input := make([]int, len(tt.input))
			copy(input, tt.input)
			solver.Rotate(input, tt.k)
			if !reflect.DeepEqual(input, tt.expected) {
				t.Errorf("Rotate(%v, %d) = %v, want %v", tt.input, tt.k, input, tt.expected)
			}
		})
	}
}

func TestRotateNil(t *testing.T) {
	solver := &RotateArray{}
	solver.Rotate(nil, 3) // Should not panic
}
