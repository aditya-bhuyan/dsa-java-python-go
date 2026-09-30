# Merge Two Sorted Lists

> Difficulty: **Easy**
> Topic: **Linked List, Two Pointers**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Two Pointer Merge)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one **sorted** list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

---

# Examples

## Example 1

Input

```text
list1 : 1 → 2 → 4 → null
list2 : 1 → 3 → 4 → null
```

Output

```text
1 → 1 → 2 → 3 → 4 → 4 → null
```

---

## Example 2

Input

```text
list1 : null
list2 : null
```

Output

```text
null
```

---

## Example 3

Input

```text
list1 : null
list2 : 0 → null
```

Output

```text
0 → null
```

---

# Constraints

```
The number of nodes in each list is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
```

---

# Understanding the Problem

Both input lists are already sorted. We need to produce a single sorted list by choosing nodes from each input list in the correct order.

```
list1 : [1] → [2] → [4]
list2 : [1] → [3] → [4]

At each step, compare the front nodes of both lists.
Take the smaller one and advance that list's pointer.

Step 1: 1 vs 1  → take list1[1] (tie → pick either, convention: list1)
Step 2: 2 vs 1  → take list2[1]
Step 3: 2 vs 3  → take list1[2]
Step 4: 4 vs 3  → take list2[3]
Step 5: 4 vs 4  → take list1[4]
Step 6: list1 exhausted → attach remaining list2 node [4]

Result: 1 → 1 → 2 → 3 → 4 → 4
```

---

# Approach 1 — Brute Force (Collect and Sort)

Collect all node values into an array, sort the array, then rebuild the list.

Pseudo Algorithm

```
values = []

while list1 != null: collect list1.val; advance list1
while list2 != null: collect list2.val; advance list2

sort(values)

rebuild linked list from sorted values
```

### Advantages

- Simple.

### Disadvantages

- Creates new nodes instead of reusing existing ones.
- O(n log n) due to sorting, even though the inputs are already sorted.
- O(n) extra memory.

---

## Complexity

Time

```
O((m + n) log(m + n))
```

Space

```
O(m + n)
```

---

# Approach 2 — Two Pointer Merge (Optimal)

Compare the current nodes of both lists. Attach the smaller node to the merged list, then advance that pointer.

Use a **dummy head node** to simplify edge cases — no special handling needed for the first node.

### Algorithm

```
dummy   = new Node(0)
current = dummy

while list1 != null and list2 != null:

    if list1.val <= list2.val:
        current.next = list1
        list1 = list1.next
    else:
        current.next = list2
        list2 = list2.next

    current = current.next

# Attach remaining nodes.
if list1 != null:
    current.next = list1
else:
    current.next = list2

return dummy.next
```

The dummy head eliminates the need for a separate "first node" check.

---

# Dry Run

Input

```
list1 : [1] → [2] → [4] → null
list2 : [1] → [3] → [4] → null
```

---

Initial

```
dummy   → null
current = dummy
```

---

Step 1 — Compare 1 vs 1 → tie, pick list1

```
current.next = list1[1]
list1        = [2]
current      = [1]

Merged: dummy → [1]
```

---

Step 2 — Compare 2 vs 1 → pick list2

```
current.next = list2[1]
list2        = [3]
current      = [1]

Merged: dummy → [1] → [1]
```

---

Step 3 — Compare 2 vs 3 → pick list1

```
current.next = list1[2]
list1        = [4]
current      = [2]

Merged: dummy → [1] → [1] → [2]
```

---

Step 4 — Compare 4 vs 3 → pick list2

```
current.next = list2[3]
list2        = [4]
current      = [3]

Merged: dummy → [1] → [1] → [2] → [3]
```

---

Step 5 — Compare 4 vs 4 → tie, pick list1

```
current.next = list1[4]
list1        = null
current      = [4]

Merged: dummy → [1] → [1] → [2] → [3] → [4]
```

---

list1 is null → loop ends. Attach remaining list2.

```
current.next = list2[4]
```

---

Result

```
dummy → [1] → [1] → [2] → [3] → [4] → [4] → null
```

Return `dummy.next` = `[1]`

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|--------|------------|
| Time | O((m+n) log(m+n)) |
| Space | O(m+n) |

---

## Two Pointer Merge

| Metric | Complexity |
|--------|------------|
| Time | O(m + n) |
| Space | O(1) |

Where:

- **m** = length of list1
- **n** = length of list2

The two-pointer approach is optimal. It merges in a single pass and reuses existing nodes — no new allocations.

---

# Edge Cases

## Both Lists Empty

```
list1 = null, list2 = null → return null
```

---

## One List Empty

```
list1 = null, list2 = [1] → [2]
```

Loop does not execute. Attach `list2` directly.

Return `[1] → [2]`.

---

## Lists of Different Lengths

The shorter list is exhausted first. The `current.next = remaining` step attaches the rest in O(1).

---

## Duplicate Values Across Lists

Both values equal → attach list1's node (or list2's — both correct). The algorithm handles ties correctly because `<=` picks list1 when equal.

---

# Interview Discussion

### Why use a dummy head node?

Without a dummy, you need special handling to initialize the head of the merged list:

```
if list1.val <= list2.val:
    head = list1
    list1 = list1.next
else:
    head = list2
    list2 = list2.next
```

The dummy eliminates this conditional. You always do `current.next = chosen; current = current.next` inside the loop.

---

### Can you solve this recursively?

Yes.

```
merge(l1, l2):
    if l1 == null: return l2
    if l2 == null: return l1

    if l1.val <= l2.val:
        l1.next = merge(l1.next, l2)
        return l1
    else:
        l2.next = merge(l1, l2.next)
        return l2
```

Time: O(m + n), Space: O(m + n) call stack.
The iterative approach is preferred for O(1) space.

---

### Does this create new nodes?

No. The merge reuses the existing nodes from `list1` and `list2` by rewiring their `next` pointers.

---

# Common Mistakes

## Forgetting to Attach the Remaining List

When one list is exhausted, the other may still have nodes. Always attach with:

```
current.next = (list1 != null) ? list1 : list2
```

---

## Modifying Values Instead of Rewiring Pointers

Swapping values is incorrect if the node objects are referenced elsewhere. Always move nodes by adjusting `next` pointers.

---

# Follow-up Questions

- Merge k sorted lists (heap / divide-and-conquer).
- Sort a linked list (merge sort on linked lists).
- Remove duplicates from two merged sorted lists.

---

# Key Takeaways

After solving this problem, you should understand:

- The two-pointer merge pattern for sorted inputs.
- Why a dummy head simplifies the list construction.
- How to attach the remainder of an unfinished list in O(1).
- The difference between O(m+n) iterative merge and O((m+n)log(m+n)) sort-and-build.

---

# Previous Problem

⬅ **Cycle Detection**

Uses the fast and slow pointer to detect whether a cycle exists.
