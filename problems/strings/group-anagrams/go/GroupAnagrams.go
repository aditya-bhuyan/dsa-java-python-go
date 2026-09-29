package main

import (
	"fmt"
	"sort"
)

type GroupAnagrams struct{}

func (ga *GroupAnagrams) GroupAnagrams(strs []string) [][]string {
	anagramMap := make(map[string][]string)
	
	for _, s := range strs {
		chars := []byte(s)
		sort.Slice(chars, func(i, j int) bool { return chars[i] < chars[j] })
		key := string(chars)
		anagramMap[key] = append(anagramMap[key], s)
	}
	
	result := make([][]string, 0)
	for _, group := range anagramMap {
		result = append(result, group)
	}
	return result
}

func main() {
	ga := &GroupAnagrams{}
	result := ga.GroupAnagrams([]string{"eat","tea","tan","ate","nat","bat"})
	fmt.Println(result)
}
