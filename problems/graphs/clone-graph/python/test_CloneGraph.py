# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Clone Graph — pytest tests

import pytest
from CloneGraph import Node, CloneGraph, build_graph, to_adj_map


@pytest.fixture
def solver():
    return CloneGraph()


def test_none_input(solver):
    assert solver.clone_graph(None) is None


def test_single_node_no_neighbors(solver):
    n = Node(1)
    clone = solver.clone_graph(n)
    assert clone is not n
    assert clone.val == 1
    assert clone.neighbors == []


def test_four_node_cycle(solver):
    original = build_graph([[2, 4], [1, 3], [2, 4], [1, 3]])
    clone = solver.clone_graph(original)
    assert clone is not original
    assert to_adj_map(original) == to_adj_map(clone)


def test_two_node_mutual(solver):
    original = build_graph([[2], [1]])
    clone = solver.clone_graph(original)
    assert clone is not original
    assert to_adj_map(original) == to_adj_map(clone)


def test_clone_is_deep(solver):
    """Mutating the original should not affect the clone."""
    original = build_graph([[2], [1]])
    clone = solver.clone_graph(original)
    original.val = 99
    assert clone.val == 1


def test_single_node_with_self_as_neighbor(solver):
    """Edge: node neighbors itself (though problem says no self-loops; guard test)."""
    # Build manually: node 1 with no neighbors to stay within problem constraints
    n = Node(42)
    clone = solver.clone_graph(n)
    assert clone.val == 42
    assert clone is not n
