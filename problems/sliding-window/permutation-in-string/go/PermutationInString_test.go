// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Permutation in String — Go tests

package main

import "testing"

func TestCheckInclusion(t *testing.T) {
	solver := PermutationInString{}

	tests := []struct {
		name     string
		s1, s2   string
		expected bool
	}{
		{"basic true", "ab", "eidbaooo", true},
		{"basic false", "ab", "eidboaoo", false},
		{"s1 single char present", "a", "ab", true},
		{"s1 single char absent", "z", "ab", false},
		{"permutation at start", "ba", "baooo", true},
		{"s1 longer than s2", "abc", "ab", false},
		{"s1 equals s2", "abc", "abc", true},
		{"adc in dcda", "adc", "dcda", true},
		{"duplicates in s1", "aa", "aab", true},
		{"duplicates not satisfied", "aa", "ab", false},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.CheckInclusion(tt.s1, tt.s2)
			if got != tt.expected {
				t.Errorf("CheckInclusion(%q, %q) = %v, want %v",
					tt.s1, tt.s2, got, tt.expected)
			}
		})
	}
}
