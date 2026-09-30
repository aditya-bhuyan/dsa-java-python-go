# Linked List Cycle Detection

> Difficulty: **Easy**
> Topic: **Linked List, Fast and Slow Pointer, Floyd's Algorithm**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Floyd's Tortoise and Hare)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given the `head` of a linked list, determine if the linked list has a **cycle** in it.

A cycle exists if there is some node in the list that can be reached again by continuously following the `next` pointer.

Return `true` if a cycle exists, `false` otherwise.

---

# Examples

## Example 1 — Cycle Exists

Input

```text
1 → 2 → 3 → 4
             ↑         ↓
             6 ← ← ← 5
```

Output

```text
true
```

Explanation

Node 4's `next` points back to node 2, creating a loop.

---

## Example 2 — No Cycle

Input

```text
1 → 2 → 3 → 4 → null
```

Output

```text
false
```

---

## Example 3 — Single Node, Self-Loop

Input

```text
1 → (points back to itself)
```

Output

```text
true
```

---

# Constraints

```
The number of nodes in the list is in the range [0, 10^4].
-10^5 <= Node.val <= 10^5
```

---

# Understanding the Problem

In a normal linked list, traversal always terminates at a node whose `next` is `null`.

In a cyclic list, following `next` indefinitely loops back to a previously visited node — traversal never terminates.

```
No Cycle

[1] → [2] → [3] → [4] → null

       Traversal terminates naturally.

Cycle

[1] → [2] → [3] → [4]
              ↑          ↓
              [6] ← ← [5]

       Traversal loops forever between [3], [4], [5], [6].
```

---

# Approach 1 — Brute Force (Hash Set)

Store every visited node in a hash set. If a node is seen twice, a cycle exists.

Pseudo Algorithm

```
visited = empty set

current = head

while current != null:
    if current in visited:
        return true
    visited.add(current)
    current = current.next

return false
```

### Advantages

- Easy to understand.

### Disadvantages

- O(n) extra memory for the hash set.

---

## Complexity

Time

```
O(n)
```

Space

```
O(n)
```

---

# Approach 2 — Floyd's Tortoise and Hare (Optimal)

Use two pointers:

- `slow` moves **1 step** at a time.
- `fast` moves **2 steps** at a time.

**Key insight:** If a cycle exists, `fast` will eventually lap `slow` and they will meet at the same node. If there is no cycle, `fast` reaches `null`.

### Algorithm

```
slow = head
fast = head

while fast != null and fast.next != null:
    slow = slow.next
    fast = fast.next.next

    if slow == fast:
        return true

return false
```

No extra memory. O(1) space.

---

# Dry Run

## No Cycle

Input

```
[1] → [2] → [3] → null
```

---

Initial

```
slow = [1], fast = [1]
```

Step 1

```
slow = [2], fast = [3]
slow ≠ fast
```

Step 2

```
fast.next = null → loop condition fails
```

Return `false`

---

## With Cycle

Input

```
[3] → [2] → [0] → [-4]
              ↑           ↓
              (tail next = [2])
```

Values: 3, 2, 0, -4 (tail points back to index 1)

---

Initial

```
slow = [3], fast = [3]
```

Step 1

```
slow = [2]
fast = [0]
slow ≠ fast
```

Step 2

```
slow = [0]
fast = [2]    (fast moved: [-4] → [2])
slow ≠ fast
```

Step 3

```
slow = [-4]
fast = [-4]   (fast moved: [0] → [-4])
slow == fast
```

Return `true`

---

# Complexity Analysis

## Hash Set

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(n) |

---

## Floyd's Algorithm

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(1) |

Floyd's algorithm is preferred because it uses constant space.

---

# Edge Cases

## Empty List

```
head = null → return false
```

---

## Single Node, No Cycle

```
[1] → null → return false
```

---

## Single Node, Self-Loop

```
[1] → [1] (next points to itself)
```

Step 1

```
slow = [1]
fast = [1]   (fast.next.next = [1])
slow == fast → return true
```

---

## Two Nodes, Cycle

```
[1] → [2] → [1]
```

Step 1

```
slow = [2]
fast = [1]
slow ≠ fast
```

Step 2

```
slow = [1]
fast = [1]
slow == fast → return true
```

---

# Interview Discussion

### Why do fast and slow always meet if there is a cycle?

Once both pointers enter the cycle, consider the gap between them. Each step, `fast` gains 1 position relative to `slow` (since fast moves 2, slow moves 1). The gap decreases by 1 every iteration. Eventually the gap reaches 0 and they occupy the same node.

---

### Why doesn't fast skip over slow?

The gap decreases by exactly 1 per step. It goes from some positive value down to 1, then to 0. It cannot jump from 1 to -1 because that would require fast to skip two positions in a single step, but fast moves to the very next position relative to slow — which is exactly the 0-gap case.

---

### What if you need to find the start of the cycle?

After detection, reset one pointer to `head` and advance both one step at a time. They will meet at the cycle entry point. This is a follow-up problem (Linked List Cycle II).

---

# Common Mistakes

## Comparing Node Values Instead of Node References

A cycle means the same node object is visited twice. Value comparison is incorrect — two different nodes may have the same value.

```
Wrong:  slow.val == fast.val
Correct: slow == fast
```

---

## Missing the Inner Equality Check

The equality check must happen **inside** the loop, not just at loop exit.

```
Wrong:

while fast != null and fast.next != null:
    slow = slow.next
    fast = fast.next.next

return slow == fast   ← only checks after loop, which exits when fast is null
```

---

# Follow-up Questions

- Find the node where the cycle begins (Linked List Cycle II).
- Find the length of the cycle.
- Detect cycle in a graph (different structure, same concept).

---

# Key Takeaways

After solving this problem, you should understand:

- How Floyd's Tortoise and Hare algorithm detects cycles in O(1) space.
- Why fast and slow pointers always converge when a cycle exists.
- The difference between comparing node references versus node values.
- How this pattern connects to finding the cycle entry point and cycle length.

---

# Next Problem

➡ **Merge Two Sorted Lists**

This problem applies a two-pointer traversal across two separate lists to merge them in sorted order.
