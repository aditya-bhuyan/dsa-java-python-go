# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Maximum Average Subarray I — pytest tests

import pytest
from MaximumAverage import MaximumAverage

EPS = 1e-5


@pytest.fixture
def solver():
    return MaximumAverage()


def test_example1(solver):
    assert abs(solver.find_max_average([1, 12, -5, -6, 50, 3], 4) - 12.75) < EPS


def test_single_element(solver):
    assert abs(solver.find_max_average([5], 1) - 5.0) < EPS


def test_all_negatives(solver):
    assert abs(solver.find_max_average([-3, -2, -5, -1], 2) - (-1.5)) < EPS


def test_k_equals_n(solver):
    assert abs(solver.find_max_average([4, 0, 4], 3) - 8 / 3) < EPS


def test_k1_max_value(solver):
    assert abs(solver.find_max_average([0, 4, 0, 3, 2], 1) - 4.0) < EPS


def test_all_same(solver):
    assert abs(solver.find_max_average([2, 2, 2, 2], 2) - 2.0) < EPS


def test_large_window(solver):
    nums = list(range(1, 101))  # 1..100
    # max window of k=5 ending at 100: [96,97,98,99,100] sum=490 avg=98.0
    assert abs(solver.find_max_average(nums, 5) - 98.0) < EPS
