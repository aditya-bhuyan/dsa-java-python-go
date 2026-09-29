package main

import "testing"

func TestIsHappy7(t *testing.T) {
	if !IsHappy(7) {
		t.Fail()
	}
}

func TestIsUnhappy2(t *testing.T) {
	if IsHappy(2) {
		t.Fail()
	}
}

func TestIsHappy19(t *testing.T) {
	if !IsHappy(19) {
		t.Fail()
	}
}

func TestIsHappy1(t *testing.T) {
	if !IsHappy(1) {
		t.Fail()
	}
}

func TestIsHappyFloyd(t *testing.T) {
	if !IsHappyFloyd(7) || IsHappyFloyd(2) {
		t.Fail()
	}
}
