/*
===============================================================================
Problem: Symmetric Tree
===============================================================================
Author   : Aditya Bhuyan  |  Language : Go 1.24+
Time: O(n)  Space: O(h)
===============================================================================
*/

package main

import "fmt"

type TreeNode struct{ Val int; Left, Right *TreeNode }

type SymmetricTree struct{}

func (s SymmetricTree) IsSymmetric(root *TreeNode) bool {
	if root == nil { return true }
	return s.isMirror(root.Left, root.Right)
}

func (s SymmetricTree) isMirror(left, right *TreeNode) bool {
	if left == nil && right == nil { return true }
	if left == nil || right == nil { return false }
	if left.Val != right.Val       { return false }
	return s.isMirror(left.Left, right.Right) && s.isMirror(left.Right, right.Left)
}

func n(v int) *TreeNode { return &TreeNode{Val: v} }

func main() {
	//     1
	//    / \
	//   2   2
	//  /\ /\
	// 3 4 4 3  → true
	r1 := &TreeNode{Val: 1}
	r1.Left = &TreeNode{Val: 2, Left: n(3), Right: n(4)}
	r1.Right = &TreeNode{Val: 2, Left: n(4), Right: n(3)}

	//     1
	//    / \
	//   2   2
	//    \   \
	//     3   3  → false
	r2 := &TreeNode{Val: 1}
	r2.Left = &TreeNode{Val: 2}; r2.Left.Right = n(3)
	r2.Right = &TreeNode{Val: 2}; r2.Right.Right = n(3)

	s := SymmetricTree{}
	fmt.Println("============================================================")
	fmt.Println("Symmetric Tree")
	fmt.Println("============================================================")
	fmt.Println("Symmetric   (expected true) :", s.IsSymmetric(r1))
	fmt.Println("Asymmetric  (expected false):", s.IsSymmetric(r2))
}
