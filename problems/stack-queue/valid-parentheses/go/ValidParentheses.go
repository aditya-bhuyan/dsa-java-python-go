/*
===============================================================================
Problem: Valid Parentheses
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given a string s containing only '(', ')', '{', '}', '[', ']',
determine if the string is valid.

Valid means:
  1. Every open bracket is closed by the same type of bracket.
  2. Open brackets are closed in the correct order.
  3. Every close bracket has a corresponding open bracket.

Example 1

Input  : "()"
Output : true

Example 2

Input  : "([)]"
Output : false

===============================================================================

Algorithm
---------

Use a stack.

For each character:

  - Open bracket  → push onto stack.
  - Close bracket →
      - If stack is empty        → return false.
      - Pop the top.
      - If top doesn't match     → return false.

After the loop, return true only if the stack is empty.

Matching pairs:
  ) matches (
  ] matches [
  } matches {

===============================================================================

Dry Run
-------

Input: "{[()]}"

{ → open → push       stack: [ { ]
[ → open → push       stack: [ { [ ]
( → open → push       stack: [ { [ ( ]
) → close, top=(  → match! pop   stack: [ { [ ]
] → close, top=[  → match! pop   stack: [ { ]
} → close, top={  → match! pop   stack: []

Stack empty → return true

---------------------------------------

Input: "([)]"

( → open → push       stack: [ ( ]
[ → open → push       stack: [ ( [ ]
) → close, top=[  → NO match → return false

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n)  — each character processed once.
Space Complexity: O(n)  — stack holds at most n/2 open brackets.

===============================================================================
*/

package main

import "fmt"

// ValidParentheses holds the bracket validation logic.
type ValidParentheses struct{}

// IsValid returns true if the bracket string is valid, false otherwise.
//
// Parameters:
//
//	s - Input string containing only bracket characters.
//
// Returns:
//
//	bool indicating validity.
func (v ValidParentheses) IsValid(s string) bool {

	// Matching map: close bracket → expected open bracket.
	matching := map[rune]rune{
		')': '(',
		']': '[',
		'}': '{',
	}

	stack := []rune{}

	for _, ch := range s {

		switch ch {
		case '(', '[', '{':
			// Open bracket — push.
			stack = append(stack, ch)

		default:
			// Close bracket.
			if len(stack) == 0 {
				return false
			}

			top := stack[len(stack)-1]
			stack = stack[:len(stack)-1]

			if top != matching[ch] {
				return false
			}
		}
	}

	return len(stack) == 0
}

func main() {

	solution := ValidParentheses{}

	cases := []struct {
		input    string
		expected bool
	}{
		{"()", true},
		{"()[]{}", true},
		{"(]", false},
		{"([)]", false},
		{"{[]}", true},
		{"", true},
	}

	fmt.Println("============================================================")
	fmt.Println("Valid Parentheses")
	fmt.Println("============================================================")

	for _, tc := range cases {
		result := solution.IsValid(tc.input)
		status := "✓"
		if result != tc.expected {
			status = "✗"
		}
		fmt.Printf("%s  input=%-12q  result=%-5v  expected=%v\n",
			status, tc.input, result, tc.expected)
	}
}
