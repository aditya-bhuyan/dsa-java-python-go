import pytest

from MergeLists import MergeLists, build_list, list_to_array

solver = MergeLists()


def test_basic_example():
    l1 = build_list([1, 2, 4])
    l2 = build_list([1, 3, 4])
    assert list_to_array(solver.merge(l1, l2)) == [1, 1, 2, 3, 4, 4]


def test_both_empty():
    assert solver.merge(None, None) is None


def test_first_empty():
    l2 = build_list([0])
    assert list_to_array(solver.merge(None, l2)) == [0]


def test_second_empty():
    l1 = build_list([1, 3])
    assert list_to_array(solver.merge(l1, None)) == [1, 3]


def test_different_lengths():
    l1 = build_list([1, 3, 5, 7])
    l2 = build_list([2, 4])
    assert list_to_array(solver.merge(l1, l2)) == [1, 2, 3, 4, 5, 7]


def test_all_same_values():
    l1 = build_list([1, 1, 1])
    l2 = build_list([1, 1])
    assert list_to_array(solver.merge(l1, l2)) == [1, 1, 1, 1, 1]


def test_interleaved():
    l1 = build_list([1, 5, 9])
    l2 = build_list([2, 3, 10])
    assert list_to_array(solver.merge(l1, l2)) == [1, 2, 3, 5, 9, 10]
