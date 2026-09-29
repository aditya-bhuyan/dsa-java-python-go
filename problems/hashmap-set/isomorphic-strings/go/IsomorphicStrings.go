package main

// Approach 1: Dual Map - Optimal
// Time: O(n), Space: O(1) - bounded by alphabet
func IsIsomorphic(s string, t string) bool {
	sToT := make(map[rune]rune)
	tToS := make(map[rune]rune)
	
	sRunes := []rune(s)
	tRunes := []rune(t)
	
	for i := 0; i < len(sRunes); i++ {
		sChar := sRunes[i]
		tChar := tRunes[i]
		
		// Check s -> t mapping
		if val, exists := sToT[sChar]; exists {
			if val != tChar {
				return false
			}
		} else {
			sToT[sChar] = tChar
		}
		
		// Check t -> s mapping (bijection)
		if val, exists := tToS[tChar]; exists {
			if val != sChar {
				return false
			}
		} else {
			tToS[tChar] = sChar
		}
	}
	
	return true
}

// Approach 2: Pattern Transformation
// Time: O(n), Space: O(n)
func IsIsomorphicPattern(s string, t string) bool {
	return getPattern(s) == getPattern(t)
}

func getPattern(s string) string {
	charMap := make(map[rune]int)
	nextID := 0
	pattern := ""
	
	for _, c := range s {
		if id, exists := charMap[c]; exists {
			pattern += string(rune('0' + id))
		} else {
			charMap[c] = nextID
			pattern += string(rune('0' + nextID))
			nextID++
		}
	}
	
	return pattern
}
