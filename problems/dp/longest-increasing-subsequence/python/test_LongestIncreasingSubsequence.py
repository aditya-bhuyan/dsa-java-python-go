"""
=============================================================================
File    : test_LongestIncreasingSubsequence.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : Longest Increasing Subsequence (LeetCode #300)
=============================================================================
"""

import pytest
from LongestIncreasingSubsequence import LongestIncreasingSubsequence


@pytest.fixture
def solver():
    return LongestIncreasingSubsequence()


@pytest.mark.parametrize("nums, expected", [
    ([10, 9, 2, 5, 3, 7, 101, 18], 4),
    ([0, 1, 0, 3, 2, 3],           4),
    ([7, 7, 7, 7, 7, 7, 7],        1),
    ([5],                           1),
    ([1, 2, 3, 4, 5],              5),
    ([5, 4, 3, 2, 1],              1),
    ([3, 3, 3],                    1),
    ([3, 10, 2, 1, 20],            3),
    ([],                           0),
])
def test_length_of_lis(solver, nums, expected):
    assert solver.length_of_lis(nums) == expected


@pytest.mark.parametrize("nums, expected", [
    ([10, 9, 2, 5, 3, 7, 101, 18], 4),
    ([0, 1, 0, 3, 2, 3],           4),
    ([7, 7, 7, 7],                 1),
    ([],                           0),
])
def test_length_of_lis_dp(solver, nums, expected):
    assert solver.length_of_lis_dp(nums) == expected
