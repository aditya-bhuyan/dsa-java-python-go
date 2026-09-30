/*
===============================================================================
Problem: Assign Cookies (LeetCode 455)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Given children's greed factors g[] and cookie sizes s[], assign each child
at most one cookie where s[j] >= g[i]. Maximise the number of content children.

Algorithm
---------
Sort both arrays ascending.
Two pointers child and cookie:
  - If s[cookie] >= g[child]: child satisfied → child++
  - Always: cookie++
Return child (count of satisfied children).

Dry Run
-------
g=[1,2,3], s=[1,1,2]  (sorted)

cookie=0: s[0]=1 >= g[0]=1 → child=1, cookie=1
cookie=1: s[1]=1 < g[1]=2  → cookie=2
cookie=2: s[2]=2 >= g[1]=2 → child=2, cookie=3
return 2 ✓

Complexity
----------
Time:  O(n log n + m log m)
Space: O(1)
===============================================================================
*/

package main

import (
	"fmt"
	"sort"
)

// AssignCookies holds the solution logic.
type AssignCookies struct{}

// FindContentChildren returns the maximum number of content children.
// Time: O(n log n + m log m)  Space: O(1)
func (a AssignCookies) FindContentChildren(g, s []int) int {
	sort.Ints(g)
	sort.Ints(s)
	child, cookie := 0, 0
	for child < len(g) && cookie < len(s) {
		if s[cookie] >= g[child] {
			child++
		}
		cookie++
	}
	return child
}

func main() {
	solver := AssignCookies{}

	tests := []struct {
		g, s     []int
		expected int
	}{
		{[]int{1, 2, 3}, []int{1, 1}, 1},
		{[]int{1, 2}, []int{1, 2, 3}, 2},
		{[]int{10, 9, 8, 7}, []int{5, 6, 7, 8}, 2},
		{[]int{1, 2, 3}, []int{}, 0},
		{[]int{}, []int{1, 2, 3}, 0},
		{[]int{1}, []int{1}, 1},
	}

	fmt.Println("============================================================")
	fmt.Println("Assign Cookies")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.FindContentChildren(append([]int{}, tt.g...), append([]int{}, tt.s...))
		ok := result == tt.expected
		fmt.Printf("Test %d: g=%v s=%v → %d (expected %d) %s\n",
			i+1, tt.g, tt.s, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
