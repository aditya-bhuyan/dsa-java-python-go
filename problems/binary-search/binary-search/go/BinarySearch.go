/*
===============================================================================
Problem: Binary Search
===============================================================================
Author: Aditya Bhuyan | Language: Go 1.24+

Template 1 — exact match.
Time: O(log n)  Space: O(1)
===============================================================================
*/

package main

import "fmt"

type BinarySearch struct{}

// Search returns the index of target in the sorted slice, or -1 if not found.
func (b BinarySearch) Search(nums []int, target int) int {
	left, right := 0, len(nums)-1
	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] == target {
			return mid
		} else if nums[mid] < target {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}
	return -1
}

func main() {
	s := BinarySearch{}
	nums := []int{-1, 0, 3, 5, 9, 12}
	fmt.Println("============================================================")
	fmt.Println("Binary Search")
	fmt.Println("============================================================")
	fmt.Printf("search(9)  = %d  (expected 4)\n", s.Search(nums, 9))
	fmt.Printf("search(2)  = %d  (expected -1)\n", s.Search(nums, 2))
}
