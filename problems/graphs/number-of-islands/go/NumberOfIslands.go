// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Number of Islands (LeetCode 200)
// Approach: DFS Flood Fill — sink each island in-place by marking '1'→'0'

package main

import "fmt"

// NumberOfIslands holds the solution logic.
type NumberOfIslands struct{}

// dirs are the 4 cardinal directions: up, down, left, right.
var dirs = [4][2]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

// NumIslands returns the count of islands in the binary grid.
// An island is a group of horizontally/vertically connected '1' cells.
// Time: O(m*n)  Space: O(m*n) recursion stack worst-case.
func (n NumberOfIslands) NumIslands(grid [][]byte) int {
	if len(grid) == 0 {
		return 0
	}
	count := 0
	for r := 0; r < len(grid); r++ {
		for c := 0; c < len(grid[0]); c++ {
			if grid[r][c] == '1' {
				count++
				dfsIsland(grid, r, c)
			}
		}
	}
	return count
}

// dfsIsland flood-fills the island rooted at (r,c) by sinking every reachable '1' to '0'.
func dfsIsland(grid [][]byte, r, c int) {
	if r < 0 || r >= len(grid) || c < 0 || c >= len(grid[0]) || grid[r][c] != '1' {
		return
	}
	grid[r][c] = '0' // mark visited
	for _, d := range dirs {
		dfsIsland(grid, r+d[0], c+d[1])
	}
}

// copyGrid creates a deep copy of a byte grid so the original is not mutated by tests.
func copyGrid(grid [][]byte) [][]byte {
	cp := make([][]byte, len(grid))
	for i, row := range grid {
		cp[i] = make([]byte, len(row))
		copy(cp[i], row)
	}
	return cp
}

func main() {
	solver := NumberOfIslands{}

	grids := [][][]byte{
		{
			{'1', '1', '1', '1', '0'},
			{'1', '1', '0', '1', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '0', '0', '0'},
		},
		{
			{'1', '1', '0', '0', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '1', '0', '0'},
			{'0', '0', '0', '1', '1'},
		},
		{{'0'}},
		{{'1'}},
	}
	expected := []int{1, 3, 0, 1}

	for i, g := range grids {
		result := solver.NumIslands(copyGrid(g))
		fmt.Printf("Test %d: NumIslands = %d (expected %d) → %s\n",
			i+1, result, expected[i], pass(result == expected[i]))
	}
}

func pass(ok bool) string {
	if ok {
		return "PASS"
	}
	return "FAIL"
}
