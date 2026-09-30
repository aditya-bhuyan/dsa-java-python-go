package main

import "testing"

func TestSymmetricTree(t *testing.T) {
	s := SymmetricTree{}

	symmetric := func() *TreeNode {
		r := &TreeNode{Val: 1}
		r.Left  = &TreeNode{Val: 2, Left: n(3), Right: n(4)}
		r.Right = &TreeNode{Val: 2, Left: n(4), Right: n(3)}
		return r
	}
	asymmetric := func() *TreeNode {
		r := &TreeNode{Val: 1}
		r.Left = &TreeNode{Val: 2}; r.Left.Right = n(3)
		r.Right = &TreeNode{Val: 2}; r.Right.Right = n(3)
		return r
	}

	tests := []struct{ name string; root *TreeNode; want bool }{
		{"nil", nil, true},
		{"single node", n(1), true},
		{"symmetric", symmetric(), true},
		{"asymmetric", asymmetric(), false},
		{"different values", &TreeNode{1, n(2), n(3)}, false},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := s.IsSymmetric(tc.root)
			if got != tc.want {
				t.Fatalf("expected %v got %v", tc.want, got)
			}
		})
	}
}
