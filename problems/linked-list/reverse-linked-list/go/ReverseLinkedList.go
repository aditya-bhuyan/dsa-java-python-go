/*
===============================================================================
Problem: Reverse Linked List
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given the head of a singly linked list, reverse the list and
return the new head.

Example

Input

1 → 2 → 3 → 4 → 5 → null

Output

5 → 4 → 3 → 2 → 1 → null

===============================================================================

Algorithm
---------

Use three pointers: prev, current, next.

1. prev    starts as nil.
2. current starts at head.

For each node:

    1. Save    next         = current.next
    2. Rewire  current.next = prev
    3. Advance prev         = current
    4. Advance current      = next

When current becomes nil, prev points to the new head.

===============================================================================

Dry Run
-------

Input: [1] → [2] → [3] → nil

prev = nil, current = [1]

Step 1
  next         = [2]
  current.next = nil        → [1] → nil
  prev         = [1]
  current      = [2]

Step 2
  next         = [3]
  current.next = [1]        → [2] → [1] → nil
  prev         = [2]
  current      = [3]

Step 3
  next         = nil
  current.next = [2]        → [3] → [2] → [1] → nil
  prev         = [3]
  current      = nil

Return prev = [3]

===============================================================================

Complexity Analysis
-------------------

Time Complexity

O(n) — each node is visited exactly once.

Space Complexity

O(1) — only three pointer variables, regardless of list length.

===============================================================================
*/

package main

import "fmt"

// ListNode represents a node in a singly linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

// ReverseLinkedList holds the reversal logic.
type ReverseLinkedList struct{}

// Reverse reverses a singly linked list and returns the new head.
//
// Parameters:
//
//	head - Pointer to the first node of the list (may be nil).
//
// Returns:
//
//	*ListNode pointing to the new head of the reversed list.
func (r ReverseLinkedList) Reverse(head *ListNode) *ListNode {

	var prev *ListNode
	current := head

	for current != nil {

		// Save the next node before overwriting.
		next := current.Next

		// Reverse the pointer.
		current.Next = prev

		// Advance both pointers.
		prev = current
		current = next
	}

	// prev is now the new head.
	return prev
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

// printList prints the linked list in a readable format.
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

	solution := ReverseLinkedList{}

	values := []int{1, 2, 3, 4, 5}

	head := buildList(values)

	fmt.Println("============================================================")
	fmt.Println("Reverse Linked List")
	fmt.Println("============================================================")

	fmt.Print("Input  : ")
	printList(head)

	reversed := solution.Reverse(head)

	fmt.Print("Output : ")
	printList(reversed)
}
