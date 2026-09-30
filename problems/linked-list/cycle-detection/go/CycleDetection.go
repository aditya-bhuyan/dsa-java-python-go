/*
===============================================================================
Problem: Linked List Cycle Detection
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given the head of a linked list, return true if the list has a cycle,
false otherwise.

A cycle exists if some node can be reached again by following next pointers.

Example 1 — Cycle

3 → 2 → 0 → -4 → (points back to node with value 2)
Returns: true

Example 2 — No Cycle

1 → 2 → null
Returns: false

===============================================================================

Algorithm — Floyd's Tortoise and Hare
--------------------------------------

slow moves 1 step per iteration.
fast moves 2 steps per iteration.

If there is a cycle, fast will eventually lap slow and they will meet.
If there is no cycle, fast reaches nil.

Loop:

    while fast != nil && fast.Next != nil:
        slow = slow.Next
        fast = fast.Next.Next

        if slow == fast:
            return true

    return false

===============================================================================

Dry Run — No Cycle
------------------

Input: [1] → [2] → [3] → nil

Initial: slow=[1], fast=[1]

Step 1: slow=[2], fast=[3]   → not equal
Step 2: fast.Next = nil → loop ends

Return false

---------------------------------------

Dry Run — Cycle
---------------

Input: [3] → [2] → [0] → [-4] → (points back to [2])

Initial: slow=[3], fast=[3]

Step 1: slow=[2], fast=[0]   → not equal
Step 2: slow=[0], fast=[2]   → not equal  (fast: [-4] → [2])
Step 3: slow=[-4], fast=[-4] → equal!

Return true

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n)
Space Complexity: O(1)

===============================================================================
*/

package main

import "fmt"

// ListNode represents a node in a singly linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

// CycleDetection holds the cycle detection logic.
type CycleDetection struct{}

// HasCycle returns true if the linked list contains a cycle.
//
// Parameters:
//
//	head - Pointer to the first node of the list.
//
// Returns:
//
//	true if a cycle exists, false otherwise.
func (c CycleDetection) HasCycle(head *ListNode) bool {

	slow := head
	fast := head

	for fast != nil && fast.Next != nil {

		slow = slow.Next
		fast = fast.Next.Next

		// Pointer equality: same node in memory.
		if slow == fast {
			return true
		}
	}

	return false
}

// buildList constructs a linked list from a slice of integers.
// cycleIndex >= 0 creates a cycle by connecting the tail to that index.
// cycleIndex < 0 means no cycle.
func buildList(values []int, cycleIndex int) *ListNode {

	if len(values) == 0 {
		return nil
	}

	nodes := make([]*ListNode, len(values))
	for i, v := range values {
		nodes[i] = &ListNode{Val: v}
	}

	for i := 0; i < len(nodes)-1; i++ {
		nodes[i].Next = nodes[i+1]
	}

	if cycleIndex >= 0 && cycleIndex < len(nodes) {
		nodes[len(nodes)-1].Next = nodes[cycleIndex]
	}

	return nodes[0]
}

func main() {

	solution := CycleDetection{}

	fmt.Println("============================================================")
	fmt.Println("Linked List Cycle Detection")
	fmt.Println("============================================================")

	// No cycle
	list1 := buildList([]int{1, 2, 3, 4}, -1)
	fmt.Printf("No cycle    : hasCycle = %v\n", solution.HasCycle(list1))

	// Cycle: tail → index 1
	list2 := buildList([]int{3, 2, 0, -4}, 1)
	fmt.Printf("With cycle  : hasCycle = %v\n", solution.HasCycle(list2))
}
