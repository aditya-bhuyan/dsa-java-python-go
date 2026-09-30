# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Minimum Window Substring — pytest tests

import pytest
from MinimumWindow import MinimumWindow


@pytest.fixture
def solver():
    return MinimumWindow()


def test_example1(solver):
    assert solver.min_window("ADOBECODEBANC", "ABC") == "BANC"


def test_exact_match(solver):
    assert solver.min_window("a", "a") == "a"


def test_needs_two_as(solver):
    assert solver.min_window("a", "aa") == ""


def test_s_equals_t(solver):
    assert solver.min_window("aa", "aa") == "aa"


def test_single_char_in_t(solver):
    assert solver.min_window("ab", "b") == "b"


def test_t_not_in_s(solver):
    assert solver.min_window("abc", "d") == ""


def test_empty_t(solver):
    assert solver.min_window("abc", "") == ""


def test_t_longer_than_s(solver):
    assert solver.min_window("a", "ab") == ""


def test_duplicates_in_t(solver):
    # t="aa" requires two 'a'; s="aab" → window "aa"
    assert solver.min_window("aab", "aa") == "aa"
