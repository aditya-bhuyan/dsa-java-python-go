# Binary Tree

> Category: **Data Structure**
> Difficulty: **Foundational → Intermediate**

---

# Table of Contents

1. What is a Binary Tree?
2. Types of Binary Trees
3. Node Definition
4. Properties and Formulas
5. DFS Traversals — Deep Dive
6. BFS — Level-Order Traversal
7. Recursive Problem-Solving Template
8. Common Binary Tree Problems
9. Complexity Summary
10. Common Mistakes
11. Key Takeaways

---

# What is a Binary Tree?

A **Binary Tree** is a tree where every node has **at most two children**: a **left** child and a **right** child.

```
         (1)
        /   \
      (2)   (3)
      / \     \
    (4) (5)   (6)
```

- Node `1` is the **root**.
- Nodes `4`, `5`, `6` are **leaves** (no children).
- Node `2` has two children; node `3` has one right child.

---

# Types of Binary Trees

## Full Binary Tree

Every node has **0 or 2** children. No node has exactly one child.

```
      (1)
     /   \
   (2)   (3)
   / \
 (4) (5)
```

---

## Complete Binary Tree

All levels are completely filled except possibly the last, and the last level is filled **from left to right**.

```
      (1)
     /   \
   (2)   (3)
   / \   /
 (4)(5)(6)
```

Used internally by binary heaps.

---

## Perfect Binary Tree

All internal nodes have exactly two children, and all leaves are at the **same level**.

```
        (1)
       /   \
     (2)   (3)
    / \   / \
  (4)(5)(6)(7)
```

A perfect binary tree with height `h` has `2^(h+1) - 1` nodes.

---

## Balanced Binary Tree

The height of the left and right subtree of **every node** differ by at most 1.

Height: O(log n). Guarantees efficient operations.

---

## Degenerate (Skewed) Tree

Every node has at most one child — behaves like a linked list.

```
(1)
  \
  (2)
    \
    (3)
      \
      (4)
```

Height: O(n). All tree benefits are lost.

---

# Node Definition

**Java**

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```

**Python**

```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
```

**Go**

```go
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}
```

---

# Properties and Formulas

For a binary tree of height `h`:

| Property | Value |
|----------|-------|
| Maximum nodes | 2^(h+1) - 1 |
| Minimum nodes | h + 1 (skewed) |
| Maximum leaves | 2^h |
| Height of complete tree with n nodes | ⌊log₂ n⌋ |

---

# DFS Traversals — Deep Dive

All three DFS orderings visit every node exactly once: O(n) time, O(h) space.

## Preorder — Root → Left → Right

Visit root first, then subtrees.

```
        (1)
       /   \
     (2)   (3)
     / \
   (4) (5)

Preorder: [1, 2, 4, 5, 3]
```

**Java**

```java
void preorder(TreeNode node, List<Integer> result) {
    if (node == null) return;
    result.add(node.val);
    preorder(node.left, result);
    preorder(node.right, result);
}
```

---

## Inorder — Left → Root → Right

Visit left subtree, root, right subtree.

```
Inorder of BST → sorted order

Inorder: [4, 2, 5, 1, 3]
```

**Python**

```python
def inorder(node, result):
    if node is None:
        return
    inorder(node.left, result)
    result.append(node.val)
    inorder(node.right, result)
```

---

## Postorder — Left → Right → Root

Visit both subtrees before the root. Used when the root's result depends on its children (height, diameter).

```
Postorder: [4, 5, 2, 3, 1]
```

**Go**

```go
func postorder(node *TreeNode, result *[]int) {
    if node == nil { return }
    postorder(node.Left, result)
    postorder(node.Right, result)
    *result = append(*result, node.Val)
}
```

---

## Iterative Inorder (using a stack)

```python
def inorder_iterative(root):
    result, stack = [], []
    current = root

    while current or stack:
        while current:
            stack.append(current)
            current = current.left
        current = stack.pop()
        result.append(current.val)
        current = current.right

    return result
```

---

# BFS — Level-Order Traversal

Process all nodes level by level using a queue.

```
        (1)          Level 0 → [1]
       /   \
     (2)   (3)       Level 1 → [2, 3]
    /   \     \
  (4)  (5)   (6)    Level 2 → [4, 5, 6]

BFS result: [[1], [2,3], [4,5,6]]
```

**Python**

```python
from collections import deque

def level_order(root):
    if not root:
        return []
    result, queue = [], deque([root])
    while queue:
        level = []
        for _ in range(len(queue)):
            node = queue.popleft()
            level.append(node.val)
            if node.left:  queue.append(node.left)
            if node.right: queue.append(node.right)
        result.append(level)
    return result
```

---

# Recursive Problem-Solving Template

Most binary tree interview problems follow one of two templates:

## Template 1 — Return a Value (Bottom-Up / Postorder)

Compute something about each subtree and return it to the parent.

```
def solve(node):
    if node is None:
        return base_value

    left_result  = solve(node.left)
    right_result = solve(node.right)

    return combine(left_result, right_result, node)
```

Examples: height, diameter, path sum, check balanced.

---

## Template 2 — Pass State Down (Top-Down / Preorder)

Pass information from parent to children via parameters.

```
def solve(node, state):
    if node is None:
        return

    update(state, node)
    solve(node.left, state)
    solve(node.right, state)
```

Examples: path from root to leaf, level tracking, max path with parent info.

---

## Template 3 — Pairwise Comparison

Compare two trees simultaneously, recursing on matching children.

```
def compare(p, q):
    if p is None and q is None: return True
    if p is None or q is None:  return False
    if p.val != q.val:          return False
    return compare(p.left, q.left) and compare(p.right, q.right)
```

Examples: same tree, symmetric tree.

---

# Common Binary Tree Problems

| Problem | Technique | Time | Space |
|---------|-----------|------|-------|
| Maximum Depth | Postorder recursion | O(n) | O(h) |
| Inorder Traversal | Inorder recursion or iteration | O(n) | O(h) |
| Same Tree | Pairwise DFS | O(n) | O(h) |
| Symmetric Tree | Mirror DFS | O(n) | O(h) |
| Diameter | Postorder, track max across calls | O(n) | O(h) |
| Level Order | BFS with queue | O(n) | O(w) |
| Path Sum | Top-down DFS | O(n) | O(h) |
| Lowest Common Ancestor | Postorder DFS | O(n) | O(h) |

Where `h` = tree height, `w` = maximum tree width.

---

# Complexity Summary

| Traversal | Time | Space (balanced) | Space (skewed) |
|-----------|------|-----------------|----------------|
| DFS (recursive) | O(n) | O(log n) | O(n) |
| DFS (iterative) | O(n) | O(log n) | O(n) |
| BFS | O(n) | O(n/2) = O(n) | O(1) |

---

# Common Mistakes

## Forgetting the Null Check

```
Wrong:
return 1 + max(height(node.left), height(node.right))

Correct:
if node is None: return 0
return 1 + max(height(node.left), height(node.right))
```

---

## Confusing Preorder / Inorder / Postorder

Memory aid:

```
Pre  = ROOT before children  (Root-Left-Right)
In   = ROOT between children (Left-Root-Right)
Post = ROOT after children   (Left-Right-Root)
```

---

## Using a Global Variable Carelessly

Problems like Diameter require tracking a maximum across the entire tree. Use a class-level variable, a list wrapper (`[0]`), or pass state explicitly.

```python
# Safe pattern
self.max_diameter = 0

def depth(node):
    if not node: return 0
    l, r = depth(node.left), depth(node.right)
    self.max_diameter = max(self.max_diameter, l + r)
    return 1 + max(l, r)
```

---

## Returning Depth Instead of Height

`depth` is measured from the root down; `height` is measured from the node up to the furthest leaf. Many problems want **height** — make sure your base case returns `0` for a null node.

---

# Key Takeaways

After studying this concept, you should understand:

- The five types of binary trees and their structural properties.
- Preorder, inorder, and postorder DFS traversals and when each is used.
- BFS level-order traversal using a queue.
- The three recursive templates: bottom-up (postorder), top-down (preorder), pairwise.
- How to handle the `null` base case in every tree function.
- Space complexity depends on tree height: O(log n) for balanced, O(n) for skewed.
