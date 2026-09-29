package main

import "testing"

func TestLongestCommonPrefix(t *testing.T) {
	lcp := &LongestCommonPrefix{}
	result := lcp.LongestPrefix([]string{"flower","flow","flight"})
	if result != "fl" {
		t.Fail()
	}
}
