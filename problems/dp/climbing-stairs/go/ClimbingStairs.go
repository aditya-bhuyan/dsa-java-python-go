/*
===============================================================================
Problem: Climbing Stairs (LeetCode 70)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
You can climb 1 or 2 steps at a time. In how many distinct ways
can you climb n stairs?

Algorithm
---------
Space-optimised bottom-up DP.
f(n) = f(n-1) + f(n-2)  (Fibonacci recurrence)

Two rolling variables replace the full DP array:
  prev2 ← f(i-2)
  prev1 ← f(i-1)
  curr  ← prev1 + prev2

Dry Run (n=5)
-------------
prev2=1, prev1=1
i=2: curr=2, prev2=1, prev1=2
i=3: curr=3, prev2=2, prev1=3
i=4: curr=5, prev2=3, prev1=5
i=5: curr=8, prev2=5, prev1=8
return 8 ✓

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
*/

package main

import "fmt"

// ClimbingStairs holds the solution logic.
type ClimbingStairs struct{}

// ClimbStairs returns the number of distinct ways to climb n stairs
// when each step allows climbing 1 or 2 stairs.
// Time: O(n)  Space: O(1)
func (c ClimbingStairs) ClimbStairs(n int) int {
	if n <= 1 {
		return 1
	}
	prev2, prev1 := 1, 1
	for i := 2; i <= n; i++ {
		curr := prev1 + prev2
		prev2 = prev1
		prev1 = curr
	}
	return prev1
}

func main() {
	solver := ClimbingStairs{}

	tests := []struct {
		n        int
		expected int
	}{
		{1, 1},
		{2, 2},
		{3, 3},
		{4, 5},
		{5, 8},
		{10, 89},
		{45, 1836311903},
	}

	fmt.Println("============================================================")
	fmt.Println("Climbing Stairs")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.ClimbStairs(tt.n)
		ok := result == tt.expected
		fmt.Printf("Test %d: n=%-3d → %d (expected %d) %s\n",
			i+1, tt.n, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
