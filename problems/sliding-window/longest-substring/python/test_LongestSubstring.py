# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Longest Substring Without Repeating Characters — pytest tests

import pytest
from LongestSubstring import LongestSubstring


@pytest.fixture
def solver():
    return LongestSubstring()


def test_abcabcbb(solver):
    assert solver.length_of_longest_substring("abcabcbb") == 3


def test_all_same(solver):
    assert solver.length_of_longest_substring("bbbbb") == 1


def test_pwwkew(solver):
    assert solver.length_of_longest_substring("pwwkew") == 3


def test_empty_string(solver):
    assert solver.length_of_longest_substring("") == 0


def test_single_char(solver):
    assert solver.length_of_longest_substring("a") == 1


def test_all_unique(solver):
    assert solver.length_of_longest_substring("abcdefg") == 7


def test_single_space(solver):
    assert solver.length_of_longest_substring(" ") == 1


def test_two_spaces(solver):
    assert solver.length_of_longest_substring("  ") == 1


def test_special_chars(solver):
    assert solver.length_of_longest_substring("!@#!@#") == 3
