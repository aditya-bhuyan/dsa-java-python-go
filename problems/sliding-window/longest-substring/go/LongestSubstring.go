/*
===============================================================================
Problem: Longest Substring Without Repeating Characters (LeetCode 3)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Given a string s, find the length of the longest substring without
repeating characters.

Algorithm
---------
Variable sliding window with an index map (char → last seen index).
For each s[right]:
  - If s[right] is in map AND its index >= left, jump left = seen+1.
  - Update seen[s[right]] = right.
  - Update best = max(best, right-left+1).

Dry Run
-------
s = "abcabcbb"

right=0 'a': seen={},        left=0, window="a",   best=1
right=1 'b': seen={'a':0},   left=0, window="ab",  best=2
right=2 'c': seen+,          left=0, window="abc", best=3
right=3 'a': seen['a']=0>=0, left=1, window="bca", best=3
right=4 'b': seen['b']=1>=1, left=2, window="cab", best=3
right=5 'c': seen['c']=2>=2, left=3, window="abc", best=3
right=6 'b': seen['b']=4>=3, left=5, window="cb",  best=3
right=7 'b': seen['b']=6>=5, left=7, window="b",   best=3

Answer: 3

Complexity
----------
Time:  O(n)
Space: O(min(n, |Σ|))
===============================================================================
*/

package main

import "fmt"

// LongestSubstring holds the solution logic.
type LongestSubstring struct{}

// LengthOfLongestSubstring returns the length of the longest substring
// without any repeating characters.
// Time: O(n)  Space: O(min(n, |Σ|))
func (l LongestSubstring) LengthOfLongestSubstring(s string) int {
	seen := make(map[byte]int)
	left, best := 0, 0
	for right := 0; right < len(s); right++ {
		if idx, ok := seen[s[right]]; ok && idx >= left {
			left = idx + 1
		}
		seen[s[right]] = right
		if right-left+1 > best {
			best = right - left + 1
		}
	}
	return best
}

func main() {
	solver := LongestSubstring{}

	tests := []struct {
		s        string
		expected int
	}{
		{"abcabcbb", 3},
		{"bbbbb", 1},
		{"pwwkew", 3},
		{"", 0},
		{"a", 1},
		{"abcdefg", 7},
		{" ", 1},
	}

	fmt.Println("============================================================")
	fmt.Println("Longest Substring Without Repeating Characters")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.LengthOfLongestSubstring(tt.s)
		ok := result == tt.expected
		fmt.Printf("Test %d: s=%q → %d (expected %d) %s\n",
			i+1, tt.s, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
