# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Assign Cookies — pytest tests

import pytest
from AssignCookies import AssignCookies


@pytest.fixture
def solver():
    return AssignCookies()


def test_example1(solver):
    assert solver.find_content_children([1, 2, 3], [1, 1]) == 1


def test_example2_all_satisfied(solver):
    assert solver.find_content_children([1, 2], [1, 2, 3]) == 2


def test_cookies_too_small(solver):
    assert solver.find_content_children([10, 9, 8, 7], [5, 6, 7, 8]) == 2


def test_no_cookies(solver):
    assert solver.find_content_children([1, 2, 3], []) == 0


def test_no_children(solver):
    assert solver.find_content_children([], [1, 2, 3]) == 0


def test_single_match(solver):
    assert solver.find_content_children([1], [1]) == 1


def test_single_no_match(solver):
    assert solver.find_content_children([2], [1]) == 0


def test_all_same_size(solver):
    assert solver.find_content_children([1, 1, 1], [1, 1, 1]) == 3


def test_more_children_than_cookies(solver):
    assert solver.find_content_children([1, 2, 3, 4], [2, 3]) == 2
