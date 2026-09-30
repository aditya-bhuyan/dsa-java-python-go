import pytest
from MaximumDepth import MaximumDepth, TreeNode

sol = MaximumDepth()

def test_null_root():     assert sol.max_depth(None) == 0
def test_single_node():   assert sol.max_depth(TreeNode(1)) == 1
def test_two_left():
    r = TreeNode(1, TreeNode(2)); assert sol.max_depth(r) == 2
def test_example1():
    r = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
    assert sol.max_depth(r) == 3
def test_right_skewed():
    r = TreeNode(1, None, TreeNode(2, None, TreeNode(3, None, TreeNode(4))))
    assert sol.max_depth(r) == 4
