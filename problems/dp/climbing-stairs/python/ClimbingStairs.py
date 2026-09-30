"""
=============================================================================
File    : ClimbingStairs.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : Climbing Stairs (LeetCode #70)

Approach : Space-Optimised Dynamic Programming (Fibonacci)
----------
  Observation: f(n) = f(n-1) + f(n-2)  (1-step or 2-step choices)
  Two rolling variables eliminate the O(n) array entirely.

Dry Run  : n = 5
  prev2=1(f1), prev1=2(f2)
  i=3 → curr=1+2=3   prev2=2, prev1=3
  i=4 → curr=2+3=5   prev2=3, prev1=5
  i=5 → curr=3+5=8   prev2=5, prev1=8
  return 8  ✓

Complexity:
  Time  : O(n)
  Space : O(1)
=============================================================================
"""


class ClimbingStairs:
    """Counts distinct ways to climb n stairs taking 1 or 2 steps at a time."""

    def climb_stairs(self, n: int) -> int:
        """
        Returns the number of distinct ways to climb n stairs.

        Args:
            n: number of stairs (1 <= n <= 45)

        Returns:
            count of distinct ways
        """
        if n == 1:
            return 1
        prev2, prev1 = 1, 2          # f(1), f(2)
        for _ in range(3, n + 1):
            prev2, prev1 = prev1, prev2 + prev1
        return prev1


# ─────────────────────────────────────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────────────────────────────────────
def main() -> None:
    solver = ClimbingStairs()
    cases = [1, 2, 3, 4, 5, 10, 20, 45]
    print("=== Climbing Stairs ===")
    for n in cases:
        print(f"climb_stairs({n:2d}) = {solver.climb_stairs(n)}")


if __name__ == "__main__":
    main()
