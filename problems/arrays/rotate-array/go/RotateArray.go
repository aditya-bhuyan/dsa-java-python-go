package main

import (
	"fmt"
)

// RotateArray rotates array to the right by k steps.
//
// Time Complexity: O(n) - three passes
// Space Complexity: O(1) - in-place modification
type RotateArray struct{}

// Rotate modifies the array in-place to rotate it right by k steps.
func (r *RotateArray) Rotate(nums []int, k int) {
	if nums == nil || len(nums) <= 1 {
		return
	}

	// Normalize k
	k = k % len(nums)
	if k == 0 {
		return
	}

	// Reverse entire array
	r.reverse(nums, 0, len(nums)-1)
	// Reverse first k elements
	r.reverse(nums, 0, k-1)
	// Reverse remaining elements
	r.reverse(nums, k, len(nums)-1)
}

// Helper method to reverse array segment
func (r *RotateArray) reverse(nums []int, start, end int) {
	for start < end {
		nums[start], nums[end] = nums[end], nums[start]
		start++
		end--
	}
}

func main() {
	solver := &RotateArray{}

	test1 := []int{1, 2, 3, 4, 5, 6, 7}
	solver.Rotate(test1, 3)
	fmt.Printf("Test 1: %v (Expected: [5 6 7 1 2 3 4])\n", test1)

	test2 := []int{-1, -100, 3, 99}
	solver.Rotate(test2, 2)
	fmt.Printf("Test 2: %v (Expected: [3 99 -1 -100])\n", test2)

	test3 := []int{1}
	solver.Rotate(test3, 0)
	fmt.Printf("Test 3: %v (Expected: [1])\n", test3)

	test4 := []int{1, 2}
	solver.Rotate(test4, 3)
	fmt.Printf("Test 4: %v (Expected: [2 1])\n", test4)
}
