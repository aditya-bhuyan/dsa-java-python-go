/*
===============================================================================
Problem: Permutation in String (LeetCode 567)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Return true if s2 contains a permutation of s1.

Algorithm
---------
Fixed sliding window of size len(s1) over s2.
Use [26]int arrays for O(1) frequency operations (lowercase letters only).
Track matches = number of chars with have[c]==need[c].
When matches==required, a permutation window is found.

Dry Run
-------
s1="ab", s2="eidbaooo"
need=['a':1,'b':1], required=2, k=2

right=0 'e': have['e']=1, 'e' not in need
right=1 'i': have['i']=1, 'i' not in need; right>=k? no
right=2 'd': have['d']=1; remove s2[0]='e' (not in need)
right=3 'b': have['b']=1==need['b'] → matches=1; remove 'i' (not in need)
right=4 'a': have['a']=1==need['a'] → matches=2; remove 'd' (not in need)
  matches==required → return true ✓

Complexity
----------
Time:  O(m + n)
Space: O(26) = O(1)
===============================================================================
*/

package main

import "fmt"

// PermutationInString holds the solution logic.
type PermutationInString struct{}

// CheckInclusion returns true if any permutation of s1 appears as a
// contiguous substring of s2.
// Time: O(m+n)  Space: O(1) — fixed 26-char alphabet
func (p PermutationInString) CheckInclusion(s1, s2 string) bool {
	if len(s1) > len(s2) {
		return false
	}

	var need, have [26]int
	for i := 0; i < len(s1); i++ {
		need[s1[i]-'a']++
	}

	required := 0
	for _, v := range need {
		if v > 0 {
			required++
		}
	}

	matches := 0
	k := len(s1)

	for right := 0; right < len(s2); right++ {
		c := s2[right] - 'a'
		have[c]++
		if have[c] == need[c] {
			matches++
		}
		if right >= k {
			lc := s2[right-k] - 'a'
			if have[lc] == need[lc] {
				matches--
			}
			have[lc]--
		}
		if matches == required {
			return true
		}
	}
	return false
}

func main() {
	solver := PermutationInString{}

	tests := []struct {
		s1, s2   string
		expected bool
	}{
		{"ab", "eidbaooo", true},
		{"ab", "eidboaoo", false},
		{"a", "ab", true},
		{"abc", "bbbca", true},
		{"hello", "ooolleoooleh", false},
		{"adc", "dcda", true},
	}

	fmt.Println("============================================================")
	fmt.Println("Permutation in String")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.CheckInclusion(tt.s1, tt.s2)
		ok := result == tt.expected
		fmt.Printf("Test %d: s1=%q s2=%q → %v (expected %v) %s\n",
			i+1, tt.s1, tt.s2, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
