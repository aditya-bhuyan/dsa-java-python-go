import pytest

from MiddleNode import MiddleNode, build_list

solver = MiddleNode()


def test_odd_length():
    head = build_list([1, 2, 3, 4, 5])
    assert solver.find_middle(head).val == 3


def test_even_length_returns_second_middle():
    head = build_list([1, 2, 3, 4, 5, 6])
    assert solver.find_middle(head).val == 4


def test_single_node():
    head = build_list([1])
    assert solver.find_middle(head).val == 1


def test_two_nodes_returns_second():
    head = build_list([1, 2])
    assert solver.find_middle(head).val == 2


def test_four_nodes():
    head = build_list([1, 2, 3, 4])
    assert solver.find_middle(head).val == 3


def test_three_nodes():
    head = build_list([10, 20, 30])
    assert solver.find_middle(head).val == 20
