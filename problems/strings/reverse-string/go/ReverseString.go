package main

import "fmt"

type ReverseString struct{}

func (rs *ReverseString) ReverseStr(s []byte) {
	left, right := 0, len(s)-1
	for left < right {
		s[left], s[right] = s[right], s[left]
		left++
		right--
	}
}

func main() {
	rs := &ReverseString{}
	s := []byte{'h', 'e', 'l', 'l', 'o'}
	rs.ReverseStr(s)
	fmt.Println(string(s))
}
