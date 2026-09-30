"""Maximum Depth of Binary Tree — O(n) recursive DFS."""

from __future__ import annotations
from typing import Optional


class TreeNode:
    def __init__(self, val: int = 0, left: Optional["TreeNode"] = None,
                 right: Optional["TreeNode"] = None) -> None:
        self.val = val
        self.left = left
        self.right = right


class MaximumDepth:
    """Returns the maximum depth via postorder DFS."""

    def max_depth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        return 1 + max(self.max_depth(root.left), self.max_depth(root.right))


def main() -> None:
    #       3
    #      / \
    #     9  20
    #        / \
    #       15   7
    root = TreeNode(3, TreeNode(9), TreeNode(20, TreeNode(15), TreeNode(7)))
    sol = MaximumDepth()
    print("=" * 60)
    print("Maximum Depth of Binary Tree")
    print("=" * 60)
    print(f"Depth: {sol.max_depth(root)}  (expected 3)")


if __name__ == "__main__":
    main()
