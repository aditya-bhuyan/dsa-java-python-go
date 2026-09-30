# Linked List

> Category: **Data Structure**
> Difficulty: **Foundational**

---

# Table of Contents

1. What is a Linked List?
2. Singly Linked List
3. Fast and Slow Pointer
4. Reverse a Linked List
5. Complexity Summary
6. When to Use Linked Lists
7. Common Mistakes
8. Key Takeaways

---

# What is a Linked List?

A **Linked List** is a linear data structure where elements, called **nodes**, are stored in non-contiguous memory locations.

Each node holds:

```
┌─────────────┬──────────┐
│    data     │   next   │
└─────────────┴──────────┘
```

- **data** — the value stored in the node.
- **next** — a pointer (or reference) to the next node in the sequence.

Unlike arrays, linked lists do not require contiguous memory. Nodes are connected through pointers.

---

## Array vs Linked List

| Operation | Array | Linked List |
|-----------|-------|-------------|
| Access by index | O(1) | O(n) |
| Insert at head | O(n) | O(1) |
| Insert at tail | O(1) amortized | O(n) without tail pointer |
| Delete from head | O(n) | O(1) |
| Search | O(n) | O(n) |
| Memory | Contiguous | Non-contiguous |

---

# Singly Linked List

A **Singly Linked List** is the simplest form of a linked list. Each node points only to the **next** node. There is no backward link.

## Structure

```
HEAD
  │
  ▼
┌────┬────┐   ┌────┬────┐   ┌────┬────┐   ┌────┬──────┐
│  1 │  ──┼──▶│  2 │  ──┼──▶│  3 │  ──┼──▶│  4 │ null │
└────┴────┘   └────┴────┘   └────┴────┘   └────┴──────┘
```

The last node's `next` pointer is `null` (or `None` in Python), marking the end of the list.

---

## Node Definition

**Java**

```java
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
```

**Python**

```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
```

**Go**

```go
type ListNode struct {
    Val  int
    Next *ListNode
}
```

---

## Common Operations

### Traversal

Walk from `head` until `next` is `null`.

```
current = head

while current != null:
    process(current.val)
    current = current.next
```

Time: O(n) — every node is visited once.

---

### Insert at Head

Create a new node and point its `next` to the current head.

```
newNode.next = head
head = newNode
```

Time: O(1)

---

### Insert at Tail

Traverse to the last node and update its `next`.

```
current = head

while current.next != null:
    current = current.next

current.next = newNode
```

Time: O(n)

---

### Delete a Node

Link the predecessor directly to the successor, skipping the target node.

```
prev.next = prev.next.next
```

Time: O(n) to find the node, O(1) to remove it.

---

## Key Properties

- **No random access** — You cannot jump to index `i` in O(1). You must walk from the head.
- **Dynamic size** — Nodes can be added or removed without resizing.
- **Head pointer is critical** — Losing the head means losing the entire list.

---

# Fast and Slow Pointer

The **Fast and Slow Pointer** technique — also called the **Floyd's Tortoise and Hare** algorithm — uses two pointers that traverse the list at different speeds.

```
slow moves 1 step at a time
fast moves 2 steps at a time
```

This technique is powerful because it solves problems without extra memory, using only O(1) space.

---

## Visual

```
Initial State

slow
 │
 ▼
[1] → [2] → [3] → [4] → [5] → null
 ▲
fast


After 1 step

       slow
        │
        ▼
[1] → [2] → [3] → [4] → [5] → null
              ▲
             fast


After 2 steps

             slow
              │
              ▼
[1] → [2] → [3] → [4] → [5] → null
                          ▲
                         fast
```

When `fast` reaches the end, `slow` is at the middle.

---

## Algorithm Template

```
slow = head
fast = head

while fast != null and fast.next != null:
    slow = slow.next
    fast = fast.next.next
```

---

## Applications

| Problem | How Fast/Slow Helps |
|---------|---------------------|
| Find middle of list | When fast reaches end, slow is at middle |
| Detect cycle | If fast and slow ever meet, a cycle exists |
| Find cycle entry point | Reset one pointer to head after detection |
| Check if palindrome | Find middle, reverse second half, compare |
| Nth node from end | Start fast N steps ahead, then move both |

---

## Why Two Steps?

When `fast` moves twice as fast as `slow`:

- In a list of length `n`, when `fast` reaches the end (or `null`), `slow` has covered exactly `n/2` steps.
- In a cyclic list, the gap between `fast` and `slow` decreases by 1 each iteration, guaranteeing they will eventually meet.

---

## Cycle Detection — Core Insight

```
List with a cycle:

[1] → [2] → [3] → [4]
                   │
                   ▼
              [6] ← [5]
```

`fast` and `slow` both enter the cycle. Since `fast` gains 1 node per step relative to `slow`, the gap closes to zero — they meet inside the cycle.

If `fast` reaches `null`, there is no cycle.

---

# Reverse a Linked List

Reversing a linked list means making each node point to its **previous** node instead of the next, and updating the head to the original tail.

## Before

```
HEAD
  │
  ▼
[1] → [2] → [3] → [4] → null
```

## After

```
                        HEAD
                          │
                          ▼
null ← [1] ← [2] ← [3] ← [4]
```

---

## Iterative Approach

Use three pointers:

- `prev` — the node that will become the next of `current`.
- `current` — the node being processed.
- `next` — temporary storage for `current.next` before it is overwritten.

### Algorithm

```
prev    = null
current = head

while current != null:
    next        = current.next   ← save the next node
    current.next = prev          ← reverse the pointer
    prev        = current        ← advance prev
    current     = next           ← advance current

head = prev
```

### Step-by-Step Dry Run

```
Input: [1] → [2] → [3] → null

Initial

prev    = null
current = [1]

---

Step 1

next         = [2]
current.next = null        → [1] → null
prev         = [1]
current      = [2]

---

Step 2

next         = [3]
current.next = [1]         → [2] → [1] → null
prev         = [2]
current      = [3]

---

Step 3

next         = null
current.next = [2]         → [3] → [2] → [1] → null
prev         = [3]
current      = null

---

Loop ends. New head = [3]

Result: [3] → [2] → [1] → null
```

---

## Recursive Approach

```
reverse(node):
    if node == null or node.next == null:
        return node

    newHead = reverse(node.next)

    node.next.next = node
    node.next = null

    return newHead
```

The recursion unwinds from the tail and rewires the pointers as the call stack returns.

---

## Complexity

| Approach | Time | Space |
|----------|------|-------|
| Iterative | O(n) | O(1) |
| Recursive | O(n) | O(n) call stack |

The **iterative approach is preferred** in interviews because it uses constant space.

---

## Common Pattern: Reverse a Sublist

Many problems ask you to reverse only part of a list (e.g., nodes from position `m` to `n`).

The core reversal logic is the same — you simply stop after `n - m + 1` iterations and reconnect the boundary nodes.

---

# Complexity Summary

| Operation | Time | Space |
|-----------|------|-------|
| Traversal | O(n) | O(1) |
| Insert at head | O(1) | O(1) |
| Insert at tail | O(n) | O(1) |
| Delete node | O(n) | O(1) |
| Reverse (iterative) | O(n) | O(1) |
| Find middle (fast/slow) | O(n) | O(1) |
| Detect cycle (fast/slow) | O(n) | O(1) |

---

# When to Use Linked Lists

Use a linked list when:

- You need frequent insertions or deletions at the head.
- The size of the data changes frequently and you want to avoid resizing.
- You are implementing a stack, queue, or LRU cache.

Prefer an array when:

- You need fast random access by index.
- Memory overhead from pointers is a concern.
- Cache performance matters (arrays have better locality).

---

# Common Mistakes

## Losing the Head

Always preserve a reference to `head`. If you advance `head = head.next` without saving the original, you lose access to the beginning of the list.

---

## Null Pointer Errors

Always check `node != null` before accessing `node.next`.

```
Wrong

current.next.next  ← crashes if current.next is null

Correct

if current.next != null:
    current.next.next
```

---

## Off-by-One in Fast/Slow

The loop condition for fast/slow depends on the problem:

```
Middle of list     → while fast != null and fast.next != null
Cycle detection    → while fast != null and fast.next != null
```

Always verify whether `fast.next` or `fast.next.next` would be null before accessing it.

---

## Forgetting to Set `next = null` When Reversing

After reversal, the original head node now points to its former predecessor. If you don't set its `next` to `null`, you create a cycle in the reversed list.

---

# Key Takeaways

After studying this concept, you should understand:

- How a singly linked list is structured and how to traverse it.
- The fast and slow pointer pattern and why it works.
- How to reverse a linked list iteratively in O(n) time and O(1) space.
- The difference between linked lists and arrays in terms of access patterns and performance.
- How cycle detection, middle-finding, and reversal are all connected through the same pointer manipulation ideas.

These patterns appear directly in interview problems such as Reverse Linked List, Middle of Linked List, Linked List Cycle, and Merge Two Sorted Lists — all covered in the problems section.

---

# Problems Covered

| Problem | Concept Applied |
|---------|----------------|
| Reverse Linked List | Iterative reversal with three pointers |
| Middle Node | Fast and slow pointer |
| Cycle Detection | Fast and slow pointer — Floyd's algorithm |
| Merge Two Sorted Lists | Two-pointer traversal across two lists |
