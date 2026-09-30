/*
===============================================================================
Problem: Same Tree
===============================================================================
Author   : Aditya Bhuyan  |  Language : Go 1.24+
Time: O(n)  Space: O(h)
===============================================================================
*/

package main

import "fmt"

type TreeNode struct{ Val int; Left, Right *TreeNode }

type SameTree struct{}

// IsSameTree returns true if p and q are structurally identical with equal values.
func (s SameTree) IsSameTree(p, q *TreeNode) bool {
	if p == nil && q == nil { return true }
	if p == nil || q == nil { return false }
	if p.Val != q.Val        { return false }
	return s.IsSameTree(p.Left, q.Left) && s.IsSameTree(p.Right, q.Right)
}

func n(v int) *TreeNode { return &TreeNode{Val: v} }

func main() {
	// p: 1-2-3   q: 1-2-3  → true
	p1 := &TreeNode{1, n(2), n(3)}
	q1 := &TreeNode{1, n(2), n(3)}
	// p: 1-2     q: 1-nil-2 → false
	p2 := &TreeNode{Val: 1}; p2.Left = n(2)
	q2 := &TreeNode{Val: 1}; q2.Right = n(2)

	s := SameTree{}
	fmt.Println("Same (expected true) :", s.IsSameTree(p1, q1))
	fmt.Println("Different structure  :", s.IsSameTree(p2, q2))
}
