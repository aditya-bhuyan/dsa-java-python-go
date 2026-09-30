"""
=============================================================================
File    : HouseRobber.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : House Robber (LeetCode #198)

Approach : Space-Optimised Dynamic Programming
----------
  dp[i] = max(dp[i-1],  dp[i-2] + nums[i])
  Keep only two rolling variables: prev2 and prev1.

Dry Run  : nums = [2, 7, 9, 3, 1]
  prev2=0, prev1=2
  i=1 (7) → curr=max(2, 0+7)=7   prev2=2,  prev1=7
  i=2 (9) → curr=max(7, 2+9)=11  prev2=7,  prev1=11
  i=3 (3) → curr=max(11,7+3)=11  prev2=11, prev1=11
  i=4 (1) → curr=max(11,11+1)=12 prev2=11, prev1=12
  return 12  ✓

Complexity:
  Time  : O(n)
  Space : O(1)
=============================================================================
"""

from typing import List


class HouseRobber:
    """Maximises the amount robbed from non-adjacent houses."""

    def rob(self, nums: List[int]) -> int:
        """
        Returns the maximum money that can be robbed without alerting
        the police (no two adjacent houses).

        Args:
            nums: list of non-negative integers representing house values

        Returns:
            maximum money that can be robbed
        """
        if not nums:
            return 0
        if len(nums) == 1:
            return nums[0]
        prev2, prev1 = 0, nums[0]
        for i in range(1, len(nums)):
            curr = max(prev1, prev2 + nums[i])
            prev2, prev1 = prev1, curr
        return prev1


# ─────────────────────────────────────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────────────────────────────────────
def main() -> None:
    solver = HouseRobber()
    print("=== House Robber ===")
    print(solver.rob([1, 2, 3, 1]))       # 4
    print(solver.rob([2, 7, 9, 3, 1]))    # 12
    print(solver.rob([5]))                # 5
    print(solver.rob([]))                 # 0


if __name__ == "__main__":
    main()
