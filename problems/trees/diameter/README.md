# Diameter of Binary Tree

> Difficulty: **Easy**
> Topic: **Binary Tree, DFS, Postorder, Running Maximum**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Key Insight
6. Approach — DFS Postorder with Running Maximum
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

Given the `root` of a binary tree, return the **diameter** of the tree.

The **diameter** is the length of the **longest path between any two nodes**. This path may or may not pass through the root.

The **length** of a path is the number of **edges** between the two nodes.

---

# Examples

## Example 1

Input

```text
        1
       / \
      2   3
     / \
    4   5
```

Output

```text
3
```

Explanation

```
The longest path is: 4 → 2 → 1 → 3   (or 5 → 2 → 1 → 3)
Length = 3 edges
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
1
```

---

# Constraints

```
The number of nodes in the tree is in the range [1, 10^4].
-100 <= Node.val <= 100
```

---

# Understanding the Problem

The diameter is the **longest path between any two nodes**.

A path through node `x` consists of:

- The longest path going **down** into `x`'s **left** subtree.
- The longest path going **down** into `x`'s **right** subtree.

The total path length through `x` = `leftHeight + rightHeight`.

```
        1
       / \
      2   3
     / \
    4   5

At node 2:
  leftHeight  = 1  (path: 2→4)
  rightHeight = 1  (path: 2→5)
  path through 2 = 1 + 1 = 2

At node 1:
  leftHeight  = 2  (path: 1→2→4 or 1→2→5)
  rightHeight = 1  (path: 1→3)
  path through 1 = 2 + 1 = 3  ← maximum
```

---

# Key Insight

The diameter at each node = left subtree height + right subtree height.

But this maximum may occur at **any** node in the tree, not necessarily the root. We need to check every node and track the global maximum.

Use a **postorder DFS**: compute subtree heights bottom-up, and update a running maximum at each node.

---

# Approach — DFS Postorder with Running Maximum

### Algorithm

```
max_diameter = 0   ← shared state, updated at each node

depth(node):
    if node is null:
        return 0

    left  = depth(node.left)
    right = depth(node.right)

    max_diameter = max(max_diameter, left + right)

    return 1 + max(left, right)   ← return height to parent

depth(root)
return max_diameter
```

`depth` returns the **height** of the subtree (used by the parent). `max_diameter` is updated as a side-effect.

---

# Dry Run

Input

```
        1
       / \
      2   3
     / \
    4   5
```

```
depth(4): left=0, right=0 → max_dia=max(0,0+0)=0, return 1
depth(5): left=0, right=0 → max_dia=max(0,0+0)=0, return 1
depth(2): left=1, right=1 → max_dia=max(0,1+1)=2, return 2
depth(3): left=0, right=0 → max_dia=max(2,0+0)=2, return 1
depth(1): left=2, right=1 → max_dia=max(2,2+1)=3, return 3

return max_diameter = 3
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(n) — each node visited exactly once |
| Space | O(h) — call stack, where h = tree height |

---

# Edge Cases

## Single Node

```
root = [1]
depth(null) = 0, depth(null) = 0
max_diameter = 0 + 0 = 0
return 0
```

---

## Two Nodes

```
    1
     \
      2

depth(2): left=0, right=0 → max_dia=0, return 1
depth(1): left=0, right=1 → max_dia=max(0, 0+1)=1, return 2

return 1
```

---

## Diameter Does Not Pass Through Root

```
          1
         /
        2
       / \
      3   4
     /
    5

Diameter = 5→3→2→4 = 3 edges
(does not pass through root 1)
```

The running-maximum approach handles this correctly because we update `max_diameter` at node 2, not at the root.

---

# Interview Discussion

### Why can't we just compute left + right at the root?

Because the diameter may pass through a node **other than the root**. In the edge case above, the root has only a left child, but the longest path is entirely in the left subtree.

---

### Why return height (not diameter) from the recursive function?

The parent needs to know how deep it can reach through this child. The **height** tells the parent the longest downward path. The **diameter** at each node is computed locally and stored in `max_diameter` — it doesn't need to be propagated up.

---

### Can you solve this without shared state?

Yes, using a 1-element list or a class-level field as a wrapper:

```python
self.max_diameter = [0]
# or
self.max_diameter = 0   (class variable)
```

Avoid a module-level global — it causes issues if the function is called multiple times.

---

# Common Mistakes

## Returning Diameter Instead of Height

```
Wrong:
return left + right   ← this loses the subtree height the parent needs
```

The function must return the **height** for the parent's use, while updating the diameter as a side effect.

---

## Off-by-One: Counting Nodes Instead of Edges

The problem asks for the number of **edges**. The base case returns `0` for null (0 edges from a null). A leaf returns `1 + max(0, 0) = 1` (1 edge from the leaf up to its parent). The diameter at a node with two children of height 1 each is `1 + 1 = 2` edges. ✓

---

## Not Initializing the Maximum to Zero

A tree with one node has diameter 0. Initialize `max_diameter = 0`.

---

# Key Takeaways

- Diameter at node `x` = left height + right height.
- Use postorder DFS: compute subtree heights, update global maximum, return height.
- The answer can be at any node — not just the root. A running max is essential.
- Time O(n), Space O(h).
- This exact pattern (postorder + running max) appears in: Longest Univalue Path, Maximum Path Sum, and Diameter of N-ary Tree.
