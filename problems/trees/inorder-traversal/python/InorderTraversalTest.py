import pytest
from InorderTraversal import InorderTraversal, TreeNode

sol = InorderTraversal()

def example():
    return TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(5))

def test_null_recursive():    assert sol.inorder_recursive(None) == []
def test_null_iterative():    assert sol.inorder_iterative(None) == []
def test_single_recursive():  assert sol.inorder_recursive(TreeNode(1)) == [1]
def test_single_iterative():  assert sol.inorder_iterative(TreeNode(1)) == [1]
def test_example_recursive(): assert sol.inorder_recursive(example()) == [1,2,3,4,5]
def test_example_iterative(): assert sol.inorder_iterative(example()) == [1,2,3,4,5]
def test_right_skewed():
    r = TreeNode(1, None, TreeNode(2, None, TreeNode(3)))
    assert sol.inorder_recursive(r) == [1, 2, 3]
    assert sol.inorder_iterative(r) == [1, 2, 3]
