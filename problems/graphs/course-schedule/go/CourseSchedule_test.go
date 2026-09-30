// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Course Schedule — Go tests

package main

import "testing"

func TestCanFinish(t *testing.T) {
	solver := CourseSchedule{}

	tests := []struct {
		name       string
		numCourses int
		prereqs    [][]int
		expected   bool
	}{
		{"two courses possible", 2, [][]int{{1, 0}}, true},
		{"two courses cycle", 2, [][]int{{1, 0}, {0, 1}}, false},
		{"no prerequisites", 1, [][]int{}, true},
		{"no prerequisites many", 5, [][]int{}, true},
		{"long chain no cycle", 5, [][]int{{1, 0}, {2, 1}, {3, 2}, {4, 3}}, true},
		{"three-node cycle", 3, [][]int{{0, 1}, {1, 2}, {2, 0}}, false},
		{"self-loop", 2, [][]int{{0, 0}}, false},
		{"disconnected no cycle", 4, [][]int{{1, 0}, {3, 2}}, true},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.CanFinish(tt.numCourses, tt.prereqs)
			if got != tt.expected {
				t.Errorf("CanFinish(%d, %v) = %v, want %v",
					tt.numCourses, tt.prereqs, got, tt.expected)
			}
		})
	}
}
