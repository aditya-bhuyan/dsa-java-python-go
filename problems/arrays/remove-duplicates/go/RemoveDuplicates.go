package main

import (
	"fmt"
)

// RemoveDuplicates removes duplicates from sorted array in-place.
//
// Time Complexity: O(n) - single pass
// Space Complexity: O(1) - in-place modification
type RemoveDuplicates struct{}

// RemoveDups modifies the array in-place and returns the number of unique elements.
func (r *RemoveDuplicates) RemoveDups(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 0
	}

	slow := 0
	for fast := 1; fast < len(nums); fast++ {
		if nums[fast] != nums[slow] {
			slow++
			nums[slow] = nums[fast]
		}
	}

	return slow + 1
}

func main() {
	solver := &RemoveDuplicates{}

	test1 := []int{1, 1, 2}
	k1 := solver.RemoveDups(test1)
	fmt.Printf("Test 1: k=%d (Expected: 2), array=%v\n", k1, test1[:k1])

	test2 := []int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
	k2 := solver.RemoveDups(test2)
	fmt.Printf("Test 2: k=%d (Expected: 5), array=%v\n", k2, test2[:k2])

	test3 := []int{1}
	k3 := solver.RemoveDups(test3)
	fmt.Printf("Test 3: k=%d (Expected: 1)\n", k3)

	test4 := []int{}
	k4 := solver.RemoveDups(test4)
	fmt.Printf("Test 4: k=%d (Expected: 0)\n", k4)
}
