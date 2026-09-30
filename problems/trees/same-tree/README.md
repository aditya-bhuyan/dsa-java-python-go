# Same Tree

> Difficulty: **Easy**
> Topic: **Binary Tree, DFS, Recursion**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Approach — Pairwise DFS
6. Dry Run
7. Complexity Analysis
8. Edge Cases
9. Interview Discussion
10. Common Mistakes
11. Key Takeaways

---

# Problem Statement

Given the roots of two binary trees `p` and `q`, write a function to check if they are the **same**.

Two binary trees are considered the same if they are **structurally identical**, and the nodes have the **same values**.

---

# Examples

## Example 1

Input

```text
p :   1          q :   1
     / \               / \
    2   3             2   3
```

Output

```text
true
```

---

## Example 2

Input

```text
p :   1          q :   1
     /                  \
    2                    2
```

Output

```text
false
```

Explanation: Different structure — `p` has a left child, `q` has a right child.

---

## Example 3

Input

```text
p :   1          q :   1
     / \               / \
    2   1             1   2
```

Output

```text
false
```

Explanation: Same structure, different values.

---

# Constraints

```
The number of nodes in both trees is in the range [0, 100].
-10^4 <= Node.val <= 10^4
```

---

# Understanding the Problem

Two trees are the same when:

1. Both are `null` — trivially equal (base case ✓).
2. One is `null` and the other isn't — unequal (structure mismatch).
3. Both are non-null but have different values — unequal.
4. Both are non-null with equal values — check left subtrees AND right subtrees recursively.

This is a **pairwise DFS** — traverse both trees simultaneously, comparing at each step.

---

# Approach — Pairwise DFS

### Algorithm

```
isSameTree(p, q):
    if p is null and q is null:
        return true

    if p is null or q is null:
        return false

    if p.val != q.val:
        return false

    return isSameTree(p.left, q.left) and isSameTree(p.right, q.right)
```

All four conditions are checked before recursing. If any mismatch is found, return `false` immediately.

---

# Dry Run

## Equal Trees

```
p:   1          q:   1
    / \              / \
   2   3            2   3
```

```
isSameTree(1, 1)
  1.val == 1.val ✓
  isSameTree(2, 2)
    2.val == 2.val ✓
    isSameTree(null, null) → true
    isSameTree(null, null) → true
    return true
  isSameTree(3, 3)
    3.val == 3.val ✓
    isSameTree(null, null) → true
    isSameTree(null, null) → true
    return true
  return true
```

---

## Structure Mismatch

```
p:   1          q:   1
    /                 \
   2                   2
```

```
isSameTree(1, 1)
  1.val == 1.val ✓
  isSameTree(2, null)
    p is not null, q is null → return false
  return false
```

---

# Complexity Analysis

| Metric | Complexity |
|--------|------------|
| Time | O(n) — visits every node at most once |
| Space | O(h) — call stack depth equals tree height |

For equal trees: visits all n nodes. For mismatched trees: stops at first difference.

---

# Edge Cases

## Both Empty

```
p = null, q = null → true
```

---

## One Empty, One Not

```
p = [1], q = null → false
```

---

## Same Structure, One Value Different

```
p: [1,2,3]
q: [1,2,4]   ← 4 ≠ 3

→ false
```

---

## Single Node, Equal

```
p = [1], q = [1] → true
```

---

# Interview Discussion

### Can you do this iteratively?

Yes. Use two queues (or a single queue of pairs), process both trees level by level:

```
queue = [(p, q)]

while queue:
    a, b = queue.dequeue()
    if a is null and b is null: continue
    if a is null or b is null:  return false
    if a.val != b.val:          return false
    queue.enqueue((a.left, b.left))
    queue.enqueue((a.right, b.right))

return true
```

---

### How does this relate to Symmetric Tree?

Symmetric Tree checks whether a **single** tree is a mirror of itself. It uses the same pairwise DFS pattern, but with mirrored child comparisons: `(left.left, right.right)` and `(left.right, right.left)`.

---

# Common Mistakes

## Checking Only Values, Not Structure

```
Wrong:
if p.val == q.val:
    return isSameTree(...)
# Fails when one subtree is null and the other isn't
```

Always handle null cases **before** accessing `.val`.

---

## Short-Circuit on First True

```
Wrong:
return isSameTree(p.left, q.left)
# Forgot to check right subtrees
```

Both subtrees must be equal: use `AND`.

---

# Key Takeaways

- Pairwise DFS: recurse both trees simultaneously, comparing at each node.
- The null cases must be handled first and separately.
- Time O(n), Space O(h).
- This exact pattern is the foundation for Symmetric Tree and Subtree of Another Tree.

---

# Next Problem

➡ **Symmetric Tree**

Applies the same pairwise DFS pattern to check whether a single tree is a mirror image of itself.
