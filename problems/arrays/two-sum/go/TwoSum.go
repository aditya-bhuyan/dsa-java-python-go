/*
===============================================================================
Problem: Two Sum
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Given an integer slice 'nums' and an integer 'target',
return the indices of the two numbers such that they add up to
the target.

Assumptions

1. Exactly one valid solution exists.
2. The same element cannot be used twice.
3. The answer may be returned in any order.

Example

Input

nums   = [2, 7, 11, 15]
target = 9

Output

[0, 1]

Explanation

nums[0] + nums[1]

2 + 7 = 9

===============================================================================

Algorithm
---------

Use a Hash Map.

The Hash Map stores

Number -> Index

For every element

1. Calculate the complement.

       complement = target - currentNumber

2. Check whether the complement exists.

3. If yes

       return both indices.

4. Otherwise

       store the current number.

===============================================================================

Dry Run
-------

nums = [2,7,11,15]

target = 9

Iteration 1

Current Number = 2

Complement = 7

Map

{}

Store

2 -> 0

------------------------------------

Iteration 2

Current Number = 7

Complement = 2

Map

{
    2 : 0
}

Found

Return

[0,1]

===============================================================================

Complexity Analysis
-------------------

Time Complexity

O(n)

Every element is visited exactly once.

Space Complexity

O(n)

The hash map stores at most n elements.

===============================================================================
*/

package main

import (
	"errors"
	"fmt"
)

// TwoSum represents the implementation of the Two Sum algorithm.
type TwoSum struct{}

// TwoSum finds the indices of two numbers whose sum equals the target.
//
// Parameters:
//
//	nums   - Input slice of integers.
//	target - Desired sum.
//
// Returns:
//
//	[]int containing the indices of the matching pair.
//
// If no valid solution exists, an error is returned.
func (s TwoSum) TwoSum(nums []int, target int) ([]int, error) {

	// Hash Map Structure
	//
	// Key   -> Number
	// Value -> Index
	numberToIndex := make(map[int]int)

	// Traverse the slice once.
	for index, currentNumber := range nums {

		// Calculate the complement.
		complement := target - currentNumber

		// Check whether the complement
		// already exists.
		if previousIndex, found := numberToIndex[complement]; found {

			return []int{
				previousIndex,
				index,
			}, nil
		}

		// Store the current number.
		numberToIndex[currentNumber] = index
	}

	// Defensive programming.
	return nil, errors.New("no valid solution exists")
}

func main() {

	solution := TwoSum{}

	numbers := []int{
		2,
		7,
		11,
		15,
	}

	target := 9

	result, err := solution.TwoSum(numbers, target)

	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println("============================================================")
	fmt.Println("Two Sum")
	fmt.Println("============================================================")

	fmt.Printf("Input Slice : %v\n", numbers)
	fmt.Printf("Target      : %d\n", target)
	fmt.Printf("Indices     : %v\n", result)

	fmt.Printf(
		"Values      : %d + %d = %d\n",
		numbers[result[0]],
		numbers[result[1]],
		target,
	)
}
