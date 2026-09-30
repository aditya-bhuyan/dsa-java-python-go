# Trees Problems

> **Week 6** · Core concept: recursive DFS (pre/in/post-order), iterative BFS, return-value patterns.

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Maximum Depth of Binary Tree | Easy | [maximum-depth/](maximum-depth/) |
| 2 | Binary Tree Inorder Traversal | Easy | [inorder-traversal/](inorder-traversal/) |
| 3 | Same Tree | Easy | [same-tree/](same-tree/) |
| 4 | Symmetric Tree | Easy | [symmetric-tree/](symmetric-tree/) |
| 5 | Diameter of Binary Tree | Easy | [diameter/](diameter/) |

## Key Patterns
- **Post-order return** — compute subtree result, return up to parent (Depth, Diameter)
- **Mirror comparison** — compare left subtree with mirrored right (Symmetric Tree)
- **Simultaneous traversal** — walk two trees in lockstep (Same Tree)
- **Iterative inorder** — stack-based, push left spine then process

## TreeNode definition (all 3 languages)
```java
class TreeNode { int val; TreeNode left, right; TreeNode(int v) { val = v; } }
```
```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None): self.val=val; self.left=left; self.right=right
```
```go
type TreeNode struct { Val int; Left, Right *TreeNode }
```

## Concepts
→ [concepts/tree.md](../../concepts/tree.md)  
→ [concepts/binary-tree.md](../../concepts/binary-tree.md)

← [Back to problems](../README.md)
