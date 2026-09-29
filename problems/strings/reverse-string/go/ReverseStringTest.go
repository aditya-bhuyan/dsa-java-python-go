package main

import (
	"reflect"
	"testing"
)

func TestReverseString(t *testing.T) {
	rs := &ReverseString{}
	s := []byte{'h', 'e', 'l', 'l', 'o'}
	rs.ReverseStr(s)
	expected := []byte{'o', 'l', 'l', 'e', 'h'}
	if !reflect.DeepEqual(s, expected) {
		t.Fail()
	}
}
