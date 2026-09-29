package main

// Approach 1: Map (HashSet equivalent) - Optimal
// Time: O(n), Space: O(n)
func ContainsDuplicate(nums []int) bool {
	seen := make(map[int]bool)
	for _, num := range nums {
		if seen[num] {
			return true
		}
		seen[num] = true
	}
	return false
}

// Approach 2: Map with struct{} (More idiomatic Go)
// Time: O(n), Space: O(n)
func ContainsDuplicateStruct(nums []int) bool {
	seen := make(map[int]struct{})
	for _, num := range nums {
		if _, exists := seen[num]; exists {
			return true
		}
		seen[num] = struct{}{}
	}
	return false
}

// Approach 3: Manual sorting check
// Time: O(n log n), Space: O(1)
func ContainsDuplicateSorting(nums []int) bool {
	// Note: This modifies the input array
	// Use with caution in production code
	for i := 0; i < len(nums)-1; i++ {
		for j := i + 1; j < len(nums); j++ {
			if nums[i] == nums[j] {
				return true
			}
		}
	}
	return false
}
