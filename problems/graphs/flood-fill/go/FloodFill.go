// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Flood Fill (LeetCode 733)
// Approach: DFS recursive flood fill

package main

import "fmt"

// FloodFill holds the solution logic.
type FloodFill struct{}

var floodDirs = [4][2]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

// FloodFill performs a flood fill starting at (sr, sc) using the new color.
// Time: O(m*n)  Space: O(m*n) recursion stack worst-case.
func (f FloodFill) FloodFill(image [][]int, sr, sc, color int) [][]int {
	origColor := image[sr][sc]
	if origColor == color {
		return image // no-op: avoids infinite recursion
	}
	dfsFill(image, sr, sc, origColor, color)
	return image
}

func dfsFill(image [][]int, r, c, origColor, newColor int) {
	if r < 0 || r >= len(image) || c < 0 || c >= len(image[0]) || image[r][c] != origColor {
		return
	}
	image[r][c] = newColor
	for _, d := range floodDirs {
		dfsFill(image, r+d[0], c+d[1], origColor, newColor)
	}
}

// copyImage creates a deep copy of a 2D int slice.
func copyImage(img [][]int) [][]int {
	cp := make([][]int, len(img))
	for i, row := range img {
		cp[i] = make([]int, len(row))
		copy(cp[i], row)
	}
	return cp
}

func main() {
	solver := FloodFill{}

	tests := []struct {
		image    [][]int
		sr, sc   int
		color    int
		expected [][]int
	}{
		{
			[][]int{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2,
			[][]int{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}},
		},
		{
			[][]int{{0, 0, 0}, {0, 0, 0}}, 0, 0, 0,
			[][]int{{0, 0, 0}, {0, 0, 0}},
		},
	}

	for i, tt := range tests {
		result := solver.FloodFill(copyImage(tt.image), tt.sr, tt.sc, tt.color)
		ok := fmt.Sprint(result) == fmt.Sprint(tt.expected)
		status := "PASS"
		if !ok {
			status = "FAIL"
		}
		fmt.Printf("Test %d: %v → %s\n", i+1, result, status)
	}
}
