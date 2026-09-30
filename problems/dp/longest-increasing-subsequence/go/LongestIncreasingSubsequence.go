/*
===============================================================================
Problem: Longest Increasing Subsequence (LeetCode 300)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Return the length of the longest strictly increasing subsequence of nums.

Algorithm (O(n log n))
-----------------------
Maintain a sorted 'tails' slice where tails[i] = smallest tail element
of all increasing subsequences of length i+1.

For each num:
  - lowerBound(tails, num) = first index where tails[pos] >= num
  - If pos == len(tails): extend (num is larger than all tails)
  - Else: replace tails[pos] = num (smaller tail → better future extensions)

Answer = len(tails)

Dry Run (nums=[10,9,2,5,3,7,101,18])
-------------------------------------
10  → tails=[10]
9   → replace pos=0 → tails=[9]
2   → replace pos=0 → tails=[2]
5   → extend        → tails=[2,5]
3   → replace pos=1 → tails=[2,3]
7   → extend        → tails=[2,3,7]
101 → extend        → tails=[2,3,7,101]
18  → replace pos=3 → tails=[2,3,7,18]
len=4 ✓

Complexity
----------
Time:  O(n log n)
Space: O(n)
===============================================================================
*/

package main

import "fmt"

// LongestIncreasingSubsequence holds the solution logic.
type LongestIncreasingSubsequence struct{}

// LengthOfLIS returns the length of the longest strictly increasing
// subsequence using the O(n log n) tails / binary-search approach.
// Time: O(n log n)  Space: O(n)
func (l LongestIncreasingSubsequence) LengthOfLIS(nums []int) int {
	tails := []int{}
	for _, num := range nums {
		pos := lowerBound(tails, num) // first index where tails[pos] >= num
		if pos == len(tails) {
			tails = append(tails, num)
		} else {
			tails[pos] = num
		}
	}
	return len(tails)
}

// lowerBound returns the first index i in sorted slice s where s[i] >= target.
func lowerBound(s []int, target int) int {
	lo, hi := 0, len(s)
	for lo < hi {
		mid := (lo + hi) / 2
		if s[mid] < target {
			lo = mid + 1
		} else {
			hi = mid
		}
	}
	return lo
}

// LengthOfLISNSquared is the O(n²) DP approach for reference / verification.
// dp[i] = length of LIS ending at index i.
func (l LongestIncreasingSubsequence) LengthOfLISNSquared(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	for i := range dp {
		dp[i] = 1
	}
	best := 1
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] && dp[j]+1 > dp[i] {
				dp[i] = dp[j] + 1
			}
		}
		if dp[i] > best {
			best = dp[i]
		}
	}
	return best
}

func main() {
	solver := LongestIncreasingSubsequence{}

	tests := []struct {
		nums     []int
		expected int
	}{
		{[]int{10, 9, 2, 5, 3, 7, 101, 18}, 4},
		{[]int{0, 1, 0, 3, 2, 3}, 4},
		{[]int{7, 7, 7, 7}, 1},
		{[]int{1}, 1},
		{[]int{1, 2, 3, 4, 5}, 5},
		{[]int{5, 4, 3, 2, 1}, 1},
		{[]int{-3, -2, -1, 0}, 4},
	}

	fmt.Println("============================================================")
	fmt.Println("Longest Increasing Subsequence")
	fmt.Println("============================================================")
	for i, tt := range tests {
		r1 := solver.LengthOfLIS(tt.nums)
		r2 := solver.LengthOfLISNSquared(tt.nums)
		ok := r1 == tt.expected && r2 == tt.expected
		fmt.Printf("Test %d: nums=%-30v → O(nlogn)=%d O(n²)=%d (expected %d) %s\n",
			i+1, tt.nums, r1, r2, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
