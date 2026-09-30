"""Diameter of Binary Tree — postorder DFS + running max. Time: O(n)  Space: O(h)"""

from __future__ import annotations
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val; self.left = left; self.right = right


class Diameter:

    def diameter_of_binary_tree(self, root: Optional[TreeNode]) -> int:
        self._max = 0

        def depth(node: Optional[TreeNode]) -> int:
            if node is None: return 0
            left  = depth(node.left)
            right = depth(node.right)
            self._max = max(self._max, left + right)
            return 1 + max(left, right)

        depth(root)
        return self._max


def main():
    #       1
    #      / \
    #     2   3
    #    / \
    #   4   5
    root = TreeNode(1, TreeNode(2, TreeNode(4), TreeNode(5)), TreeNode(3))
    sol = Diameter()
    print(f"Diameter: {sol.diameter_of_binary_tree(root)}  (expected 3)")

if __name__ == "__main__": main()
