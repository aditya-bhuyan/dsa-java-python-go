/*
===============================================================================
Problem: Maximum Depth of Binary Tree
===============================================================================
Author   : Aditya Bhuyan
Language : Go 1.24+

maxDepth(node) = 0                       if node == nil
              = 1 + max(left, right)     otherwise

Time: O(n)  Space: O(h)
===============================================================================
*/

package main

import "fmt"

// TreeNode represents a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// MaximumDepth holds the depth logic.
type MaximumDepth struct{}

// MaxDepth returns the maximum depth (number of nodes on the longest root-to-leaf path).
func (m MaximumDepth) MaxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left  := m.MaxDepth(root.Left)
	right := m.MaxDepth(root.Right)
	if left > right {
		return 1 + left
	}
	return 1 + right
}

// ---- helpers ----------------------------------------------------------------

func newNode(val int) *TreeNode { return &TreeNode{Val: val} }

func main() {
	//       3
	//      / \
	//     9  20
	//        / \
	//       15   7
	root := newNode(3)
	root.Left = newNode(9)
	root.Right = newNode(20)
	root.Right.Left = newNode(15)
	root.Right.Right = newNode(7)

	s := MaximumDepth{}
	fmt.Println("============================================================")
	fmt.Println("Maximum Depth of Binary Tree")
	fmt.Println("============================================================")
	fmt.Printf("Depth: %d  (expected 3)\n", s.MaxDepth(root))
}
