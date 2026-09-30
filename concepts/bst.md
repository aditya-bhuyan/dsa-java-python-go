# Binary Search Tree (BST)

## Table of Contents

1. [Introduction](#introduction)
2. [BST Property](#bst-property)
3. [Core Operations](#core-operations)
4. [Traversals in a BST](#traversals-in-a-bst)
5. [Balanced vs Unbalanced BSTs](#balanced-vs-unbalanced-bsts)
6. [Self-Balancing BSTs](#self-balancing-bsts)
7. [Complexity Analysis](#complexity-analysis)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

A **Binary Search Tree (BST)** is a binary tree where every node satisfies the ordering property: all values in the **left subtree** are strictly less than the node's value, and all values in the **right subtree** are strictly greater.

This ordering enables **O(log n) search, insert, and delete** on a balanced tree — combining the flexibility of a linked list with the fast lookup of binary search.

---

## BST Property

For every node `N`:

```
All nodes in left subtree  <  N.val  <  All nodes in right subtree
```

### Example BST

```
        8
       / \
      3   10
     / \    \
    1   6   14
       / \   /
      4   7 13
```

- In-order traversal (left → root → right) yields: **1 3 4 6 7 8 10 13 14** — always sorted.
- This is the defining diagnostic: **BST in-order = sorted sequence**.

---

## Core Operations

### Search

```
search(root, target):
    if root is None or root.val == target: return root
    if target < root.val: return search(root.left, target)
    else:                 return search(root.right, target)
```

**Trace:** Search 6 in the tree above  
→ 6 < 8 → go left to 3  
→ 6 > 3 → go right to 6  
→ Found ✓

---

### Insert

Always insert at a leaf position:

```
insert(root, val):
    if root is None: return Node(val)
    if val < root.val: root.left  = insert(root.left, val)
    else:              root.right = insert(root.right, val)
    return root
```

**Trace:** Insert 5 into the example tree  
→ 5 < 8 → left → 5 > 3 → right → 5 < 6 → left → 5 > 4 → right → None  
→ Create Node(5) as right child of 4 ✓

---

### Delete

Three cases:

```
Case 1: Node has no children → simply remove it.

Case 2: Node has one child → replace node with its child.

Case 3: Node has two children →
    Find in-order successor (smallest node in right subtree).
    Replace node's value with successor's value.
    Delete the successor from the right subtree.
```

```
delete(root, val):
    if root is None: return None
    if val < root.val:
        root.left = delete(root.left, val)
    elif val > root.val:
        root.right = delete(root.right, val)
    else:
        if root.left is None:  return root.right  # Case 1 or 2
        if root.right is None: return root.left   # Case 2
        # Case 3: find in-order successor
        successor = minNode(root.right)
        root.val = successor.val
        root.right = delete(root.right, successor.val)
    return root
```

---

### Find Minimum / Maximum

```
minNode(root): walk left until left is None
maxNode(root): walk right until right is None
```

---

### In-Order Successor / Predecessor

**Successor** (next larger value):
- If right subtree exists → min of right subtree
- Else → walk up ancestors until we go up a left child

**Predecessor** (next smaller value):
- If left subtree exists → max of left subtree
- Else → walk up ancestors until we go up a right child

---

## Traversals in a BST

| Traversal | Order | Use in BST |
|---|---|---|
| In-order (L→N→R) | Ascending sorted order | Validate BST, k-th smallest |
| Reverse in-order (R→N→L) | Descending order | k-th largest |
| Pre-order (N→L→R) | Root first | Serialize / reconstruct BST |
| Post-order (L→R→N) | Root last | Delete tree, evaluate subtrees |

### Validate BST

Use in-order traversal and check that each value is strictly greater than the previous:

```
prev = -∞
def is_valid(node):
    if not node: return True
    if not is_valid(node.left): return False
    if node.val <= prev: return False
    prev = node.val
    return is_valid(node.right)
```

Or recursively pass `[min, max]` bounds:

```
def is_valid(node, lo=-∞, hi=+∞):
    if not node: return True
    if not (lo < node.val < hi): return False
    return is_valid(node.left, lo, node.val) and \
           is_valid(node.right, node.val, hi)
```

---

## Balanced vs Unbalanced BSTs

### Balanced BST

```
        4
       / \
      2   6
     / \ / \
    1  3 5  7
```
Height = O(log n). All operations O(log n).

### Degenerate BST (worst case — sorted input)

```
1
 \
  2
   \
    3
     \
      4
```
Height = O(n). All operations degrade to O(n) — equivalent to a linked list.

**Key insight:** Never assume a BST is balanced unless it is explicitly stated or you use a self-balancing variant.

---

## Self-Balancing BSTs

| Structure | Balancing Strategy | Height Guarantee |
|---|---|---|
| AVL Tree | Strict balance factor ≤ 1; rotations on insert/delete | O(log n) |
| Red-Black Tree | Color-based invariants; at most 2× height difference | O(log n) |
| B-Tree | Multi-key nodes; used in databases/filesystems | O(log n) |
| Splay Tree | Recently accessed nodes moved to root | Amortized O(log n) |

**Java:** `TreeMap` / `TreeSet` use Red-Black Trees.  
**C++:** `std::map` / `std::set` use Red-Black Trees.  
**Python:** No built-in BST; use `sortedcontainers.SortedList`.

### Rotations (AVL / Red-Black)

```
Right Rotation at y:           Left Rotation at x:
    y                               x
   / \                             / \
  x   C    →    x        →       A   y
 / \           / \                   / \
A   B         A   y                 B   C
                 / \
                B   C
```

---

## Complexity Analysis

| Operation | Balanced BST | Unbalanced (worst) |
|---|---|---|
| Search | O(log n) | O(n) |
| Insert | O(log n) | O(n) |
| Delete | O(log n) | O(n) |
| Min / Max | O(log n) | O(n) |
| In-order traversal | O(n) | O(n) |
| Space | O(n) | O(n) |

---

## Language Implementations

### Go

```go
type TreeNode struct {
    Val   int
    Left  *TreeNode
    Right *TreeNode
}

func search(root *TreeNode, target int) *TreeNode {
    if root == nil || root.Val == target { return root }
    if target < root.Val { return search(root.Left, target) }
    return search(root.Right, target)
}

func insert(root *TreeNode, val int) *TreeNode {
    if root == nil { return &TreeNode{Val: val} }
    if val < root.Val { root.Left = insert(root.Left, val) } else { root.Right = insert(root.Right, val) }
    return root
}

// In-order traversal → sorted slice
func inOrder(root *TreeNode, result *[]int) {
    if root == nil { return }
    inOrder(root.Left, result)
    *result = append(*result, root.Val)
    inOrder(root.Right, result)
}
```

### Java

```java
// Java TreeMap wraps a Red-Black BST
TreeMap<Integer, String> bst = new TreeMap<>();
bst.put(5, "five");
bst.put(3, "three");
bst.firstKey();    // min
bst.lastKey();     // max
bst.floorKey(4);   // largest key ≤ 4
bst.ceilingKey(4); // smallest key ≥ 4
```

### Python

```python
class TreeNode:
    def __init__(self, val=0):
        self.val = val
        self.left = self.right = None

def insert(root, val):
    if not root: return TreeNode(val)
    if val < root.val: root.left = insert(root.left, val)
    else:              root.right = insert(root.right, val)
    return root

def inorder(root):
    return inorder(root.left) + [root.val] + inorder(root.right) if root else []
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Assuming BST input is always balanced | Never assume; treat as O(n) worst case unless problem states balanced |
| Using `<=` in validation bounds (allows duplicates when problem says unique) | Clarify with interviewer; use strict `<` and `>` for standard BST |
| Forgetting to return `root` in recursive insert/delete | BST mutations return the updated subtree root |
| Finding in-order successor incorrectly when right subtree is absent | Track the last ancestor from which we came up a left branch |
| Confusing BST delete's three cases | Always check: 0 children, 1 child, 2 children |

---

## Problems Covered

| Problem | BST Operation | LeetCode |
|---|---|---|
| Validate Binary Search Tree | In-order + bounds check | #98 |
| Kth Smallest Element in BST | In-order traversal | #230 |
| Lowest Common Ancestor of BST | BST property navigation | #235 |
| Insert into BST | Recursive insert | #701 |
| Delete Node in BST | Three-case delete | #450 |
| Search in BST | Recursive search | #700 |
