package main

import (
	"reflect"
	"testing"
)

func TestInorderTraversal(t *testing.T) {
	s := InorderTraversal{}

	tests := []struct {
		name     string
		root     *TreeNode
		expected []int
	}{
		{"Nil", nil, []int{}},
		{"Single", &TreeNode{Val: 1}, []int{1}},
		{"Example [4,2,5,1,3]", func() *TreeNode {
			r := &TreeNode{Val: 4}
			r.Left = &TreeNode{Val: 2}
			r.Right = &TreeNode{Val: 5}
			r.Left.Left = &TreeNode{Val: 1}
			r.Left.Right = &TreeNode{Val: 3}
			return r
		}(), []int{1, 2, 3, 4, 5}},
		{"Right-skewed", func() *TreeNode {
			r := &TreeNode{Val: 1}
			r.Right = &TreeNode{Val: 2}
			r.Right.Right = &TreeNode{Val: 3}
			return r
		}(), []int{1, 2, 3}},
	}

	for _, tc := range tests {
		t.Run(tc.name+" recursive", func(t *testing.T) {
			got := s.InorderRecursive(tc.root)
			if !reflect.DeepEqual(got, tc.expected) {
				t.Fatalf("recursive: expected %v got %v", tc.expected, got)
			}
		})
		t.Run(tc.name+" iterative", func(t *testing.T) {
			got := s.InorderIterative(tc.root)
			if !reflect.DeepEqual(got, tc.expected) {
				t.Fatalf("iterative: expected %v got %v", tc.expected, got)
			}
		})
	}
}
