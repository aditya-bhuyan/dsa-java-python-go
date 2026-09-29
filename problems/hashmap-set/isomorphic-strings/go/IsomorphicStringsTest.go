package main

import "testing"

func TestIsIsomorphicEggAdd(t *testing.T) {
	if !IsIsomorphic("egg", "add") {
		t.Fail()
	}
}

func TestIsIsomorphicFooBar(t *testing.T) {
	if IsIsomorphic("foo", "bar") {
		t.Fail()
	}
}

func TestIsIsomorphicBadcBaba(t *testing.T) {
	if IsIsomorphic("badc", "baba") {
		t.Fail()
	}
}

func TestIsIsomorphicSingleChar(t *testing.T) {
	if !IsIsomorphic("a", "b") {
		t.Fail()
	}
}

func TestIsIsomorphicPaperTitle(t *testing.T) {
	if !IsIsomorphic("paper", "title") {
		t.Fail()
	}
}
