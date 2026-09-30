# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Flood Fill (LeetCode 733)
# Approach: BFS iterative flood fill (avoids Python recursion limit)
#
# Dry Run (image=[[1,1,1],[1,1,0],[1,0,1]], sr=1, sc=1, color=2):
# origColor = 1, color = 2 (different → proceed)
# queue = [(1,1)], image[1][1] = 2
# Process (1,1): neighbors (0,1),(2,1),(1,0),(1,2)
#   (0,1)=1→set 2, enqueue; (2,1)=0≠1 skip; (1,0)=1→set 2, enqueue; (1,2)=0≠1 skip
# Process (0,1): neighbors (-1,1)OOB, (1,1)=2≠1, (0,0)=1→set 2, (0,2)=1→set 2
# Process (1,0): neighbors (0,0)=2 already, (2,0)=1→set 2, (1,-1)OOB, (1,1)=2
# Process (0,0): no unvisited 1s
# Process (0,2): no unvisited 1s
# Process (2,0): no unvisited 1s
# Final: [[2,2,2],[2,2,0],[2,0,1]] ✓

from collections import deque
import copy


class FloodFill:
    """
    Solution for Flood Fill using BFS (iterative).

    Time:  O(m * n)      — each pixel visited at most once
    Space: O(min(m, n))  — BFS queue size bounded by perimeter
    """

    DIRS = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def flood_fill(self, image: list[list[int]], sr: int, sc: int, color: int) -> list[list[int]]:
        """
        Perform a flood fill on image starting at (sr, sc) with the given color.

        Args:
            image: m×n pixel grid (mutated in place)
            sr:    starting row
            sc:    starting column
            color: new fill color
        Returns:
            Modified image after flood fill.
        """
        orig_color = image[sr][sc]
        if orig_color == color:
            return image  # no-op: prevents infinite loop

        rows, cols = len(image), len(image[0])
        queue: deque[tuple[int, int]] = deque([(sr, sc)])
        image[sr][sc] = color

        while queue:
            r, c = queue.popleft()
            for dr, dc in self.DIRS:
                nr, nc = r + dr, c + dc
                if 0 <= nr < rows and 0 <= nc < cols and image[nr][nc] == orig_color:
                    image[nr][nc] = color
                    queue.append((nr, nc))

        return image


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = FloodFill()

    test_cases = [
        ([[1, 1, 1], [1, 1, 0], [1, 0, 1]], 1, 1, 2, [[2, 2, 2], [2, 2, 0], [2, 0, 1]]),
        ([[0, 0, 0], [0, 0, 0]], 0, 0, 0, [[0, 0, 0], [0, 0, 0]]),
        ([[1]], 0, 0, 5, [[5]]),
    ]

    for i, (image, sr, sc, color, expected) in enumerate(test_cases, 1):
        result = solver.flood_fill(copy.deepcopy(image), sr, sc, color)
        status = "PASS" if result == expected else "FAIL"
        print(f"Test {i}: {result} → {status}")


if __name__ == "__main__":
    main()
