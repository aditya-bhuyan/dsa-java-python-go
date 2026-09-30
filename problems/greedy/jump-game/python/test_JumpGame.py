# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Jump Game — pytest tests

import pytest
from JumpGame import JumpGame


@pytest.fixture
def solver():
    return JumpGame()


def test_example1_reachable(solver):
    assert solver.can_jump([2, 3, 1, 1, 4]) is True


def test_example2_stuck(solver):
    assert solver.can_jump([3, 2, 1, 0, 4]) is False


def test_single_element_zero(solver):
    assert solver.can_jump([0]) is True


def test_single_element_nonzero(solver):
    assert solver.can_jump([5]) is True


def test_two_elements_can_jump(solver):
    assert solver.can_jump([1, 0]) is True


def test_two_elements_cannot_jump(solver):
    assert solver.can_jump([0, 1]) is False


def test_all_ones(solver):
    assert solver.can_jump([1, 1, 1, 1]) is True


def test_large_jump_from_start(solver):
    assert solver.can_jump([5, 0, 0, 0, 0]) is True


def test_last_zero_reachable(solver):
    assert solver.can_jump([2, 0, 0]) is True


def test_all_zeros_length_gt1(solver):
    assert solver.can_jump([0, 0, 0]) is False
