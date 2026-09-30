"""
=============================================================================
File    : test_HouseRobber.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : House Robber (LeetCode #198)
=============================================================================
"""

import pytest
from HouseRobber import HouseRobber


@pytest.fixture
def solver():
    return HouseRobber()


@pytest.mark.parametrize("nums, expected", [
    ([1, 2, 3, 1],    4),
    ([2, 7, 9, 3, 1], 12),
    ([5],             5),
    ([3, 10],         10),
    ([4, 4, 4, 4],    8),
    ([9, 5, 3, 1],    12),
    ([1, 9, 1, 9, 1], 27),
    ([],              0),
])
def test_rob(solver, nums, expected):
    assert solver.rob(nums) == expected
