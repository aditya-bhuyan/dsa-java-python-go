# Maximum Depth of Binary Tree

> Difficulty: **Easy**
> Topic: **Binary Tree, DFS, Recursion**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Approach 1 — DFS Recursive (Postorder)
6. Approach 2 — BFS (Level-Order)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

Given the `root` of a binary tree, return its **maximum depth**.

The maximum depth is the number of nodes along the **longest path** from the root node down to the farthest leaf node.

---

# Examples

## Example 1

Input

```text
        3
       / \
      9  20
         / \
        15   7
```

Output

```text
3
```

Explanation

```
Longest path: 3 → 20 → 15  (or 3 → 20 → 7)
3 nodes → depth = 3
```

---

## Example 2

Input

```text
    1
     \
      2
```

Output

```text
2
```

---

# Constraints

```
The number of nodes in the tree is in the range [0, 10^4].
-100 <= Node.val <= 100
```

---

# Understanding the Problem

The **depth** of a tree is the number of nodes on the longest root-to-leaf path.

```
depth = 1 + max(depth of left subtree, depth of right subtree)
```

This naturally defines a recursive postorder solution: compute both subtree depths, then combine.

---

# Approach 1 — DFS Recursive (Postorder)

The depth at any node is `1` (for the node itself) plus the maximum depth among its children.

### Algorithm

```
maxDepth(node):
    if node is null:
        return 0
    left  = maxDepth(node.left)
    right = maxDepth(node.right)
    return 1 + max(left, right)
```

Base case: a null node has depth 0.

---

# Approach 2 — BFS (Level-Order)

Process the tree level by level. The number of levels equals the maximum depth.

### Algorithm

```
if root is null: return 0

queue = [root]
depth = 0

while queue is not empty:
    depth++
    for each node at this level:
        dequeue node
        enqueue its children

return depth
```

---

# Dry Run

Input

```
        3
       / \
      9  20
         / \
        15   7
```

---

## DFS Recursive

```
maxDepth(3)
  maxDepth(9)
    maxDepth(null) = 0
    maxDepth(null) = 0
    return 1 + max(0, 0) = 1
  maxDepth(20)
    maxDepth(15)
      maxDepth(null) = 0
      maxDepth(null) = 0
      return 1 + max(0,0) = 1
    maxDepth(7)
      maxDepth(null) = 0
      maxDepth(null) = 0
      return 1 + max(0,0) = 1
    return 1 + max(1,1) = 2
  return 1 + max(1, 2) = 3
```

Result: `3` ✓

---

## BFS

```
Level 1: dequeue [3]          → enqueue [9, 20]    depth=1
Level 2: dequeue [9, 20]      → enqueue [15, 7]    depth=2
Level 3: dequeue [15, 7]      → enqueue []         depth=3

Queue empty → return 3
```

---

# Complexity Analysis

## DFS Recursive

| Metric | Complexity |
|--------|------------|
| Time | O(n) — every node visited once |
| Space | O(h) — call stack, where h = height |

---

## BFS

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(w) — max width of the tree |

For a balanced tree: DFS space O(log n), BFS space O(n/2).
For a skewed tree: DFS space O(n), BFS space O(1).

---

# Edge Cases

## Empty Tree

```
root = null → return 0
```

---

## Single Node

```
root = [1] → return 1
```

No children, both recursive calls return 0. `1 + max(0,0) = 1`.

---

## Skewed Tree (all right children)

```
1 → 2 → 3 → 4 → 5

depth = 5
```

---

# Interview Discussion

### Why postorder here?

The depth of a node depends on the depths of its children — we must process children **before** the parent. This is postorder (Left → Right → Root).

---

### DFS vs BFS for this problem?

Both work. DFS is more concise (3 lines of code). BFS is intuitive as "count the levels." DFS is preferred in interviews for readability.

---

### What if we counted edges instead of nodes?

Change the base case: return `-1` for null (so a single node returns `0` edges).

---

# Common Mistakes

## Returning Height Instead of Depth for Leaf

The base case must return `0` for `null`, not `1`. A leaf node returns `1 + max(0, 0) = 1`, which is correct (one node = depth 1).

---

## Not Handling Empty Tree

Always check `if root is None: return 0` before starting traversal.

---

# Key Takeaways

- Maximum depth is the classic postorder recursion: `1 + max(left, right)`.
- Base case: `null` node returns `0`.
- Both DFS (O(h) space) and BFS (O(w) space) give O(n) time.
- This exact pattern appears as a building block in Diameter, Balanced Tree Check, and Lowest Common Ancestor.

---

# Next Problem

➡ **Inorder Traversal**

Applies the Left → Root → Right DFS ordering, and shows how to convert recursion into an iterative solution.
