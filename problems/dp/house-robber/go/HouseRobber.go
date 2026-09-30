/*
===============================================================================
Problem: House Robber (LeetCode 198)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Rob non-adjacent houses to maximise total money. Return the max amount.

Algorithm
---------
Space-optimised bottom-up DP.
dp[i] = max(dp[i-1], dp[i-2] + nums[i])

Two rolling variables:
  prev2 ← best up to i-2
  prev1 ← best up to i-1
  curr  ← max(prev1, prev2 + nums[i])

Dry Run (nums=[2,7,9,3,1])
--------------------------
prev2=0, prev1=2
i=1(7):  curr=max(2, 0+7)=7,  prev2=2, prev1=7
i=2(9):  curr=max(7, 2+9)=11, prev2=7, prev1=11
i=3(3):  curr=max(11,7+3)=11, prev2=11,prev1=11
i=4(1):  curr=max(11,11+1)=12,prev2=11,prev1=12
return 12 ✓

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
*/

package main

import "fmt"

// HouseRobber holds the solution logic.
type HouseRobber struct{}

// Rob returns the maximum amount that can be robbed without robbing
// two adjacent houses.
// Time: O(n)  Space: O(1)
func (h HouseRobber) Rob(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	if len(nums) == 1 {
		return nums[0]
	}
	prev2, prev1 := 0, nums[0]
	for i := 1; i < len(nums); i++ {
		curr := prev1
		if prev2+nums[i] > curr {
			curr = prev2 + nums[i]
		}
		prev2 = prev1
		prev1 = curr
	}
	return prev1
}

func main() {
	solver := HouseRobber{}

	tests := []struct {
		nums     []int
		expected int
	}{
		{[]int{1, 2, 3, 1}, 4},
		{[]int{2, 7, 9, 3, 1}, 12},
		{[]int{5}, 5},
		{[]int{2, 9}, 9},
		{[]int{1, 2, 3, 4, 5}, 9},
		{[]int{0, 0, 0}, 0},
		{[]int{3, 3, 3, 3}, 6},
	}

	fmt.Println("============================================================")
	fmt.Println("House Robber")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.Rob(tt.nums)
		ok := result == tt.expected
		fmt.Printf("Test %d: nums=%-20v → %d (expected %d) %s\n",
			i+1, tt.nums, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
