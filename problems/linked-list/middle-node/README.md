# Middle of the Linked List

> Difficulty: **Easy**
> Topic: **Linked List, Fast and Slow Pointer**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Fast and Slow Pointer)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given the `head` of a singly linked list, return the **middle node** of the linked list.

If there are two middle nodes, return the **second middle node**.

---

# Examples

## Example 1

Input

```text
1 → 2 → 3 → 4 → 5 → null
```

Output

```text
Node with value 3
```

Explanation

```
The list has 5 nodes.
The middle is at position 3 (1-indexed).
```

---

## Example 2

Input

```text
1 → 2 → 3 → 4 → 5 → 6 → null
```

Output

```text
Node with value 4
```

Explanation

```
The list has 6 nodes — two midpoints at positions 3 and 4.
Return the second middle: node with value 4.
```

---

# Constraints

```
The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
```

---

# Understanding the Problem

We need to find the node that sits at the halfway point of the list.

For odd-length lists, there is exactly one middle.

For even-length lists, there are two candidates — the problem asks for the **second** one.

```
Odd  (length 5): [1] [2] [3] [4] [5]
                           ↑
                         middle (index 2, value 3)

Even (length 6): [1] [2] [3] [4] [5] [6]
                                ↑
                          second middle (index 3, value 4)
```

---

# Approach 1 — Brute Force (Count and Traverse)

Count all nodes first, then traverse to the middle index.

Pseudo Algorithm

```
count = 0
current = head

while current != null:
    count++
    current = current.next

middle = count / 2

current = head
for i in range(middle):
    current = current.next

return current
```

### Advantages

- Easy to understand.

### Disadvantages

- Two passes through the list.

---

## Complexity

Time

```
O(n)
```

Space

```
O(1)
```

---

# Approach 2 — Fast and Slow Pointer (Optimal)

Use two pointers:

- `slow` moves **1 step** at a time.
- `fast` moves **2 steps** at a time.

When `fast` reaches the end of the list, `slow` is at the middle.

### Algorithm

```
slow = head
fast = head

while fast != null and fast.next != null:
    slow = slow.next
    fast = fast.next.next

return slow
```

Only one pass. No extra memory.

---

# Dry Run

## Odd-length list

Input

```
[1] → [2] → [3] → [4] → [5] → null
```

---

Initial

```
slow = [1]
fast = [1]
```

---

Step 1

```
slow = [2]
fast = [3]
```

---

Step 2

```
slow = [3]
fast = [5]
```

---

Step 3

```
fast.next = null → loop condition fails → stop
```

---

Return `slow = [3]`

---

## Even-length list

Input

```
[1] → [2] → [3] → [4] → [5] → [6] → null
```

---

Initial

```
slow = [1]
fast = [1]
```

---

Step 1

```
slow = [2]
fast = [3]
```

---

Step 2

```
slow = [3]
fast = [5]
```

---

Step 3

```
slow = [4]
fast = null  (fast.next was [6], fast.next.next was null → moved fast to null)
```

Wait — let's re-examine:

After Step 2: `fast = [5]`, `fast.next = [6]` (not null), so loop continues.

Step 3

```
slow = [4]
fast = [6].next = null
```

Now `fast = null` → loop stops.

Return `slow = [4]` (second middle).

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(1) |

---

## Fast and Slow Pointer

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(1) |

Both approaches are O(n) time and O(1) space. The fast/slow pointer approach solves it in **one pass** instead of two.

---

# Edge Cases

## Single Node

```
[1] → null
```

`fast` is not null, `fast.next` is null → loop does not execute.

Return `slow = [1]`.

---

## Two Nodes

```
[1] → [2] → null
```

Step 1

```
slow = [2]
fast = null  (fast = [1].next.next = null)
```

Return `slow = [2]` (second middle).

---

# Interview Discussion

### Why does slow reach the middle when fast reaches the end?

`fast` moves at twice the speed of `slow`. When `fast` has traveled distance `n`, `slow` has traveled `n/2`. Since the list has length `n`, `slow` is at position `n/2` — the midpoint.

---

### Why return the second middle for even-length lists?

The loop condition `fast.next != null` stops slow one step past the first middle for even-length lists. This is the conventional behavior for this problem (matching LeetCode 876).

---

### Can you modify this to return the first middle for even-length lists?

Yes. Change the loop condition:

```
while fast.next != null and fast.next.next != null:
```

This stops `slow` one step earlier, at the first middle.

---

# Common Mistakes

## Checking Only `fast != null`

```
Wrong:

while fast != null:
    slow = slow.next
    fast = fast.next.next   ← crashes when fast.next is null
```

Always check both `fast != null` and `fast.next != null`.

---

## Returning `fast` Instead of `slow`

`fast` is at the end. `slow` is at the middle. Return `slow`.

---

# Follow-up Questions

- Check if a linked list is a palindrome (find middle, reverse second half, compare).
- Reorder list: L0 → Ln → L1 → Ln-1 (find middle, reverse second half, merge).
- Find the kth node from the end (start fast k steps ahead, then move both).

---

# Key Takeaways

After solving this problem, you should understand:

- The fast and slow pointer pattern for finding the midpoint in one pass.
- Why `slow` is always at position `n/2` when `fast` is at position `n`.
- The difference in behavior between odd-length and even-length lists.
- How this pattern is used as a building block for harder linked list problems.

---

# Next Problem

➡ **Cycle Detection**

This applies the same **fast and slow pointer** technique to detect whether a linked list contains a cycle.
