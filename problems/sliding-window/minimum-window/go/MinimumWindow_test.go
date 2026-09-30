// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Minimum Window Substring — Go tests

package main

import "testing"

func TestMinWindow(t *testing.T) {
	solver := MinimumWindow{}

	tests := []struct {
		name     string
		s, t     string
		expected string
	}{
		{"example1", "ADOBECODEBANC", "ABC", "BANC"},
		{"exact match", "a", "a", "a"},
		{"t needs two a", "a", "aa", ""},
		{"s equals t repeated", "aa", "aa", "aa"},
		{"single char in t", "ab", "b", "b"},
		{"t not in s", "abc", "d", ""},
		{"empty t", "abc", "", ""},
		{"t longer than s", "a", "ab", ""},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := solver.MinWindow(tt.s, tt.t)
			if got != tt.expected {
				t.Errorf("MinWindow(%q, %q) = %q, want %q",
					tt.s, tt.t, got, tt.expected)
			}
		})
	}
}
