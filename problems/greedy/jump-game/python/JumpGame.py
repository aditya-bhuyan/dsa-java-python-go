"""
===============================================================================
Problem: Jump Game (LeetCode 55)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Given an integer array nums where each element is the maximum jump length
from that position, return True if you can reach the last index.

Algorithm
---------
Greedy — track maximum reachable index.
For each i, v in enumerate(nums):
  - If i > max_reach → unreachable → return False.
  - max_reach = max(max_reach, i + v).
Return True.

Dry Run
-------
nums = [2, 3, 1, 1, 4]

i=0, v=2: max_reach = max(0, 0+2) = 2
i=1, v=3: max_reach = max(2, 1+3) = 4
i=2, v=1: max_reach = max(4, 2+1) = 4
i=3, v=1: max_reach = max(4, 3+1) = 4
i=4, v=4: 4 <= 4 ✓, max_reach = 8
return True ✓

nums = [3, 2, 1, 0, 4]

i=0: max_reach=3; i=1: max_reach=3; i=2: max_reach=3
i=3: max_reach=3; i=4: 4 > 3 → return False ✓

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
"""


class JumpGame:
    """
    Solution for Jump Game using greedy max-reach tracking.

    Time:  O(n)
    Space: O(1)
    """

    def can_jump(self, nums: list[int]) -> bool:
        """
        Return True if the last index is reachable from index 0.

        Args:
            nums: list where nums[i] = maximum jump length from index i
        Returns:
            True if last index is reachable.
        """
        max_reach = 0
        for i, v in enumerate(nums):
            if i > max_reach:
                return False
            if i + v > max_reach:
                max_reach = i + v
        return True


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = JumpGame()

    test_cases = [
        ([2, 3, 1, 1, 4], True),
        ([3, 2, 1, 0, 4], False),
        ([0],             True),
        ([1, 0],          True),
        ([0, 1],          False),
        ([1, 1, 1, 1],    True),
        ([5, 0, 0, 0, 0], True),
    ]

    print("=" * 60)
    print("Jump Game")
    print("=" * 60)
    for i, (nums, expected) in enumerate(test_cases, 1):
        result = solver.can_jump(nums)
        print(f"Test {i}: nums={nums} → {result} "
              f"(expected {expected}) {'PASS' if result == expected else 'FAIL'}")


if __name__ == "__main__":
    main()
