"""
===============================================================================
Problem: Maximum Average Subarray I (LeetCode 643)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Find a contiguous subarray of length k that has the maximum average value.
Return that maximum average.

Algorithm
---------
Fixed sliding window of size k.
1. Compute sum of first k elements.
2. Slide: sum += nums[right] - nums[right - k].
3. Track maximum sum.
4. Return maxSum / k.

Dry Run
-------
nums = [1, 12, -5, -6, 50, 3],  k = 4

Initial window [0..3]: sum = 1+12-5-6 = 2,  best = 2
right=4: sum = 2 + 50 - 1 = 51, best = 51
right=5: sum = 51 + 3 - 12 = 42, best = 51
return 51 / 4 = 12.75

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
"""


class MaximumAverage:
    """
    Solution for Maximum Average Subarray I using a fixed sliding window.

    Time:  O(n)
    Space: O(1)
    """

    def find_max_average(self, nums: list[int], k: int) -> float:
        """
        Return the maximum average value of any contiguous subarray of length k.

        Args:
            nums: integer array
            k:    window size (1 <= k <= len(nums))
        Returns:
            Maximum average as a float.
        """
        window_sum = sum(nums[:k])
        best = window_sum
        for right in range(k, len(nums)):
            window_sum += nums[right] - nums[right - k]
            if window_sum > best:
                best = window_sum
        return best / k


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = MaximumAverage()

    test_cases = [
        ([1, 12, -5, -6, 50, 3], 4, 12.75),
        ([5], 1, 5.0),
        ([-1, -12, -5, -6], 2, -6.0),
        ([0, 4, 0, 3, 2], 1, 4.0),
    ]

    print("=" * 60)
    print("Maximum Average Subarray I")
    print("=" * 60)
    for i, (nums, k, expected) in enumerate(test_cases, 1):
        result = solver.find_max_average(nums, k)
        ok = abs(result - expected) < 1e-5
        print(f"Test {i}: nums={nums} k={k} → {result:.5f} "
              f"(expected {expected:.5f}) {'PASS' if ok else 'FAIL'}")


if __name__ == "__main__":
    main()
