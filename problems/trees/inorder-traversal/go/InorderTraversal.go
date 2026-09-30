/*
===============================================================================
Problem: Binary Tree Inorder Traversal
===============================================================================
Author   : Aditya Bhuyan
Language : Go 1.24+

Both recursive and iterative implementations.
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

// InorderTraversal holds both traversal methods.
type InorderTraversal struct{}

// InorderRecursive returns the inorder traversal using recursion.
func (t InorderTraversal) InorderRecursive(root *TreeNode) []int {
	result := []int{}
	var dfs func(*TreeNode)
	dfs = func(node *TreeNode) {
		if node == nil {
			return
		}
		dfs(node.Left)
		result = append(result, node.Val)
		dfs(node.Right)
	}
	dfs(root)
	return result
}

// InorderIterative returns the inorder traversal without recursion.
func (t InorderTraversal) InorderIterative(root *TreeNode) []int {
	result  := []int{}
	stack   := []*TreeNode{}
	current := root

	for current != nil || len(stack) > 0 {
		// Go as far left as possible.
		for current != nil {
			stack = append(stack, current)
			current = current.Left
		}
		// Pop and visit.
		current = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		result = append(result, current.Val)
		// Move to right subtree.
		current = current.Right
	}
	return result
}

func main() {
	//     4
	//    / \
	//   2   5
	//  / \
	// 1   3
	root := &TreeNode{Val: 4}
	root.Left = &TreeNode{Val: 2}
	root.Right = &TreeNode{Val: 5}
	root.Left.Left = &TreeNode{Val: 1}
	root.Left.Right = &TreeNode{Val: 3}

	s := InorderTraversal{}
	fmt.Println("============================================================")
	fmt.Println("Binary Tree Inorder Traversal")
	fmt.Println("============================================================")
	fmt.Printf("Recursive : %v  (expected [1 2 3 4 5])\n", s.InorderRecursive(root))
	fmt.Printf("Iterative : %v  (expected [1 2 3 4 5])\n", s.InorderIterative(root))
}
