package main

import "testing"

func TestValidAnagramArray(t *testing.T) {
	va := &ValidAnagram{}
	if !va.IsAnagramArray("anagram", "nagaram") {
		t.Fail()
	}
	if va.IsAnagramArray("rat", "car") {
		t.Fail()
	}
}
