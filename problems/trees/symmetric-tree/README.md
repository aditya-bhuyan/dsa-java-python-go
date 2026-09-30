# Symmetric Tree

> Difficulty: **Easy**
> Topic: **Binary Tree, DFS, Mirror Recursion**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Approach 1 — Recursive (Mirror DFS)
6. Approach 2 — Iterative (Queue of Pairs)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Key Takeaways

---

# Problem Statement

Given the `root` of a binary tree, check whether it is a **mirror of itself** (i.e., symmetric around its center).

---

# Examples

## Example 1

Input

```text
        1
       / \
      2   2
     / \ / \
    3  4 4  3
```

Output

```text
true
```

---

## Example 2

Input

```text
        1
       / \
      2   2
       \   \
        3   3
```

Output

```text
false
```

Explanation: The right subtree of the left child is not mirrored with the left subtree of the right child.

---

# Constraints

```
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
```

---

# Understanding the Problem

A tree is symmetric if its **left subtree is a mirror of its right subtree**.

Two subtrees are mirrors when:

1. Both are `null` — symmetric.
2. One is `null` and the other isn't — asymmetric.
3. Both are non-null but have different values — asymmetric.
4. Both are non-null, same value, AND:
   - The **outer pair** mirrors: left's left ↔ right's right.
   - The **inner pair** mirrors: left's right ↔ right's left.

```
        1
       / \
      2   2
     / \ / \
    3  4 4  3

Outer pair: left(3) mirrors right(3)  ✓
Inner pair: left(4) mirrors right(4)  ✓
```

---

# Approach 1 — Recursive (Mirror DFS)

Define a helper `isMirror(left, right)`:

### Algorithm

```
isSymmetric(root):
    return isMirror(root.left, root.right)

isMirror(left, right):
    if left is null and right is null:
        return true
    if left is null or right is null:
        return false
    if left.val != right.val:
        return false
    return isMirror(left.left,  right.right)
       and isMirror(left.right, right.left)
```

The key is the **crossed** recursive calls: outer nodes mirror each other, inner nodes mirror each other.

---

# Approach 2 — Iterative (Queue of Pairs)

Enqueue pairs of nodes that should be mirror images. Process until the queue is empty or a mismatch is found.

### Algorithm

```
queue = [(root.left, root.right)]

while queue is not empty:
    left, right = dequeue()

    if left is null and right is null: continue
    if left is null or right is null:  return false
    if left.val != right.val:          return false

    enqueue( (left.left,  right.right) )   ← outer pair
    enqueue( (left.right, right.left)  )   ← inner pair

return true
```

---

# Dry Run

Input

```
        1
       / \
      2   2
     / \ / \
    3  4 4  3
```

## Recursive

```
isSymmetric(1) → isMirror(2, 2)
  2.val == 2.val ✓
  isMirror(3, 3)   ← outer
    3.val == 3.val ✓
    isMirror(null, null) → true
    isMirror(null, null) → true
    return true
  isMirror(4, 4)   ← inner
    4.val == 4.val ✓
    isMirror(null, null) → true
    isMirror(null, null) → true
    return true
  return true
```

---

## Asymmetric Example

```
        1
       / \
      2   2
       \   \
        3   3
```

```
isMirror(2, 2)
  2.val == 2.val ✓
  isMirror(null, 3)   ← outer: 2.left=null, 2.right=3
    left null, right not → return false
```

---

# Complexity Analysis

## Recursive

| Metric | Complexity |
|--------|------------|
| Time | O(n) — each node visited once |
| Space | O(h) — call stack |

## Iterative

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(w) — queue width |

---

# Edge Cases

## Single Node

```
root = [1] → isMirror(null, null) → true
```

---

## Root with One Child

```
    1
   /
  2

isMirror(2, null) → false
```

---

## All Same Values

```
    1
   / \
  1   1
 / \ / \
1  1 1  1

→ true
```

---

# Interview Discussion

### How does this differ from Same Tree?

`Same Tree` compares corresponding children: `(left.left, right.left)`.
`Symmetric Tree` compares mirrored children: `(left.left, right.right)` and `(left.right, right.left)`.

The structure is identical; only the pairing of recursive calls changes.

---

### Can you solve this with a single DFS without a helper?

Not cleanly. The symmetry check inherently needs two pointers that traverse from opposite sides simultaneously. A helper (or pair queue) is the natural structure.

---

# Common Mistakes

## Using Same-Tree Comparisons

```
Wrong:
isMirror(left.left,  right.left)   ← same side, not mirror
isMirror(left.right, right.right)  ← same side, not mirror

Correct:
isMirror(left.left,  right.right)  ← outer pair
isMirror(left.right, right.left)   ← inner pair
```

---

## Forgetting to Check `root` Itself

A tree with just one node is always symmetric. The root itself doesn't need to be compared against anything — only its left and right subtrees do.

---

# Key Takeaways

- Symmetric Tree = pairwise DFS with **crossed** child comparisons.
- `isMirror(left.left, right.right)` and `isMirror(left.right, right.left)`.
- Time O(n), Space O(h).
- This is the mirror variant of Same Tree — same structure, different pairing.

---

# Next Problem

➡ **Diameter of Binary Tree**

Applies postorder DFS where the answer lives **across** the root rather than being accumulated downward.
