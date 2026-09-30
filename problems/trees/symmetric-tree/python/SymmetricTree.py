"""Symmetric Tree — mirror DFS. Time: O(n)  Space: O(h)"""

from __future__ import annotations
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val; self.left = left; self.right = right


class SymmetricTree:

    def is_symmetric(self, root: Optional[TreeNode]) -> bool:
        if root is None: return True
        return self._is_mirror(root.left, root.right)

    def _is_mirror(self, left: Optional[TreeNode], right: Optional[TreeNode]) -> bool:
        if left is None and right is None: return True
        if left is None or  right is None: return False
        if left.val != right.val:          return False
        return (self._is_mirror(left.left,  right.right) and
                self._is_mirror(left.right, right.left))


def main():
    sol = SymmetricTree()
    r = TreeNode(1,
        TreeNode(2, TreeNode(3), TreeNode(4)),
        TreeNode(2, TreeNode(4), TreeNode(3)))
    print(f"Symmetric: {sol.is_symmetric(r)}  (expected True)")

if __name__ == "__main__": main()
