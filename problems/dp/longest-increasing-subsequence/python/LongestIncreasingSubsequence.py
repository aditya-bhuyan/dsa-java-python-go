"""
=============================================================================
File    : LongestIncreasingSubsequence.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : Longest Increasing Subsequence (LeetCode #300)

Two approaches:

1) Patience Sort / bisect  O(n log n) — length_of_lis()
   Maintain a `tails` list where tails[i] = smallest tail element
   of all increasing subsequences of length i+1.
   For each num: bisect_left gives the replacement position.

Dry Run  : [10,9,2,5,3,7,101,18]
   10 → tails=[10]
    9 → tails=[9]
    2 → tails=[2]
    5 → tails=[2,5]
    3 → tails=[2,3]
    7 → tails=[2,3,7]
  101 → tails=[2,3,7,101]
   18 → tails=[2,3,7,18]   length=4  ✓

2) Classic DP  O(n²) — length_of_lis_dp()
   dp[i] = length of LIS ending at index i

Complexity:
  length_of_lis    : Time O(n log n), Space O(n)
  length_of_lis_dp : Time O(n²),      Space O(n)
=============================================================================
"""

import bisect
from typing import List


class LongestIncreasingSubsequence:
    """Computes the length of the longest strictly increasing subsequence."""

    def length_of_lis(self, nums: List[int]) -> int:
        """
        Patience-sort / bisect approach — O(n log n).

        Args:
            nums: input list of integers

        Returns:
            length of the longest strictly increasing subsequence
        """
        tails: List[int] = []
        for num in nums:
            pos = bisect.bisect_left(tails, num)
            if pos == len(tails):
                tails.append(num)
            else:
                tails[pos] = num
        return len(tails)

    def length_of_lis_dp(self, nums: List[int]) -> int:
        """
        Classic DP approach — O(n²).

        Args:
            nums: input list of integers

        Returns:
            length of the longest strictly increasing subsequence
        """
        if not nums:
            return 0
        n = len(nums)
        dp = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)


# ─────────────────────────────────────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────────────────────────────────────
def main() -> None:
    solver = LongestIncreasingSubsequence()
    print("=== Longest Increasing Subsequence ===")

    a = [10, 9, 2, 5, 3, 7, 101, 18]
    print(f"O(n log n): {solver.length_of_lis(a)}")    # 4
    print(f"O(n²)     : {solver.length_of_lis_dp(a)}") # 4

    b = [0, 1, 0, 3, 2, 3]
    print(f"O(n log n): {solver.length_of_lis(b)}")    # 4
    print(f"O(n²)     : {solver.length_of_lis_dp(b)}") # 4

    c = [7, 7, 7, 7, 7]
    print(f"O(n log n): {solver.length_of_lis(c)}")    # 1
    print(f"O(n²)     : {solver.length_of_lis_dp(c)}") # 1


if __name__ == "__main__":
    main()
