package main

import (
	"fmt"
	"sort"
)

type ValidAnagram struct{}

// IsAnagramSort checks anagram using sorting. Time: O(n log n)
func (va *ValidAnagram) IsAnagramSort(s, t string) bool {
	if len(s) != len(t) {
		return false
	}
	
	sChars := []rune(s)
	tChars := []rune(t)
	sort.Slice(sChars, func(i, j int) bool { return sChars[i] < sChars[j] })
	sort.Slice(tChars, func(i, j int) bool { return tChars[i] < tChars[j] })
	
	for i := 0; i < len(sChars); i++ {
		if sChars[i] != tChars[i] {
			return false
		}
	}
	return true
}

// IsAnagramArray checks anagram using fixed array. Time: O(n), Space: O(1)
func (va *ValidAnagram) IsAnagramArray(s, t string) bool {
	if len(s) != len(t) {
		return false
	}
	
	freq := [26]int{}
	for _, c := range s {
		freq[c-'a']++
	}
	
	for _, c := range t {
		freq[c-'a']--
		if freq[c-'a'] < 0 {
			return false
		}
	}
	return true
}

// IsAnagramMap checks anagram using map. Time: O(n), Space: O(k)
func (va *ValidAnagram) IsAnagramMap(s, t string) bool {
	if len(s) != len(t) {
		return false
	}
	
	freq := make(map[rune]int)
	for _, c := range s {
		freq[c]++
	}
	
	for _, c := range t {
		if count, ok := freq[c]; !ok || count == 0 {
			return false
		}
		freq[c]--
	}
	return true
}

func main() {
	va := &ValidAnagram{}
	
	tests := []struct {
		s string
		t string
	}{
		{"anagram", "nagaram"},
		{"rat", "car"},
		{"abc", "def"},
		{"", ""},
		{"a", "a"},
	}
	
	for _, test := range tests {
		fmt.Printf("Testing: \"%s\", \"%s\"\n", test.s, test.t)
		fmt.Printf("  Sort: %v\n", va.IsAnagramSort(test.s, test.t))
		fmt.Printf("  Array: %v\n", va.IsAnagramArray(test.s, test.t))
		fmt.Printf("  Map: %v\n", va.IsAnagramMap(test.s, test.t))
	}
}
