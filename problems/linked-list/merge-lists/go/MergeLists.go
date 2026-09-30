/*
===============================================================================
Problem: Merge Two Sorted Lists
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given the heads of two sorted linked lists list1 and list2,
merge them into one sorted list by splicing together the nodes
of the two lists. Return the head of the merged list.

Example

Input

list1 : 1 → 2 → 4 → nil
list2 : 1 → 3 → 4 → nil

Output

1 → 1 → 2 → 3 → 4 → 4 → nil

===============================================================================

Algorithm
---------

Use a dummy head node and a current pointer.

While both lists have nodes:

    Compare front nodes.
    Attach the smaller one to current.
    Advance that list's pointer.
    Advance current.

After the loop, attach whichever list still has remaining nodes.

Return dummy.Next.

===============================================================================

Dry Run
-------

list1 : [1] → [2] → [4]
list2 : [1] → [3] → [4]

dummy → nil
current = dummy

Step 1: 1 <= 1 → attach list1[1], list1=[2], merged: dummy→[1]
Step 2: 2 >  1 → attach list2[1], list2=[3], merged: dummy→[1]→[1]
Step 3: 2 <= 3 → attach list1[2], list1=[4], merged: dummy→[1]→[1]→[2]
Step 4: 4 >  3 → attach list2[3], list2=[4], merged: dummy→[1]→[1]→[2]→[3]
Step 5: 4 <= 4 → attach list1[4], list1=nil, merged: ...→[4]

list1 nil → attach remaining list2[4]

Result: 1 → 1 → 2 → 3 → 4 → 4 → nil

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(m + n)  — one pass through both lists.
Space Complexity: O(1)      — dummy node + current pointer only.

===============================================================================
*/

package main

import "fmt"

// ListNode represents a node in a singly linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

// MergeLists holds the merge logic.
type MergeLists struct{}

// Merge merges two sorted linked lists and returns the new head.
//
// Parameters:
//
//	list1 - Head of the first sorted list (may be nil).
//	list2 - Head of the second sorted list (may be nil).
//
// Returns:
//
//	*ListNode — head of the merged sorted list.
func (m MergeLists) Merge(list1 *ListNode, list2 *ListNode) *ListNode {

	// Dummy head simplifies edge cases.
	dummy := &ListNode{}
	current := dummy

	// Compare front nodes, attach the smaller.
	for list1 != nil && list2 != nil {

		if list1.Val <= list2.Val {
			current.Next = list1
			list1 = list1.Next
		} else {
			current.Next = list2
			list2 = list2.Next
		}

		current = current.Next
	}

	// Attach the remaining nodes from whichever list is not exhausted.
	if list1 != nil {
		current.Next = list1
	} else {
		current.Next = list2
	}

	return dummy.Next
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

	solution := MergeLists{}

	list1 := buildList([]int{1, 2, 4})
	list2 := buildList([]int{1, 3, 4})

	fmt.Println("============================================================")
	fmt.Println("Merge Two Sorted Lists")
	fmt.Println("============================================================")

	fmt.Print("list1  : ")
	printList(list1)

	fmt.Print("list2  : ")
	printList(list2)

	merged := solution.Merge(list1, list2)

	fmt.Print("Merged : ")
	printList(merged)
}
