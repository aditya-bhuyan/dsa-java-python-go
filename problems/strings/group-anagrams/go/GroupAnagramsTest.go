package main

import "testing"

func TestGroupAnagrams(t *testing.T) {
	ga := &GroupAnagrams{}
	result := ga.GroupAnagrams([]string{"eat","tea","tan","ate","nat","bat"})
	if len(result) != 3 {
		t.Fail()
	}
}
