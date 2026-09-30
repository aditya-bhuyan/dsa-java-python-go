package main

import "testing"

func TestMaximumDepth(t *testing.T) {
	s := MaximumDepth{}

	tests := []struct {
		name     string
		build    func() *TreeNode
		expected int
	}{
		{"Nil root", func() *TreeNode { return nil }, 0},
		{"Single node", func() *TreeNode { return newNode(1) }, 1},
		{"Two nodes left", func() *TreeNode {
			r := newNode(1); r.Left = newNode(2); return r
		}, 2},
		{"Example 1 depth 3", func() *TreeNode {
			r := newNode(3)
			r.Left = newNode(9)
			r.Right = newNode(20)
			r.Right.Left = newNode(15)
			r.Right.Right = newNode(7)
			return r
		}, 3},
		{"Right-skewed depth 4", func() *TreeNode {
			r := newNode(1)
			r.Right = newNode(2)
			r.Right.Right = newNode(3)
			r.Right.Right.Right = newNode(4)
			return r
		}, 4},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := s.MaxDepth(tc.build())
			if got != tc.expected {
				t.Fatalf("expected %d, got %d", tc.expected, got)
			}
		})
	}
}
