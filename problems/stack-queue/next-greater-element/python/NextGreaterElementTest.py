import pytest

from NextGreaterElement import NextGreaterElement

solver = NextGreaterElement()


def test_example1():
    assert solver.find_next_greater([4, 1, 2], [1, 3, 4, 2]) == [-1, 3, -1]

def test_example2():
    assert solver.find_next_greater([2, 4], [1, 2, 3, 4]) == [3, -1]

def test_all_increasing_nums2():
    assert solver.find_next_greater([1, 2], [1, 2, 3]) == [2, 3]

def test_all_decreasing_nums2():
    assert solver.find_next_greater([3, 1], [3, 2, 1]) == [-1, -1]

def test_last_element_in_nums2():
    assert solver.find_next_greater([1], [2, 1]) == [-1]

def test_single_element_query():
    assert solver.find_next_greater([5], [5, 4, 3, 2, 1]) == [-1]
