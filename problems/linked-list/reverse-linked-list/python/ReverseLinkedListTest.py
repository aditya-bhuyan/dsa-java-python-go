import pytest

from ReverseLinkedList import ReverseLinkedList, ListNode, build_list, list_to_array

solver = ReverseLinkedList()


def test_basic_example():
    head = build_list([1, 2, 3, 4, 5])
    assert list_to_array(solver.reverse(head)) == [5, 4, 3, 2, 1]


def test_two_nodes():
    head = build_list([1, 2])
    assert list_to_array(solver.reverse(head)) == [2, 1]


def test_single_node():
    head = build_list([1])
    assert list_to_array(solver.reverse(head)) == [1]


def test_empty_list():
    assert solver.reverse(None) is None


def test_already_reversed():
    head = build_list([5, 4, 3, 2, 1])
    assert list_to_array(solver.reverse(head)) == [1, 2, 3, 4, 5]


def test_negative_values():
    head = build_list([-3, -2, -1])
    assert list_to_array(solver.reverse(head)) == [-1, -2, -3]
