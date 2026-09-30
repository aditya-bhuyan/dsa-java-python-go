import pytest
from Diameter import Diameter, TreeNode

def n(v): return TreeNode(v)

def test_single_node():
    assert Diameter().diameter_of_binary_tree(n(1)) == 0

def test_two_nodes():
    r = n(1); r.right = n(2)
    assert Diameter().diameter_of_binary_tree(r) == 1

def test_example1():
    root = TreeNode(1, TreeNode(2, n(4), n(5)), n(3))
    assert Diameter().diameter_of_binary_tree(root) == 3

def test_null_root():
    assert Diameter().diameter_of_binary_tree(None) == 0

def test_diameter_not_through_root():
    r = n(1)
    r.left = n(2); r.left.left = n(3); r.left.right = n(4)
    r.left.left.left = n(5)
    assert Diameter().diameter_of_binary_tree(r) == 3
