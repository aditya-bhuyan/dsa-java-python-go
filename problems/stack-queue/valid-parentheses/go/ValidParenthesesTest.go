package main

import "testing"

func TestValidParentheses(t *testing.T) {

	solver := ValidParentheses{}

	tests := []struct {
		name     string
		input    string
		expected bool
	}{
		{"Single pair", "()", true},
		{"Multiple pairs sequential", "()[]{}", true},
		{"Nested", "{[]}", true},
		{"Deeply nested", "{[()]}", true},
		{"Mismatched type", "(]", false},
		{"Wrong order", "([)]", false},
		{"Unclosed open", "(", false},
		{"Extra close", ")", false},
		{"Empty string", "", true},
		{"Only open brackets", "((({{{", false},
		{"Only close brackets", ")))]]]", false},
		{"Odd length", "([)", false},
		{"Long valid", "(((({}))))", true},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			result := solver.IsValid(tc.input)
			if result != tc.expected {
				t.Fatalf(
					"IsValid(%q) = %v, expected %v",
					tc.input, result, tc.expected,
				)
			}
		})
	}
}
