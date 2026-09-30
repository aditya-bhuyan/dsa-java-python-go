/*
===============================================================================
Problem: Minimum Window Substring (LeetCode 76)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Return the minimum window substring of s that contains all characters of t.

Algorithm
---------
Variable sliding window + match counter.
1. Build need[c] from t.  required = distinct chars in t.
2. Expand right: add s[right] to have; if have[c]==need[c], matches++.
3. When matches==required, shrink left:
   - Record window if smallest so far.
   - Remove s[left] from have; if drops below need, matches--.
   - left++.
4. Repeat until right reaches end.

Dry Run (abbreviated)
---------------------
s="ADOBECODEBANC", t="ABC"
Expand to "ADOBEC" → valid (matches=3), best="ADOBEC"(len=6)
Shrink 'A' → invalid; expand to include 'A' again...
Eventually best="BANC" (len=4)

Complexity
----------
Time:  O(m + n)  m=len(s), n=len(t)
Space: O(|Σ|)
===============================================================================
*/

package main

import "fmt"

// MinimumWindow holds the solution logic.
type MinimumWindow struct{}

// MinWindow returns the shortest substring of s containing all chars of t.
// Returns "" if no such window exists.
// Time: O(m+n)  Space: O(|Σ|)
func (mw MinimumWindow) MinWindow(s, t string) string {
	if len(s) == 0 || len(t) == 0 {
		return ""
	}

	need := make(map[byte]int)
	for i := 0; i < len(t); i++ {
		need[t[i]]++
	}
	required := len(need)

	have := make(map[byte]int)
	matches := 0
	left := 0
	bestLen := len(s) + 1
	bestLeft := 0

	for right := 0; right < len(s); right++ {
		c := s[right]
		have[c]++
		if cnt, ok := need[c]; ok && have[c] == cnt {
			matches++
		}
		for matches == required {
			if right-left+1 < bestLen {
				bestLen = right - left + 1
				bestLeft = left
			}
			lc := s[left]
			have[lc]--
			if cnt, ok := need[lc]; ok && have[lc] < cnt {
				matches--
			}
			left++
		}
	}

	if bestLen > len(s) {
		return ""
	}
	return s[bestLeft : bestLeft+bestLen]
}

func main() {
	solver := MinimumWindow{}

	tests := []struct {
		s, t     string
		expected string
	}{
		{"ADOBECODEBANC", "ABC", "BANC"},
		{"a", "a", "a"},
		{"a", "aa", ""},
		{"aa", "aa", "aa"},
		{"ab", "b", "b"},
	}

	fmt.Println("============================================================")
	fmt.Println("Minimum Window Substring")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.MinWindow(tt.s, tt.t)
		ok := result == tt.expected
		fmt.Printf("Test %d: s=%q t=%q → %q (expected %q) %s\n",
			i+1, tt.s, tt.t, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
