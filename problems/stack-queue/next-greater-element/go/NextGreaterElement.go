/*
===============================================================================
Problem: Next Greater Element
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given nums1 (subset of nums2), for each element in nums1 find the first
greater element to its right in nums2. Return -1 if none exists.

Example

nums1 = [4,1,2]
nums2 = [1,3,4,2]
Output: [-1,3,-1]

===============================================================================

Algorithm — Monotonic Stack + Hash Map
----------------------------------------

Phase 1: Process nums2 with a monotonic decreasing stack (store values).
         When a larger number arrives, it is the "next greater" for the
         popped element. Record in a hash map.

Phase 2: For each element in nums1, look up the hash map in O(1).

===============================================================================

Dry Run
-------

nums2 = [1, 3, 4, 2]

num=1  stack empty → push 1        stack:[1]
num=3  3>1 → pop 1, map[1]=3       stack:[]
       push 3                       stack:[3]
num=4  4>3 → pop 3, map[3]=4       stack:[]
       push 4                       stack:[4]
num=2  2<4 → push 2                stack:[4,2]

Stack remaining: 4,2 → map[4]=-1, map[2]=-1

map = {1:3, 3:4, 4:-1, 2:-1}

nums1 = [4, 1, 2]
answer = [map[4], map[1], map[2]] = [-1, 3, -1]

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(m + n) — one pass over nums2, one pass over nums1.
Space Complexity: O(n)     — map + stack.

===============================================================================
*/

package main

import "fmt"

// NextGreaterElement holds the monotonic stack solution.
type NextGreaterElement struct{}

// FindNextGreater returns the next greater element in nums2 for each value in nums1.
//
// Parameters:
//
//	nums1 - Query values (subset of nums2).
//	nums2 - Reference array.
//
// Returns:
//
//	[]int — next greater element for each nums1[i], or -1 if none.
func (n NextGreaterElement) FindNextGreater(nums1 []int, nums2 []int) []int {

	// Phase 1: build next-greater map from nums2.
	nextGreater := make(map[int]int)
	stack := []int{} // stores values

	for _, num := range nums2 {

		for len(stack) > 0 && num > stack[len(stack)-1] {
			popped := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			nextGreater[popped] = num
		}

		stack = append(stack, num)
	}

	// Remaining in stack have no next greater element.
	for _, num := range stack {
		nextGreater[num] = -1
	}

	// Phase 2: answer each query.
	result := make([]int, len(nums1))
	for i, val := range nums1 {
		result[i] = nextGreater[val]
	}

	return result
}

func main() {

	solution := NextGreaterElement{}

	fmt.Println("============================================================")
	fmt.Println("Next Greater Element")
	fmt.Println("============================================================")

	nums1 := []int{4, 1, 2}
	nums2 := []int{1, 3, 4, 2}
	fmt.Printf("nums1    : %v\n", nums1)
	fmt.Printf("nums2    : %v\n", nums2)
	fmt.Printf("Output   : %v\n", solution.FindNextGreater(nums1, nums2))
	fmt.Printf("Expected : [-1 3 -1]\n")

	fmt.Println()

	nums1b := []int{2, 4}
	nums2b := []int{1, 2, 3, 4}
	fmt.Printf("nums1    : %v\n", nums1b)
	fmt.Printf("nums2    : %v\n", nums2b)
	fmt.Printf("Output   : %v\n", solution.FindNextGreater(nums1b, nums2b))
	fmt.Printf("Expected : [3 -1]\n")
}
