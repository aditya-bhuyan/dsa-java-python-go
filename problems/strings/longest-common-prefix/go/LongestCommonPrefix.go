package main

import (
	"fmt"
	"sort"
	"strings"
)

type LongestCommonPrefix struct{}

func (lcp *LongestCommonPrefix) LongestPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
	
	minLen := len(strs[0])
	for _, s := range strs {
		if len(s) < minLen {
			minLen = len(s)
		}
	}
	
	for i := 0; i < minLen; i++ {
		c := strs[0][i]
		for _, s := range strs {
			if s[i] != c {
				return strs[0][:i]
			}
		}
	}
	return strs[0][:minLen]
}

func main() {
	lcp := &LongestCommonPrefix{}
	fmt.Println(lcp.LongestPrefix([]string{"flower","flow","flight"}))
}
