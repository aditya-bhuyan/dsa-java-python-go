package main

import (
	"fmt"
)

// MoveZeroes moves all zeros to the end while maintaining relative order.
//
// Time Complexity: O(n) - single pass
// Space Complexity: O(1) - in-place modification
type MoveZeroes struct{}

// MoveZeros modifies the array in-place to move all zeros to the end.
func (m *MoveZeroes) MoveZeros(nums []int) {
	if nums == nil || len(nums) == 0 {
		return
	}

	pos := 0 // Position for next non-zero element

	// Move all non-zero elements forward
	for i := 0; i < len(nums); i++ {
		if nums[i] != 0 {
			nums[pos] = nums[i]
			pos++
		}
	}

	// Fill remaining positions with zeros
	for pos < len(nums) {
		nums[pos] = 0
		pos++
	}
}

func main() {
	solver := &MoveZeroes{}

	test1 := []int{0, 1, 0, 3, 12}
	solver.MoveZeros(test1)
	fmt.Printf("Test 1: %v (Expected: [1 3 12 0 0])\n", test1)

	test2 := []int{0}
	solver.MoveZeros(test2)
	fmt.Printf("Test 2: %v (Expected: [0])\n", test2)

	test3 := []int{1, 2, 3}
	solver.MoveZeros(test3)
	fmt.Printf("Test 3: %v (Expected: [1 2 3])\n", test3)

	test4 := []int{0, 0, 1}
	solver.MoveZeros(test4)
	fmt.Printf("Test 4: %v (Expected: [1 0 0])\n", test4)
}
