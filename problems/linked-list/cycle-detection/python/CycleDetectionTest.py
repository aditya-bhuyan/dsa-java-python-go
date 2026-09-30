import pytest

from CycleDetection import CycleDetection, build_list

solver = CycleDetection()


def test_no_cycle():
    head = build_list([1, 2, 3, 4])
    assert solver.has_cycle(head) is False


def test_cycle_tail_to_head():
    head = build_list([1, 2, 3], cycle_index=0)
    assert solver.has_cycle(head) is True


def test_cycle_tail_to_middle():
    head = build_list([3, 2, 0, -4], cycle_index=1)
    assert solver.has_cycle(head) is True


def test_self_loop():
    head = build_list([1], cycle_index=0)
    assert solver.has_cycle(head) is True


def test_single_node_no_cycle():
    head = build_list([1])
    assert solver.has_cycle(head) is False


def test_two_nodes_cycle():
    head = build_list([1, 2], cycle_index=0)
    assert solver.has_cycle(head) is True


def test_two_nodes_no_cycle():
    head = build_list([1, 2])
    assert solver.has_cycle(head) is False


def test_empty_list():
    assert solver.has_cycle(None) is False
