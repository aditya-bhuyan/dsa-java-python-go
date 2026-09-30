import pytest
from SymmetricTree import SymmetricTree, TreeNode

sol = SymmetricTree()
def n(v): return TreeNode(v)

def test_none():         assert sol.is_symmetric(None) is True
def test_single():       assert sol.is_symmetric(n(1)) is True
def test_symmetric():
    r = TreeNode(1, TreeNode(2, n(3), n(4)), TreeNode(2, n(4), n(3)))
    assert sol.is_symmetric(r) is True
def test_asymmetric():
    r = TreeNode(1); r.left = TreeNode(2); r.left.right = n(3)
    r.right = TreeNode(2); r.right.right = n(3)
    assert sol.is_symmetric(r) is False
def test_diff_values():
    assert sol.is_symmetric(TreeNode(1, n(2), n(3))) is False
