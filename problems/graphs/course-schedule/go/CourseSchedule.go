// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Course Schedule (LeetCode 207)
// Approach: BFS Kahn's Algorithm (Topological Sort)

package main

import "fmt"

// CourseSchedule holds the solution logic.
type CourseSchedule struct{}

// CanFinish returns true if all numCourses can be completed given prerequisites.
// Each prerequisite [a, b] means course b must be taken before course a.
// Time: O(V+E)  Space: O(V+E)
func (cs CourseSchedule) CanFinish(numCourses int, prerequisites [][]int) bool {
	adj := make([][]int, numCourses)
	inDegree := make([]int, numCourses)

	for _, pre := range prerequisites {
		a, b := pre[0], pre[1] // b → a  (b must come before a)
		adj[b] = append(adj[b], a)
		inDegree[a]++
	}

	// Enqueue all nodes with in-degree 0
	queue := []int{}
	for i := 0; i < numCourses; i++ {
		if inDegree[i] == 0 {
			queue = append(queue, i)
		}
	}

	processed := 0
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		processed++
		for _, nb := range adj[node] {
			inDegree[nb]--
			if inDegree[nb] == 0 {
				queue = append(queue, nb)
			}
		}
	}
	return processed == numCourses
}

func main() {
	solver := CourseSchedule{}

	tests := []struct {
		numCourses int
		prereqs    [][]int
		expected   bool
	}{
		{2, [][]int{{1, 0}}, true},
		{2, [][]int{{1, 0}, {0, 1}}, false},
		{1, [][]int{}, true},
		{5, [][]int{{1, 0}, {2, 1}, {3, 2}, {4, 3}}, true},
		{3, [][]int{{0, 1}, {1, 2}, {2, 0}}, false},
	}

	for i, tt := range tests {
		result := solver.CanFinish(tt.numCourses, tt.prereqs)
		status := "PASS"
		if result != tt.expected {
			status = "FAIL"
		}
		fmt.Printf("Test %d: CanFinish=%v (expected %v) → %s\n", i+1, result, tt.expected, status)
	}
}
