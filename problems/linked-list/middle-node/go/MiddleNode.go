/*
===============================================================================
Problem: Middle of the Linked List
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given the head of a singly linked list, return the middle node.
If there are two middle nodes, return the second middle node.

Example 1

Input  : [1] → [2] → [3] → [4] → [5] → nil
Output : [3]

Example 2

Input  : [1] → [2] → [3] → [4] → [5] → [6] → nil
Output : [4]   (second middle)

===============================================================================

Algorithm
---------

Fast and Slow Pointer.

slow moves 1 step at a time.
fast moves 2 steps at a time.

When fast reaches nil (or fast.Next is nil), slow is at the middle.

Loop condition

    for fast != nil && fast.Next != nil

===============================================================================

Dry Run (odd length)
--------------------

Input: [1] → [2] → [3] → [4] → [5] → nil

Initial: slow=[1], fast=[1]

Step 1: slow=[2], fast=[3]
Step 2: slow=[3], fast=[5]

fast.Next = nil → loop stops

Return slow = [3]

---------------------------------------

Dry Run (even length)
---------------------

Input: [1] → [2] → [3] → [4] → [5] → [6] → nil

Initial: slow=[1], fast=[1]

Step 1: slow=[2], fast=[3]
Step 2: slow=[3], fast=[5]
Step 3: slow=[4], fast=nil   (fast moved to [5].Next.Next = nil)

Loop stops

Return slow = [4]

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n) — single pass.
Space Complexity: O(1) — two pointer variables only.

===============================================================================
*/

package main

import "fmt"

// ListNode represents a node in a singly linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

// MiddleNode holds the fast/slow pointer logic.
type MiddleNode struct{}

// FindMiddle returns the middle node of the linked list.
// For even-length lists it returns the second middle node.
//
// Parameters:
//
//	head - Pointer to the first node of the list.
//
// Returns:
//
//	*ListNode pointing to the middle node.
func (m MiddleNode) FindMiddle(head *ListNode) *ListNode {

	slow := head
	fast := head

	for fast != nil && fast.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}

	return slow
}

// buildList constructs a linked list from a slice of integers.
func buildList(values []int) *ListNode {

	if len(values) == 0 {
		return nil
	}

	head := &ListNode{Val: values[0]}
	current := head

	for _, v := range values[1:] {
		current.Next = &ListNode{Val: v}
		current = current.Next
	}

	return head
}

// printList prints the linked list.
func printList(head *ListNode) {

	for head != nil {
		if head.Next != nil {
			fmt.Printf("%d → ", head.Val)
		} else {
			fmt.Printf("%d → null\n", head.Val)
		}
		head = head.Next
	}
}

func main() {

	solution := MiddleNode{}

	fmt.Println("============================================================")
	fmt.Println("Middle of the Linked List")
	fmt.Println("============================================================")

	// Odd-length list
	list1 := buildList([]int{1, 2, 3, 4, 5})
	fmt.Print("Input  : ")
	printList(list1)
	middle1 := solution.FindMiddle(list1)
	fmt.Printf("Middle : %d\n", middle1.Val)

	fmt.Println()

	// Even-length list
	list2 := buildList([]int{1, 2, 3, 4, 5, 6})
	fmt.Print("Input  : ")
	printList(list2)
	middle2 := solution.FindMiddle(list2)
	fmt.Printf("Middle : %d\n", middle2.Val)
}
