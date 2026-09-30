/*
===============================================================================
Problem: Search in Rotated Sorted Array
===============================================================================
Author: Aditya Bhuyan | Language: Go 1.24+

One half is always sorted. Use that to decide which half contains target.
Time: O(log n)  Space: O(1)
===============================================================================
*/

package main

import "fmt"

type SearchRotatedArray struct{}

// Search returns the index of target in the rotated sorted slice, or -1.
func (s SearchRotatedArray) Search(nums []int, target int) int {
	left, right := 0, len(nums)-1

	for left <= right {
		mid := left + (right-left)/2

		if nums[mid] == target {
			return mid
		}

		// Determine which half is sorted.
		if nums[left] <= nums[mid] { // left half is sorted
			if nums[left] <= target && target < nums[mid] {
				right = mid - 1 // target in sorted left half
			} else {
				left = mid + 1 // target in right half
			}
		} else { // right half is sorted
			if nums[mid] < target && target <= nums[right] {
				left = mid + 1 // target in sorted right half
			} else {
				right = mid - 1 // target in left half
			}
		}
	}
	return -1
}

func main() {
	s := SearchRotatedArray{}
	fmt.Println("============================================================")
	fmt.Println("Search in Rotated Sorted Array")
	fmt.Println("============================================================")
	fmt.Printf("[4,5,6,7,0,1,2] target=0 → %d  (expected 4)\n", s.Search([]int{4, 5, 6, 7, 0, 1, 2}, 0))
	fmt.Printf("[4,5,6,7,0,1,2] target=3 → %d  (expected -1)\n", s.Search([]int{4, 5, 6, 7, 0, 1, 2}, 3))
	fmt.Printf("[1] target=0             → %d  (expected -1)\n", s.Search([]int{1}, 0))
}
