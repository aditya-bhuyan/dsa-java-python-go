package main

import "testing"

func TestIsPalindrome(t *testing.T) {
	p := &Palindrome{}
	if !p.IsPalindrome("A man, a plan, a canal: Panama") {
		t.Fail()
	}
	if p.IsPalindrome("race a car") {
		t.Fail()
	}
	if !p.IsPalindrome(" ") {
		t.Fail()
	}
}
