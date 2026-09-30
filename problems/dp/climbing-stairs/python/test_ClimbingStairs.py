"""
=============================================================================
File    : test_ClimbingStairs.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : Climbing Stairs (LeetCode #70)
=============================================================================
"""

import pytest
from ClimbingStairs import ClimbingStairs


@pytest.fixture
def solver():
    return ClimbingStairs()


@pytest.mark.parametrize("n, expected", [
    (1,  1),
    (2,  2),
    (3,  3),
    (4,  5),
    (5,  8),
    (10, 89),
    (20, 10946),
    (45, 1836311903),
])
def test_climb_stairs(solver, n, expected):
    assert solver.climb_stairs(n) == expected
