/*
===============================================================================
Problem: First Bad Version
===============================================================================
Author: Aditya Bhuyan | Language: Go 1.24+

Template 2 — left boundary on boolean space.
isBadVersion(mid) == true → right = mid (preserve candidate)
isBadVersion(mid) == false → left = mid + 1
Time: O(log n)  Space: O(1)
===============================================================================
*/

package main

import "fmt"

// VersionChecker wraps the isBadVersion API for testability.
type VersionChecker struct {
	firstBad int
}

func (v VersionChecker) IsBadVersion(version int) bool {
	return version >= v.firstBad
}

// FirstBadVersion finds the first bad version using binary search.
func FirstBadVersion(n int, isBadVersion func(int) bool) int {
	left, right := 1, n
	for left < right {
		mid := left + (right-left)/2
		if isBadVersion(mid) {
			right = mid // mid could be the first bad; keep it
		} else {
			left = mid + 1 // mid is good; first bad is to the right
		}
	}
	return left
}

func main() {
	fmt.Println("============================================================")
	fmt.Println("First Bad Version")
	fmt.Println("============================================================")

	vc := VersionChecker{firstBad: 4}
	fmt.Printf("n=5, bad=4 → %d  (expected 4)\n", FirstBadVersion(5, vc.IsBadVersion))

	vc2 := VersionChecker{firstBad: 1}
	fmt.Printf("n=1, bad=1 → %d  (expected 1)\n", FirstBadVersion(1, vc2.IsBadVersion))
}
