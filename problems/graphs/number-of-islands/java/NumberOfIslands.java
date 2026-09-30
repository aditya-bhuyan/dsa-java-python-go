// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Number of Islands (LeetCode 200)
// Approach: DFS Flood Fill

package graphs.numberofislands;

/**
 * Solution for Number of Islands.
 *
 * <p>Strategy: DFS Flood Fill.
 * Scan every cell; when a '1' is found, increment the island count and
 * recursively sink the entire connected component by setting cells to '0'.
 *
 * <p>Time:  O(m * n)
 * Space: O(m * n)  — recursion stack in worst case (all land grid)
 */
public class NumberOfIslands {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Returns the number of islands in the given binary grid.
     *
     * @param grid m x n grid of '0' (water) and '1' (land)
     * @return count of islands
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }
        return count;
    }

    /** Flood-fills the island rooted at (r, c) by sinking every reachable '1' to '0'. */
    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }
        grid[r][c] = '0';
        for (int[] d : DIRS) {
            dfs(grid, r + d[0], c + d[1]);
        }
    }

    // -------------------------------------------------------------------------
    // Helper — deep copy a char[][] so test data is not mutated
    // -------------------------------------------------------------------------
    public static char[][] copyGrid(char[][] grid) {
        char[][] cp = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            cp[i] = grid[i].clone();
        }
        return cp;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        char[][][] grids = {
            {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
            },
            {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
            },
            {{'0'}},
            {{'1'}}
        };
        int[] expected = {1, 3, 0, 1};

        for (int i = 0; i < grids.length; i++) {
            int result = solver.numIslands(copyGrid(grids[i]));
            System.out.printf("Test %d: numIslands = %d (expected %d) → %s%n",
                i + 1, result, expected[i], result == expected[i] ? "PASS" : "FAIL");
        }
    }
}
