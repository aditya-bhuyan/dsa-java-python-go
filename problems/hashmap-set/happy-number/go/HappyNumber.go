package main

// Approach 1: Map (HashSet) Cycle Detection - Optimal
// Time: O(log n * m), Space: O(m)
func IsHappy(n int) bool {
	seen := make(map[int]bool)
	for n != 1 && !seen[n] {
		seen[n] = true
		n = sumOfSquares(n)
	}
	return n == 1
}

// Approach 2: Floyd's Cycle Detection
// Time: O(log n * m), Space: O(1)
func IsHappyFloyd(n int) bool {
	slow := n
	fast := sumOfSquares(n)
	
	for fast != 1 && slow != fast {
		slow = sumOfSquares(slow)
		fast = sumOfSquares(sumOfSquares(fast))
	}
	
	return fast == 1
}

// Helper function to calculate sum of squares of digits
func sumOfSquares(n int) int {
	sum := 0
	for n > 0 {
		digit := n % 10
		sum += digit * digit
		n /= 10
	}
	return sum
}
