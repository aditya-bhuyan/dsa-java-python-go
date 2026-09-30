package main

import "testing"

func TestDiameter(t *testing.T) {
	tests := []struct {
		name     string
		root     *TreeNode
		expected int
	}{
		{"single node", n(1), 0},
		{"two nodes", func() *TreeNode { r := n(1); r.Right = n(2); return r }(), 1},
		{"example 1", func() *TreeNode {
			r := &TreeNode{Val: 1}
			r.Left = &TreeNode{Val: 2, Left: n(4), Right: n(5)}
			r.Right = n(3)
			return r
		}(), 3},
		{"diameter not through root", func() *TreeNode {
			r := n(1)
			r.Left = &TreeNode{Val: 2}
			r.Left.Left = &TreeNode{Val: 3, Left: n(5)}
			r.Left.Right = n(4)
			return r
		}(), 3},
		{"nil", nil, 0},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			d := &DiameterOfBinaryTree{}
			got := d.DiameterOfTree(tc.root)
			if got != tc.expected {
				t.Fatalf("expected %d got %d", tc.expected, got)
			}
		})
	}
}
