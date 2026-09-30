package main

import "testing"

func TestSameTree(t *testing.T) {
	s := SameTree{}

	tests := []struct {
		name     string
		p, q     *TreeNode
		expected bool
	}{
		{"both nil", nil, nil, true},
		{"p nil", nil, n(1), false},
		{"q nil", n(1), nil, false},
		{"single equal", n(1), n(1), true},
		{"single unequal", n(1), n(2), false},
		{"same structure same values", &TreeNode{1, n(2), n(3)}, &TreeNode{1, n(2), n(3)}, true},
		{"same structure diff values", &TreeNode{1, n(2), n(3)}, &TreeNode{1, n(2), n(4)}, false},
		{"different structure", func() *TreeNode { r := n(1); r.Left = n(2); return r }(),
			func() *TreeNode { r := n(1); r.Right = n(2); return r }(), false},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			got := s.IsSameTree(tc.p, tc.q)
			if got != tc.expected {
				t.Fatalf("expected %v got %v", tc.expected, got)
			}
		})
	}
}
