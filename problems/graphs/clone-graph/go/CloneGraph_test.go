// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Clone Graph — Go tests

package main

import (
	"reflect"
	"sort"
	"testing"
)

// adjListFromNode converts a cloned graph back to a sorted adjacency list
// (map: val → sorted neighbor vals) for easy deep-equal comparison.
func adjListFromNode(node *Node) map[int][]int {
	if node == nil {
		return nil
	}
	result := make(map[int][]int)
	visited := make(map[*Node]bool)
	var walk func(*Node)
	walk = func(n *Node) {
		if visited[n] {
			return
		}
		visited[n] = true
		vals := make([]int, 0, len(n.Neighbors))
		for _, nb := range n.Neighbors {
			vals = append(vals, nb.Val)
		}
		sort.Ints(vals)
		result[n.Val] = vals
		for _, nb := range n.Neighbors {
			walk(nb)
		}
	}
	walk(node)
	return result
}

func TestCloneGraph(t *testing.T) {
	solver := CloneGraph{}

	t.Run("nil input", func(t *testing.T) {
		if solver.CloneGraph(nil) != nil {
			t.Error("expected nil for nil input")
		}
	})

	t.Run("single node no neighbors", func(t *testing.T) {
		n := &Node{Val: 1}
		clone := solver.CloneGraph(n)
		if clone == n {
			t.Error("clone should be a different object")
		}
		if clone.Val != 1 || len(clone.Neighbors) != 0 {
			t.Errorf("unexpected clone: %+v", clone)
		}
	})

	t.Run("4-node cycle", func(t *testing.T) {
		original := buildGraph([][]int{{2, 4}, {1, 3}, {2, 4}, {1, 3}})
		clone := solver.CloneGraph(original)

		if clone == original {
			t.Error("clone root must be a different object")
		}

		origAdj := adjListFromNode(original)
		cloneAdj := adjListFromNode(clone)
		if !reflect.DeepEqual(origAdj, cloneAdj) {
			t.Errorf("adjacency mismatch: orig=%v clone=%v", origAdj, cloneAdj)
		}
	})

	t.Run("two-node mutual", func(t *testing.T) {
		original := buildGraph([][]int{{2}, {1}})
		clone := solver.CloneGraph(original)

		origAdj := adjListFromNode(original)
		cloneAdj := adjListFromNode(clone)
		if !reflect.DeepEqual(origAdj, cloneAdj) {
			t.Errorf("adjacency mismatch: orig=%v clone=%v", origAdj, cloneAdj)
		}
		if clone == original {
			t.Error("clone root must be a different pointer")
		}
	})
}
