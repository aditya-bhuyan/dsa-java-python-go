# Reverse Linked List

> Difficulty: **Easy**
> Topic: **Linked List, Iterative Reversal**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Iterative)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given the `head` of a singly linked list, reverse the list and return the reversed list's head.

---

# Examples

## Example 1

Input

```text
1 → 2 → 3 → 4 → 5 → null
```

Output

```text
5 → 4 → 3 → 2 → 1 → null
```

---

## Example 2

Input

```text
1 → 2 → null
```

Output

```text
2 → 1 → null
```

---

## Example 3

Input

```text
1 → null
```

Output

```text
1 → null
```

---

# Constraints

```
The number of nodes is in the range [0, 5000].
-5000 <= Node.val <= 5000
```

---

# Understanding the Problem

Each node in the list has a `val` and a `next` pointer.

Reversing means rewiring each `next` pointer to point backward.

Before

```
HEAD
  │
  ▼
[1] → [2] → [3] → [4] → [5] → null
```

After

```
                              HEAD
                                │
                                ▼
null ← [1] ← [2] ← [3] ← [4] ← [5]
```

The original tail `[5]` becomes the new head.
The original head `[1]` now points to `null`.

---

# Approach 1 — Brute Force (Collect and Rebuild)

Collect all values into an array, then rebuild the list in reverse order.

Pseudo Algorithm

```
values = []

current = head
while current != null:
    values.append(current.val)
    current = current.next

Rebuild list from values in reverse:

new_head = null
for val in values:
    newNode.next = new_head
    new_head = newNode
```

### Advantages

- Simple to understand.

### Disadvantages

- Requires O(n) extra memory for the value array.
- Creates entirely new nodes rather than rewiring existing pointers.

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

# Approach 2 — Iterative (Optimal)

Reverse the list in a single pass using three pointers:

- `prev` — tracks the previously processed node (starts as `null`).
- `current` — the node currently being reversed.
- `next` — temporary hold of `current.next` before it is overwritten.

### Algorithm

```
prev    = null
current = head

while current != null:
    next         = current.next
    current.next = prev
    prev         = current
    current      = next

return prev
```

`prev` ends up pointing at the new head (the original tail).

---

# Dry Run

Input

```
[1] → [2] → [3] → null
```

---

Initial state

```
prev    = null
current = [1]
```

---

Step 1

```
next         = [2]
current.next = null         [1] → null
prev         = [1]
current      = [2]
```

---

Step 2

```
next         = [3]
current.next = [1]          [2] → [1] → null
prev         = [2]
current      = [3]
```

---

Step 3

```
next         = null
current.next = [2]          [3] → [2] → [1] → null
prev         = [3]
current      = null
```

---

Loop ends.

New head = `[3]`

Result

```
[3] → [2] → [1] → null
```

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(n) |

---

## Iterative (Optimal)

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(1) |

The iterative approach is preferred because it uses no extra memory.

---

# Edge Cases

## Empty List

```
head = null → return null
```

---

## Single Node

```
[1] → null → return [1] → null
```

No reversal needed.

---

## Two Nodes

```
[1] → [2] → null → [2] → [1] → null
```

Standard reversal with a single iteration.

---

# Interview Discussion

### Why not use a stack?

A stack collects values and pops them in reverse order. It works but uses O(n) extra memory. The iterative three-pointer approach achieves the same result in O(1) space.

---

### What happens if you forget to save `next` before overwriting?

```
current.next = prev    ← you now cannot reach the rest of the list
```

Always save `next = current.next` before changing any pointers.

---

### Can you solve this recursively?

Yes.

```
reverse(node):
    if node == null or node.next == null:
        return node

    newHead = reverse(node.next)

    node.next.next = node
    node.next = null

    return newHead
```

Time: O(n), Space: O(n) due to call stack.
The iterative approach is preferred in interviews.

---

# Common Mistakes

## Forgetting to Set `current.next = null` for the Original Head

After reversal, the original `head` still has its old `next` pointer value until you overwrite it. The algorithm handles this correctly because `prev` starts as `null` and is assigned in the first iteration.

---

## Advancing `current` After Losing `next`

Always:

```
1. Save   next = current.next
2. Rewire current.next = prev
3. Move   prev = current
4. Move   current = next
```

Any other order loses the forward reference.

---

# Follow-up Questions

- Reverse a linked list between positions `m` and `n` (Reverse Linked List II).
- Check if a linked list is a palindrome (requires reversal of the second half).
- Reverse nodes in k-group.

---

# Key Takeaways

After solving this problem, you should understand:

- The three-pointer iterative reversal pattern.
- Why saving `next` before overwriting is essential.
- The difference between O(1)-space iterative and O(n)-space recursive approaches.
- How pointer manipulation replaces the need for extra memory.

---

# Next Problem

➡ **Middle Node**

This introduces the **Fast and Slow Pointer** technique to find the midpoint of a linked list in a single pass.
