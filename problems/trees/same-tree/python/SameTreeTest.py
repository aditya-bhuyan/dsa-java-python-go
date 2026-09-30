import pytest
from SameTree import SameTree, TreeNode

sol = SameTree()
def n(v): return TreeNode(v)

def test_both_none():         assert sol.is_same_tree(None, None) is True
def test_p_none():            assert sol.is_same_tree(None, n(1)) is False
def test_q_none():            assert sol.is_same_tree(n(1), None) is False
def test_single_equal():      assert sol.is_same_tree(n(1), n(1)) is True
def test_single_unequal():    assert sol.is_same_tree(n(1), n(2)) is False
def test_same():
    p = TreeNode(1, n(2), n(3)); q = TreeNode(1, n(2), n(3))
    assert sol.is_same_tree(p, q) is True
def test_diff_structure():
    p = TreeNode(1, n(2), None); q = TreeNode(1, None, n(2))
    assert sol.is_same_tree(p, q) is False
def test_diff_values():
    p = TreeNode(1, n(2), n(3)); q = TreeNode(1, n(2), n(4))
    assert sol.is_same_tree(p, q) is False
