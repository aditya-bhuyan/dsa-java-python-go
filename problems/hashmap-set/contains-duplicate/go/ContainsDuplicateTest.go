package main

import "testing"

func TestContainsDuplicateDuplicateAtStart(t *testing.T) {
	if !ContainsDuplicate([]int{1, 2, 3, 1}) {
		t.Fail()
	}
}

func TestContainsDuplicateAllUnique(t *testing.T) {
	if ContainsDuplicate([]int{1, 2, 3, 4}) {
		t.Fail()
	}
}

func TestContainsDuplicateAdjacentDuplicates(t *testing.T) {
	if !ContainsDuplicate([]int{99, 99}) {
		t.Fail()
	}
}

func TestContainsDuplicateSingleElement(t *testing.T) {
	if ContainsDuplicate([]int{1}) {
		t.Fail()
	}
}

func TestContainsDuplicateNegativeNumbers(t *testing.T) {
	if !ContainsDuplicate([]int{-1, -1, 0, 1}) {
		t.Fail()
	}
}
