# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Number of Islands (LeetCode 200)
# Approach: BFS Flood Fill (iterative — avoids Python recursion limit on large grids)
#
# Dry Run (Example 2 — three islands):
# grid = [["1","1","0","0","0"],
#         ["1","1","0","0","0"],
#         ["0","0","1","0","0"],
#         ["0","0","0","1","1"]]
#
# Step 1: (0,0)='1' → count=1, BFS sinks top-left 2×2 block
# Step 2: scan zeros until (2,2)='1' → count=2, BFS sinks that cell
# Step 3: scan zeros until (3,3)='1' → count=3, BFS sinks (3,3) and (3,4)
# Result: 3

from collections import deque
import copy


class NumberOfIslands:
    """
    Solution for Number of Islands using BFS Flood Fill.

    Time:  O(m * n)  — each cell enqueued / dequeued at most once
    Space: O(min(m, n))  — BFS queue holds at most the perimeter of an island
    """

    DIRS = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def num_islands(self, grid: list[list[str]]) -> int:
        """
        Return the number of islands in the binary grid.

        Args:
            grid: m×n list of lists containing '0' (water) or '1' (land).
                  The grid is mutated in place (visited cells set to '0').
        Returns:
            Number of islands (connected components of '1').
        """
        if not grid:
            return 0

        rows, cols = len(grid), len(grid[0])
        count = 0

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == '1':
                    count += 1
                    self._bfs(grid, r, c, rows, cols)

        return count

    def _bfs(self, grid: list[list[str]], r: int, c: int, rows: int, cols: int) -> None:
        """BFS flood-fill: sink the entire island rooted at (r, c)."""
        queue: deque[tuple[int, int]] = deque()
        queue.append((r, c))
        grid[r][c] = '0'  # mark visited immediately on enqueue

        while queue:
            row, col = queue.popleft()
            for dr, dc in self.DIRS:
                nr, nc = row + dr, col + dc
                if 0 <= nr < rows and 0 <= nc < cols and grid[nr][nc] == '1':
                    grid[nr][nc] = '0'
                    queue.append((nr, nc))


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = NumberOfIslands()

    test_cases = [
        (
            [["1","1","1","1","0"],
             ["1","1","0","1","0"],
             ["1","1","0","0","0"],
             ["0","0","0","0","0"]],
            1,
        ),
        (
            [["1","1","0","0","0"],
             ["1","1","0","0","0"],
             ["0","0","1","0","0"],
             ["0","0","0","1","1"]],
            3,
        ),
        ([["0"]], 0),
        ([["1"]], 1),
    ]

    for i, (grid, expected) in enumerate(test_cases, 1):
        result = solver.num_islands(copy.deepcopy(grid))
        status = "PASS" if result == expected else "FAIL"
        print(f"Test {i}: num_islands = {result} (expected {expected}) → {status}")


if __name__ == "__main__":
    main()
