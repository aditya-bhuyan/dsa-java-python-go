"""Binary Tree Inorder Traversal — recursive and iterative."""

from __future__ import annotations
from typing import Optional


class TreeNode:
    def __init__(self, val: int = 0, left: Optional["TreeNode"] = None,
                 right: Optional["TreeNode"] = None) -> None:
        self.val = val; self.left = left; self.right = right


class InorderTraversal:

    def inorder_recursive(self, root: Optional[TreeNode]) -> list[int]:
        result: list[int] = []
        def dfs(node: Optional[TreeNode]) -> None:
            if node is None: return
            dfs(node.left)
            result.append(node.val)
            dfs(node.right)
        dfs(root)
        return result

    def inorder_iterative(self, root: Optional[TreeNode]) -> list[int]:
        result: list[int] = []
        stack: list[TreeNode] = []
        current = root
        while current or stack:
            while current:
                stack.append(current)
                current = current.left
            current = stack.pop()
            result.append(current.val)
            current = current.right
        return result


def main() -> None:
    root = TreeNode(4, TreeNode(2, TreeNode(1), TreeNode(3)), TreeNode(5))
    sol = InorderTraversal()
    print("=" * 60)
    print("Binary Tree Inorder Traversal")
    print("=" * 60)
    print(f"Recursive : {sol.inorder_recursive(root)}  (expected [1,2,3,4,5])")
    print(f"Iterative : {sol.inorder_iterative(root)}  (expected [1,2,3,4,5])")


if __name__ == "__main__":
    main()
