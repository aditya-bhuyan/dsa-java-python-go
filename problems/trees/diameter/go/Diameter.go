/*
===============================================================================
Problem: Diameter of Binary Tree
===============================================================================
Author   : Aditya Bhuyan  |  Language : Go 1.24+

diameter at node x = leftHeight(x) + rightHeight(x)
Track global max across all nodes during postorder DFS.
Time: O(n)  Space: O(h)
===============================================================================
*/

package main

import "fmt"

type TreeNode struct{ Val int; Left, Right *TreeNode }

type DiameterOfBinaryTree struct {
	maxDiameter int
}

// DiameterOfTree returns the diameter (longest path, in edges) of the binary tree.
func (d *DiameterOfBinaryTree) DiameterOfTree(root *TreeNode) int {
	d.maxDiameter = 0
	d.depth(root)
	return d.maxDiameter
}

// depth returns the height of the subtree and updates maxDiameter as a side-effect.
func (d *DiameterOfBinaryTree) depth(node *TreeNode) int {
	if node == nil { return 0 }
	left  := d.depth(node.Left)
	right := d.depth(node.Right)
	if left+right > d.maxDiameter {
		d.maxDiameter = left + right
	}
	if left > right { return 1 + left }
	return 1 + right
}

func n(v int) *TreeNode { return &TreeNode{Val: v} }

func main() {
	//       1
	//      / \
	//     2   3
	//    / \
	//   4   5
	root := &TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2, Left: n(4), Right: n(5)}
	root.Right = n(3)

	d := &DiameterOfBinaryTree{}
	fmt.Println("============================================================")
	fmt.Println("Diameter of Binary Tree")
	fmt.Println("============================================================")
	fmt.Printf("Diameter: %d  (expected 3)\n", d.DiameterOfTree(root))
}
