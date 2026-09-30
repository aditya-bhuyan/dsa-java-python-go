# Tree

> Category: **Data Structure**
> Difficulty: **Foundational**

---

# Table of Contents

1. What is a Tree?
2. Tree Terminology
3. Types of Trees
4. Tree Representation
5. Depth-First Search (DFS)
6. Breadth-First Search (BFS)
7. Complexity Summary
8. DFS vs BFS — When to Use Which
9. Common Mistakes
10. Key Takeaways

---

# What is a Tree?

A **Tree** is a non-linear hierarchical data structure made up of **nodes** connected by **edges**.

```
            (A)          ← Root
           /   \
         (B)   (C)       ← Internal nodes
        /   \     \
      (D)  (E)    (F)    ← Leaf nodes
```

Properties:

- There is exactly **one root** node with no parent.
- Every non-root node has exactly **one parent**.
- There are **no cycles** — it is an acyclic connected graph.
- A tree with `n` nodes has exactly `n - 1` edges.

---

# Tree Terminology

| Term | Definition |
|------|-----------|
| **Root** | The topmost node; has no parent |
| **Node** | An element of the tree |
| **Edge** | A connection between parent and child |
| **Parent** | A node with children |
| **Child** | A node directly below a parent |
| **Leaf** | A node with no children |
| **Sibling** | Nodes sharing the same parent |
| **Ancestor** | Any node on the path from root to a node |
| **Descendant** | Any node in the subtree below a node |
| **Depth** | Number of edges from root to the node |
| **Height** | Number of edges on the longest path from node to a leaf |
| **Level** | Depth + 1 (root is at level 1) |
| **Subtree** | A node and all its descendants |
| **Degree** | Number of children a node has |

---

## Height vs Depth

```
            (A)     depth=0, height=2
           /   \
         (B)   (C)  depth=1, height=1 / 1
        /   \
      (D)  (E)      depth=2, height=0 (leaves)
```

**Height of tree** = height of root = longest path from root to any leaf.

---

# Types of Trees

## General Tree

Each node can have any number of children. No structural constraints.

---

## Binary Tree

Each node has **at most two children**: left and right.

```
      (1)
     /   \
   (2)   (3)
   /
 (4)
```

Covered in depth in `binary-tree.md`.

---

## N-ary Tree

Each node can have at most **N children** for a fixed N.

---

## Binary Search Tree (BST)

A binary tree where:

- All nodes in the **left subtree** are less than the node.
- All nodes in the **right subtree** are greater than the node.

Covered in `bst.md`.

---

## Balanced Tree

A tree where the height difference between the left and right subtree of every node is at most 1 (e.g., AVL Tree, Red-Black Tree).

Height: O(log n).

---

## Complete Binary Tree

All levels are fully filled except possibly the last, which is filled from left to right.

Used internally by binary heaps.

---

## Perfect Binary Tree

All internal nodes have exactly two children, and all leaves are at the same depth.

---

## Degenerate / Skewed Tree

Every node has at most one child. Behaves like a linked list.

Height: O(n). All O(log n) BST guarantees are lost.

---

# Tree Representation

## Node Class

**Java**

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
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

## Array Representation (for Complete Trees)

A complete binary tree can be stored in a flat array using index arithmetic:

```
Index:   0   1   2   3   4   5   6
Array: [ A | B | C | D | E | F | G ]

Parent of i    = (i - 1) / 2
Left child     = 2 * i + 1
Right child    = 2 * i + 2
```

Used by heaps.

---

# Depth-First Search (DFS)

**DFS** explores as far as possible along a branch before backtracking. On a binary tree it naturally maps to recursion.

There are three DFS orderings, defined by **when the root is processed** relative to its children:

---

## Preorder — Root → Left → Right

```
Process root first, then recurse.

        (1)
       /   \
     (2)   (3)
     / \
   (4) (5)

Preorder: 1 2 4 5 3
```

Use case: copy a tree, serialize a tree structure.

```
preorder(node):
    if node is null: return
    visit(node)           ← ROOT first
    preorder(node.left)
    preorder(node.right)
```

---

## Inorder — Left → Root → Right

```
Recurse left, process root, recurse right.

Inorder of a BST produces elements in sorted order.

Inorder: 4 2 5 1 3
```

Use case: sorted output from a BST, expression trees.

```
inorder(node):
    if node is null: return
    inorder(node.left)
    visit(node)           ← ROOT middle
    inorder(node.right)
```

---

## Postorder — Left → Right → Root

```
Recurse both children, then process root.

Postorder: 4 5 2 3 1
```

Use case: delete a tree, compute subtree properties (height, diameter).

```
postorder(node):
    if node is null: return
    postorder(node.left)
    postorder(node.right)
    visit(node)           ← ROOT last
```

---

## Iterative DFS (using an explicit stack)

When recursion depth is a concern, DFS can be implemented iteratively:

```
stack = [root]

while stack:
    node = stack.pop()
    visit(node)
    if node.right: stack.push(node.right)  ← push right first
    if node.left:  stack.push(node.left)   ← left processed first (LIFO)
```

This gives preorder. Inorder and postorder require slightly more bookkeeping.

---

# Breadth-First Search (BFS)

**BFS** explores all nodes at the current depth before going deeper. It uses a **queue** (FIFO) instead of a stack.

Also called **Level-Order Traversal**.

```
        (1)          Level 0
       /   \
     (2)   (3)       Level 1
    /   \     \
  (4)  (5)   (6)     Level 2

BFS order: 1  2 3  4 5 6
```

---

## Algorithm

```
queue = [root]

while queue is not empty:
    node = queue.dequeue()
    visit(node)
    if node.left:  queue.enqueue(node.left)
    if node.right: queue.enqueue(node.right)
```

---

## Level-by-Level BFS

To process one level at a time (useful for "level order" problems):

```
queue = [root]

while queue:
    level_size = len(queue)
    for _ in range(level_size):
        node = queue.popleft()
        visit(node)
        if node.left:  queue.append(node.left)
        if node.right: queue.append(node.right)
```

---

## BFS Visual

```
Start:  queue = [(1)]

Step 1: dequeue (1) → enqueue (2),(3)
        queue = [(2),(3)]

Step 2: dequeue (2) → enqueue (4),(5)
        queue = [(3),(4),(5)]

Step 3: dequeue (3) → enqueue (6)
        queue = [(4),(5),(6)]

Step 4-6: dequeue leaves, no children to enqueue.
```

---

# Complexity Summary

| Operation | DFS (recursive) | DFS (iterative) | BFS |
|-----------|-----------------|-----------------|-----|
| Time | O(n) | O(n) | O(n) |
| Space (balanced) | O(log n) stack | O(log n) stack | O(w) queue* |
| Space (skewed) | O(n) stack | O(n) stack | O(1) queue |

\* `w` = maximum width of the tree. For a perfect binary tree, the last level has `n/2` nodes → BFS queue can hold O(n/2) = O(n) nodes. DFS stack holds only one path root-to-leaf = O(log n) for balanced trees.

---

# DFS vs BFS — When to Use Which

| Use DFS when… | Use BFS when… |
|---------------|---------------|
| You need to explore **all paths** (backtracking) | You need the **shortest path** or minimum depth |
| You need to process **subtree information** (height, diameter) | You need **level-order** output |
| The problem has **recursive structure** (same tree, symmetric tree) | You need to find the **nearest** node meeting a condition |
| Tree is **deep** (BFS queue would be small) | Tree is **wide** (DFS stack would be small) |

---

# Common Mistakes

## Not Handling the Null / None Node

Every recursive or iterative tree function must check for `null` before accessing `.left`, `.right`, or `.val`.

```
Wrong:

def height(node):
    return 1 + max(height(node.left), height(node.right))
    # crashes when node is None

Correct:

def height(node):
    if node is None: return 0
    return 1 + max(height(node.left), height(node.right))
```

---

## Confusing Height and Depth

- **Height** is measured **upward** from the leaves.
- **Depth** is measured **downward** from the root.

Height of a leaf = 0. Depth of root = 0.

---

## Using BFS for Subtree-Dependent Problems

Problems like height, diameter, and path sums depend on **subtree results**. These are naturally solved with DFS postorder (children first, then root). BFS processes nodes level by level, making it harder to accumulate subtree information.

---

# Key Takeaways

After studying this concept, you should understand:

- Tree terminology: root, leaf, height, depth, subtree.
- The three DFS orderings: preorder, inorder, postorder — and what each is used for.
- BFS (level-order) traversal using a queue.
- When to prefer DFS (subtree computation, recursion) vs BFS (level order, shortest path).
- Space complexity depends on tree shape: O(log n) for balanced, O(n) for skewed.

---

# Problems Covered

| Problem | Traversal Used |
|---------|---------------|
| Maximum Depth | DFS postorder |
| Inorder Traversal | DFS inorder |
| Same Tree | DFS preorder (pairwise) |
| Symmetric Tree | DFS (mirror comparison) |
| Diameter | DFS postorder with running maximum |
