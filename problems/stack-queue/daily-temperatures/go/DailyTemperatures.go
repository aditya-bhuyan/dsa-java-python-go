/*
===============================================================================
Problem: Daily Temperatures
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given temperatures[], return answer[] where answer[i] = number of days
to wait after day i for a warmer temperature.

Example

Input  : [73,74,75,71,69,72,76,73]
Output : [1,1,4,2,1,1,0,0]

===============================================================================

Algorithm — Monotonic Decreasing Stack
----------------------------------------

Store indices in the stack. The temperatures at those indices are in
decreasing order from bottom to top.

For each day i:

  While the stack is not empty and temperatures[i] > temperatures[stack.top()]:
      prevIdx = stack.pop()
      answer[prevIdx] = i - prevIdx

  stack.push(i)

Remaining indices in the stack → answer stays 0.

===============================================================================

Dry Run
-------

temps = [73,74,75,71,69,72,76,73]
        i=0 1  2  3  4  5  6  7

i=0 T=73 push 0              stack:[0]
i=1 T=74 74>73 pop 0→ans[0]=1, push 1    stack:[1]
i=2 T=75 75>74 pop 1→ans[1]=1, push 2    stack:[2]
i=3 T=71 push 3              stack:[2,3]
i=4 T=69 push 4              stack:[2,3,4]
i=5 T=72 72>69 pop 4→ans[4]=1
         72>71 pop 3→ans[3]=2, push 5    stack:[2,5]
i=6 T=76 76>72 pop 5→ans[5]=1
         76>75 pop 2→ans[2]=4, push 6    stack:[6]
i=7 T=73 push 7              stack:[6,7]

ans = [1,1,4,2,1,1,0,0]

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n) — each index pushed/popped at most once.
Space Complexity: O(n) — stack + answer array.

===============================================================================
*/

package main

import "fmt"

// DailyTemperatures holds the monotonic stack solution.
type DailyTemperatures struct{}

// WaitDays returns an array where answer[i] is the number of days until
// a warmer temperature after day i (0 if none).
//
// Parameters:
//
//	temperatures - Slice of daily temperatures.
//
// Returns:
//
//	[]int — wait-day counts.
func (d DailyTemperatures) WaitDays(temperatures []int) []int {

	n := len(temperatures)
	answer := make([]int, n)
	stack := []int{} // stores indices

	for i := 0; i < n; i++ {

		// While the current day is warmer than the day at the top of the stack:
		for len(stack) > 0 && temperatures[i] > temperatures[stack[len(stack)-1]] {

			prevIdx := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			answer[prevIdx] = i - prevIdx
		}

		stack = append(stack, i)
	}

	// Remaining indices in the stack have no warmer future day (answer stays 0).
	return answer
}

func main() {

	solution := DailyTemperatures{}

	temps := []int{73, 74, 75, 71, 69, 72, 76, 73}

	fmt.Println("============================================================")
	fmt.Println("Daily Temperatures")
	fmt.Println("============================================================")

	fmt.Printf("Input  : %v\n", temps)
	fmt.Printf("Output : %v\n", solution.WaitDays(temps))
	fmt.Printf("Expected: [1 1 4 2 1 1 0 0]\n")
}
