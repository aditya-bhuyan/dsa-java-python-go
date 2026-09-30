# Binary Tree Inorder Traversal

> Difficulty: **Easy**
> Topic: **Binary Tree, DFS, Inorder, Iteration**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Approach 1 — Recursive
6. Approach 2 — Iterative (Explicit Stack)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

Given the `root` of a binary tree, return the **inorder traversal** of its nodes' values.

Inorder traversal visits nodes in the order: **Left → Root → Right**.

---

# Examples

## Example 1

Input

```text
    1
     \
      2
     /
    3
```

Output

```text
[1, 3, 2]
```

---

## Example 2

Input

```text
    (empty)
```

Output

```text
[]
```

---

## Example 3

Input

```text
    1
```

Output

```text
[1]
```

---

# Constraints

```
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
```

---

# Understanding the Problem

**Inorder traversal** visits the left subtree, then the current node, then the right subtree.

```
        4
       / \
      2   5
     / \
    1   3

Inorder: [1, 2, 3, 4, 5]
```

For a **Binary Search Tree**, inorder traversal produces a **sorted** sequence. This is one of the most important properties of BSTs.

---

# Approach 1 — Recursive

Recurse left, visit root, recurse right.

### Algorithm

```
inorder(node, result):
    if node is null:
        return
    inorder(node.left, result)
    result.append(node.val)
    inorder(node.right, result)
```

Clean, direct, and mirrors the definition.

---

# Approach 2 — Iterative (Explicit Stack)

The follow-up challenge for this problem is always: **"Can you do it without recursion?"**

Use an explicit stack and a `current` pointer:

1. Drive `current` as far **left** as possible, pushing nodes.
2. Pop when `current` is null — this is the node to visit.
3. Move `current` to the popped node's **right** child.
4. Repeat.

### Algorithm

```
result  = []
stack   = []
current = root

while current is not null OR stack is not empty:

    while current is not null:
        stack.push(current)
        current = current.left

    current = stack.pop()
    result.append(current.val)
    current = current.right

return result
```

---

# Dry Run

Input

```
        4
       / \
      2   5
     / \
    1   3
```

---

## Recursive

```
inorder(4)
  inorder(2)
    inorder(1)
      inorder(null) → return
      visit 1  → result: [1]
      inorder(null) → return
    visit 2  → result: [1, 2]
    inorder(3)
      inorder(null) → return
      visit 3  → result: [1, 2, 3]
      inorder(null) → return
  visit 4  → result: [1, 2, 3, 4]
  inorder(5)
    inorder(null) → return
    visit 5  → result: [1, 2, 3, 4, 5]
    inorder(null) → return
```

---

## Iterative

```
current=4, stack=[]

Inner while: push 4, go left → push 2, go left → push 1, go left → null

stack=[4,2,1], current=null

Pop 1 → visit 1, current = 1.right = null    result:[1]
Pop 2 → visit 2, current = 2.right = 3       result:[1,2]

Inner while: push 3, go left → null
Pop 3 → visit 3, current = 3.right = null    result:[1,2,3]
Pop 4 → visit 4, current = 4.right = 5       result:[1,2,3,4]

Inner while: push 5, go left → null
Pop 5 → visit 5, current = 5.right = null    result:[1,2,3,4,5]

stack empty, current null → done
```

---

# Complexity Analysis

| Approach | Time | Space |
|----------|------|-------|
| Recursive | O(n) | O(h) call stack |
| Iterative | O(n) | O(h) explicit stack |

Both use O(h) space. For a balanced tree h = O(log n); for a skewed tree h = O(n).

---

# Edge Cases

## Empty Tree

```
root = null → return []
```

---

## Single Node

```
root = [1] → [1]
```

---

## Right-Skewed Tree

```
1 → 2 → 3 → 4

Inorder: [1, 2, 3, 4]
```

---

## Left-Skewed Tree

```
    4
   /
  3
 /
2
/
1

Inorder: [1, 2, 3, 4]
```

---

# Interview Discussion

### Why is inorder traversal of a BST sorted?

In a BST, for any node `x`, all left-subtree values are smaller and all right-subtree values are larger. Visiting Left → Root → Right processes values in ascending order by definition.

---

### Why is the iterative version harder?

Recursion uses the call stack implicitly. The iterative version makes the stack management explicit. The key insight is: push a node when you first encounter it, but visit it only when you've exhausted its entire left subtree.

---

### Can inorder be done with Morris Traversal?

Yes. Morris Traversal uses no stack and no recursion (O(1) extra space) by temporarily rewiring the tree's `right` pointers. Time is O(n) but involves modifying the tree structure during traversal.

---

# Common Mistakes

## Visiting Root Before Left Subtree

```
Wrong (this is Preorder):
result.append(node.val)
inorder(node.left, result)
inorder(node.right, result)
```

---

## Returning Early in Iterative Without Right Move

After popping and visiting a node, you **must** move `current` to `node.right`. Forgetting this skips the entire right subtree.

---

# Key Takeaways

- Inorder = Left → Root → Right. For a BST this produces sorted output.
- The recursive implementation is 4 lines; the iterative version uses a pointer and explicit stack.
- The iterative pattern (push-left, pop-visit, move-right) is the standard interview template.
- Space is O(h) for both approaches.

---

# Next Problem

➡ **Same Tree**

Uses pairwise DFS to check structural and value equality of two trees simultaneously.
