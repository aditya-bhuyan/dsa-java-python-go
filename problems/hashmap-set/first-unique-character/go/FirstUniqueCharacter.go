package main

// Approach 1: Map Count - Optimal
// Time: O(n), Space: O(1)
func FirstUniqChar(s string) int {
	count := make(map[rune]int)
	
	// Count frequencies
	for _, c := range s {
		count[c]++
	}
	
	// Find first unique
	for i, c := range s {
		if count[c] == 1 {
			return i
		}
	}
	
	return -1
}

// Approach 2: Array Index (Lowercase English)
// Time: O(n), Space: O(1)
func FirstUniqCharArray(s string) int {
	count := [26]int{}
	
	// Count frequencies
	for _, c := range s {
		count[c-'a']++
	}
	
	// Find first unique
	for i, c := range s {
		if count[c-'a'] == 1 {
			return i
		}
	}
	
	return -1
}
