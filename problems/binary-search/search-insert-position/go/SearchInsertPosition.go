/*
===============================================================================
Problem: Search Insert Position
===============================================================================
Author: Aditya Bhuyan | Language: Go 1.24+

Template 2 — left boundary. Find first index where nums[i] >= target.
Time: O(log n)  Space: O(1)
===============================================================================
*/

package main

import "fmt"

type SearchInsertPosition struct{}

// SearchInsert returns the index of target if found, else the insertion index.
func (s SearchInsertPosition) SearchInsert(nums []int, target int) int {
	left, right := 0, len(nums) // right = n (exclusive) to handle insert-at-end
	for left < right {
		mid := left + (right-left)/2
		if nums[mid] >= target {
			right = mid // mid could be the answer
		} else {
			left = mid + 1 // mid is too small
		}
	}
	return left
}

func main() {
	s := SearchInsertPosition{}
	nums := []int{1, 3, 5, 6}
	fmt.Println("============================================================")
	fmt.Println("Search Insert Position")
	fmt.Println("============================================================")
	fmt.Printf("target=5 → %d  (expected 2)\n", s.SearchInsert(nums, 5))
	fmt.Printf("target=2 → %d  (expected 1)\n", s.SearchInsert(nums, 2))
	fmt.Printf("target=7 → %d  (expected 4)\n", s.SearchInsert(nums, 7))
	fmt.Printf("target=0 → %d  (expected 0)\n", s.SearchInsert(nums, 0))
}
