"""Same Tree — pairwise DFS. Time: O(n)  Space: O(h)"""

from __future__ import annotations
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val; self.left = left; self.right = right


class SameTree:
    def is_same_tree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if p is None and q is None: return True
        if p is None or  q is None: return False
        if p.val != q.val:          return False
        return self.is_same_tree(p.left, q.left) and self.is_same_tree(p.right, q.right)


def main():
    sol = SameTree()
    p = TreeNode(1, TreeNode(2), TreeNode(3))
    q = TreeNode(1, TreeNode(2), TreeNode(3))
    print(f"Same: {sol.is_same_tree(p, q)}  (expected True)")

if __name__ == "__main__": main()
