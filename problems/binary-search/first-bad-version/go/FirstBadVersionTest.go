package main

import "testing"

func TestFirstBadVersion(t *testing.T) {
	tests := []struct{ n, bad, want int }{
		{5, 4, 4},
		{1, 1, 1},
		{5, 1, 1},
		{5, 5, 5},
		{10, 6, 6},
		{2147483647, 2147483647, 2147483647}, // large n, no overflow
	}
	for _, tc := range tests {
		vc := VersionChecker{firstBad: tc.bad}
		got := FirstBadVersion(tc.n, vc.IsBadVersion)
		if got != tc.want {
			t.Errorf("n=%d bad=%d: got %d, want %d", tc.n, tc.bad, got, tc.want)
		}
	}
}
