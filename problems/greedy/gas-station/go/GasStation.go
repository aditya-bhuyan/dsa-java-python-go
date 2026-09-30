/*
===============================================================================
Problem: Gas Station (LeetCode 134)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Go

Problem Statement
-----------------
Given gas[] and cost[] arrays for n stations on a circular route, return the
starting station index from which a full circuit can be completed, or -1 if
no such station exists.

Algorithm
---------
Greedy — single pass with running sum and reset.
diff[i] = gas[i] - cost[i]
Track total (overall sum) and current (tank from candidate start).
If current < 0 at station i, reset: start = i+1, current = 0.
If total >= 0 after full scan, return start; else return -1.

Dry Run
-------
gas=[1,2,3,4,5], cost=[3,4,5,1,2]
diff=[-2,-2,-2,3,3]

i=0: total=-2, current=-2 < 0 → start=1, current=0
i=1: total=-4, current=-2 < 0 → start=2, current=0
i=2: total=-6, current=-2 < 0 → start=3, current=0
i=3: total=-3, current=3
i=4: total=0,  current=6
total=0 >= 0 → return start=3 ✓

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
*/

package main

import "fmt"

// GasStation holds the solution logic.
type GasStation struct{}

// CanCompleteCircuit returns the index of the starting gas station from which
// a full clockwise circuit can be completed, or -1 if impossible.
// Time: O(n)  Space: O(1)
func (g GasStation) CanCompleteCircuit(gas, cost []int) int {
	total, current, start := 0, 0, 0
	for i := 0; i < len(gas); i++ {
		diff := gas[i] - cost[i]
		total += diff
		current += diff
		if current < 0 {
			start = i + 1
			current = 0
		}
	}
	if total >= 0 {
		return start
	}
	return -1
}

func main() {
	solver := GasStation{}

	tests := []struct {
		gas, cost []int
		expected  int
	}{
		{[]int{1, 2, 3, 4, 5}, []int{3, 4, 5, 1, 2}, 3},
		{[]int{2, 3, 4}, []int{3, 4, 3}, -1},
		{[]int{5}, []int{4}, 0},
		{[]int{1}, []int{1}, 0},
		{[]int{3, 1, 1}, []int{1, 2, 2}, 0},
	}

	fmt.Println("============================================================")
	fmt.Println("Gas Station")
	fmt.Println("============================================================")
	for i, tt := range tests {
		result := solver.CanCompleteCircuit(tt.gas, tt.cost)
		ok := result == tt.expected
		fmt.Printf("Test %d: gas=%v cost=%v → %d (expected %d) %s\n",
			i+1, tt.gas, tt.cost, result, tt.expected, passStr(ok))
	}
}

func passStr(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
