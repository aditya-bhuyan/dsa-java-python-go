package main

import "testing"

func TestFirstUniqCharLeetcode(t *testing.T) {
	if FirstUniqChar("leetcode") != 0 {
		t.Fail()
	}
}

func TestFirstUniqCharLoveleetcode(t *testing.T) {
	if FirstUniqChar("loveleetcode") != 2 {
		t.Fail()
	}
}

func TestFirstUniqCharAllDuplicates(t *testing.T) {
	if FirstUniqChar("aabb") != -1 {
		t.Fail()
	}
}

func TestFirstUniqCharSingle(t *testing.T) {
	if FirstUniqChar("a") != 0 {
		t.Fail()
	}
}

func TestFirstUniqCharUniqueAtEnd(t *testing.T) {
	if FirstUniqChar("aabbc") != 4 {
		t.Fail()
	}
}

func TestFirstUniqCharArray(t *testing.T) {
	if FirstUniqCharArray("leetcode") != 0 {
		t.Fail()
	}
}
