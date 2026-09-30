/*
===============================================================================
Problem: Jump Game (LeetCode 55)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Given an integer array nums where each element is the maximum jump length
from that position, return true if you can reach the last index starting
from index 0.

Algorithm
---------
Greedy — track maximum reachable index.
For each index i:
  - If i > maxReach → unreachable → return false.
  - maxReach = max(maxReach, i + nums[i]).
Return true.

Dry Run
-------
nums = [2,3,1,1,4]

i=0: maxReach=max(0,0+2)=2
i=1: maxReach=max(2,1+3)=4
i=2: maxReach=max(4,2+1)=4
i=3: maxReach=max(4,3+1)=4
i=4: maxReach=max(4,4+4)=8
return true ✓

nums = [3,2,1,0,4]

i=0: maxReach=3
i=1: maxReach=3
i=2: maxReach=3
i=3: maxReach=3
i=4: 4 > 3 → return false ✓

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
*/

package main

import "fmt"

// JumpGame holds the solution logic.
type JumpGame struct{}

// CanJump returns true if the last index of nums is reachable from index 0.
// Time: O(n)  Space: O(1)
func (j JumpGame) CanJump(nums []int) bool {
	maxReach := 0
	for i, v := range nums {
		if i > maxReach {
			return false
		}
		if i+v > maxReach {
			maxReach = i + v
		}
	}
	return true
}

func main() {
	solver := JumpGame{}

	tests := []struct {
		nums     []int
		expected bool
	}{
		{[]int{2, 3, 1, 1, 4}, true},
		{[]int{3, 2, 1, 0, 4}, false},
		{[]int{0}, true},
		{[]int{1, 0}, true},
		{[]int{0, 1}, false},
		{[]int{1, 1, 1, 1}, true},
		{[]int{2, 0, 0}, true},
	}

	fmt.Println("============================================================")
	fmt.Println("Jump Game")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.CanJump(tt.nums)
		ok := result == tt.expected
		fmt.Printf("Test %d: nums=%v → %v (expected %v) %s\n",
			i+1, tt.nums, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
