package main

import (
	"fmt"
	"unicode"
)

type Palindrome struct{}

func (p *Palindrome) IsPalindrome(s string) bool {
	left, right := 0, len(s)-1
	for left < right {
		for left < right && !unicode.IsLetter(rune(s[left])) && !unicode.IsDigit(rune(s[left])) {
			left++
		}
		for left < right && !unicode.IsLetter(rune(s[right])) && !unicode.IsDigit(rune(s[right])) {
			right--
		}
		if unicode.ToLower(rune(s[left])) != unicode.ToLower(rune(s[right])) {
			return false
		}
		left++
		right--
	}
	return true
}

func main() {
	p := &Palindrome{}
	fmt.Println(p.IsPalindrome("A man, a plan, a canal: Panama"))
}
