// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Clone Graph (LeetCode 133)
// Approach: DFS + visited map (original → clone)

package main

import "fmt"

// Node represents a graph node.
type Node struct {
	Val       int
	Neighbors []*Node
}

// CloneGraph holds the solution logic.
type CloneGraph struct{}

// CloneGraph returns a deep copy of the graph rooted at node.
// Time: O(V+E)  Space: O(V)
func (cg CloneGraph) CloneGraph(node *Node) *Node {
	if node == nil {
		return nil
	}
	visited := make(map[*Node]*Node)
	return dfsClone(node, visited)
}

func dfsClone(node *Node, visited map[*Node]*Node) *Node {
	if clone, ok := visited[node]; ok {
		return clone
	}
	clone := &Node{Val: node.Val}
	visited[node] = clone // register BEFORE recursing to break cycles
	for _, nb := range node.Neighbors {
		clone.Neighbors = append(clone.Neighbors, dfsClone(nb, visited))
	}
	return clone
}

// buildGraph constructs a graph from an adjacency list (1-indexed values).
// adjList[i] is the list of neighbor values for node i+1.
func buildGraph(adjList [][]int) *Node {
	if len(adjList) == 0 {
		return nil
	}
	nodes := make([]*Node, len(adjList)+1)
	for i := 1; i <= len(adjList); i++ {
		nodes[i] = &Node{Val: i}
	}
	for i, neighbors := range adjList {
		nodeVal := i + 1
		for _, nb := range neighbors {
			nodes[nodeVal].Neighbors = append(nodes[nodeVal].Neighbors, nodes[nb])
		}
	}
	return nodes[1]
}

// graphToAdjList converts a cloned graph back to an adjacency list for comparison.
func graphToAdjList(node *Node) map[int][]int {
	if node == nil {
		return nil
	}
	result := make(map[int][]int)
	visited := make(map[*Node]bool)
	var dfs func(*Node)
	dfs = func(n *Node) {
		if visited[n] {
			return
		}
		visited[n] = true
		for _, nb := range n.Neighbors {
			result[n.Val] = append(result[n.Val], nb.Val)
		}
		for _, nb := range n.Neighbors {
			dfs(nb)
		}
	}
	dfs(node)
	return result
}

func main() {
	solver := CloneGraph{}

	// Example: 4-node cycle: 1-2-3-4-1, plus 1-4
	original := buildGraph([][]int{{2, 4}, {1, 3}, {2, 4}, {1, 3}})
	clone := solver.CloneGraph(original)

	fmt.Println("Original adjacency:", graphToAdjList(original))
	fmt.Println("Clone adjacency:   ", graphToAdjList(clone))
	fmt.Println("Different objects: ", original != clone)

	// Single node
	single := &Node{Val: 1}
	clonedSingle := solver.CloneGraph(single)
	fmt.Printf("Single node clone: val=%d, neighbors=%v, same_ptr=%v\n",
		clonedSingle.Val, clonedSingle.Neighbors, single == clonedSingle)

	// Nil input
	fmt.Println("Nil clone:", solver.CloneGraph(nil))
}
