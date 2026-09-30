/*
===============================================================================
Problem: Min Stack
===============================================================================

Author   : Aditya Bhuyan
Language : Go 1.24+

Problem Statement
-----------------
Design a stack supporting push, pop, top, and getMin — all in O(1).

===============================================================================

Approach — Auxiliary Min Stack
--------------------------------

Maintain two slices:
  main    — all pushed values.
  minStack — running minimum at each level.

On push(val):
  append val to main.
  append min(val, top of minStack) to minStack.

On pop():
  remove last from both slices.

top()    → last of main
getMin() → last of minStack

===============================================================================

Dry Run
-------

push(-2)   main:[-2]       minStack:[-2]
push(0)    main:[-2,0]     minStack:[-2,-2]
push(-3)   main:[-2,0,-3]  minStack:[-2,-2,-3]
getMin()   → -3
pop()      main:[-2,0]     minStack:[-2,-2]
top()      → 0
getMin()   → -2

===============================================================================

Complexity Analysis
-------------------

All operations: O(1) time.
Space         : O(n) total.

===============================================================================
*/

package main

import "fmt"

// MinStack implements a stack with O(1) getMin support.
type MinStack struct {
	main     []int
	minStack []int
}

// NewMinStack constructs and returns an empty MinStack.
func NewMinStack() *MinStack {
	return &MinStack{}
}

// Push adds val to the stack.
func (s *MinStack) Push(val int) {
	s.main = append(s.main, val)

	if len(s.minStack) == 0 || val < s.minStack[len(s.minStack)-1] {
		s.minStack = append(s.minStack, val)
	} else {
		s.minStack = append(s.minStack, s.minStack[len(s.minStack)-1])
	}
}

// Pop removes the top element.
func (s *MinStack) Pop() {
	s.main = s.main[:len(s.main)-1]
	s.minStack = s.minStack[:len(s.minStack)-1]
}

// Top returns the top element without removing it.
func (s *MinStack) Top() int {
	return s.main[len(s.main)-1]
}

// GetMin returns the minimum element in the stack.
func (s *MinStack) GetMin() int {
	return s.minStack[len(s.minStack)-1]
}

func main() {

	ms := NewMinStack()

	ms.Push(-2)
	ms.Push(0)
	ms.Push(-3)

	fmt.Println("============================================================")
	fmt.Println("Min Stack")
	fmt.Println("============================================================")

	fmt.Printf("After push(-2), push(0), push(-3)\n")
	fmt.Printf("getMin() = %d  (expected -3)\n", ms.GetMin())

	ms.Pop()
	fmt.Printf("After pop()\n")
	fmt.Printf("top()    = %d  (expected 0)\n", ms.Top())
	fmt.Printf("getMin() = %d  (expected -2)\n", ms.GetMin())
}
