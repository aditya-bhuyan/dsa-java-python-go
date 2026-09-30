// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Longest Substring Without Repeating Characters — Go tests

package main

import "testing"

func TestLengthOfLongestSubstring(t *testing.T) {
	solver := LongestSubstring{}

	tests := []struct {
		name     string
		s        string
		expected int
	}{
		{"abcabcbb", "abcabcbb", 3},
		{"all same", "bbbbb", 1},
		{"pwwkew", "pwwkew", 3},
		{"empty string", "", 0},
		{"single char", "a", 1},
		{"all unique", "abcdefg", 7},
		{"space", " ", 1},
		{"two spaces", "  ", 1},
		{"special chars", "!@#!@#", 3},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.LengthOfLongestSubstring(tt.s)
			if got != tt.expected {
				t.Errorf("LengthOfLongestSubstring(%q) = %d, want %d",
					tt.s, got, tt.expected)
			}
		})
	}
}
