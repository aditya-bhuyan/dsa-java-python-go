/*
===============================================================================
Problem: Maximum Average Subarray I (LeetCode 643)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Find a contiguous subarray of length k with the maximum average value.
Return that maximum average.

Algorithm
---------
Fixed sliding window of size k.
1. Compute sum of the first k elements.
2. Slide right: sum += nums[right] - nums[right-k].
3. Track maximum sum throughout.
4. Return maxSum / k.

Dry Run
-------
nums = [1, 12, -5, -6, 50, 3],  k = 4

Initial: sum = 1+12-5-6 = 2,  maxSum = 2
right=4: sum = 2 + 50 - 1 = 51, maxSum = 51
right=5: sum = 51 + 3 - 12 = 42, maxSum = 51

return 51 / 4 = 12.75

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
*/

package main

import "fmt"

// MaximumAverage holds the solution logic.
type MaximumAverage struct{}

// FindMaxAverage returns the maximum average of any contiguous subarray of length k.
// Time: O(n)  Space: O(1)
func (m MaximumAverage) FindMaxAverage(nums []int, k int) float64 {
	sum := 0
	for i := 0; i < k; i++ {
		sum += nums[i]
	}
	maxSum := sum
	for right := k; right < len(nums); right++ {
		sum += nums[right] - nums[right-k]
		if sum > maxSum {
			maxSum = sum
		}
	}
	return float64(maxSum) / float64(k)
}

func main() {
	solver := MaximumAverage{}

	tests := []struct {
		nums     []int
		k        int
		expected float64
	}{
		{[]int{1, 12, -5, -6, 50, 3}, 4, 12.75},
		{[]int{5}, 1, 5.0},
		{[]int{-1, -12, -5, -6}, 2, -6.0},
		{[]int{0, 4, 0, 3, 2}, 1, 4.0},
	}

	fmt.Println("============================================================")
	fmt.Println("Maximum Average Subarray I")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.FindMaxAverage(tt.nums, tt.k)
		ok := abs(result-tt.expected) < 1e-5
		fmt.Printf("Test %d: nums=%v k=%d → %.5f (expected %.5f) %s\n",
			i+1, tt.nums, tt.k, result, tt.expected, passStr(ok))
	}
}

func abs(x float64) float64 {
	if x < 0 {
		return -x
	}
	return x
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
