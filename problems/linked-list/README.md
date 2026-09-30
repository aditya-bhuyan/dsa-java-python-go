# Linked List Problems

> **Week 4** · Core concept: pointer manipulation, fast/slow pointer, in-place reversal.

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Reverse Linked List | Easy | [reverse-linked-list/](reverse-linked-list/) |
| 2 | Middle of the Linked List | Easy | [middle-node/](middle-node/) |
| 3 | Linked List Cycle | Easy | [cycle-detection/](cycle-detection/) |
| 4 | Merge Two Sorted Lists | Easy | [merge-lists/](merge-lists/) |

## Key Patterns
- **Iterative reversal** — `prev → cur → next` pointer walk
- **Fast/slow pointer** — slow moves 1 step, fast moves 2; meet at cycle or midpoint
- **Dummy head node** — simplifies edge cases in merge/insert problems
- **Two pointer merge** — interleave two sorted lists in O(n)

## Node definition (all 3 languages)
```java
class ListNode { int val; ListNode next; ListNode(int v) { val = v; } }
```
```python
class ListNode:
    def __init__(self, val=0, next=None): self.val = val; self.next = next
```
```go
type ListNode struct { Val int; Next *ListNode }
```

## Concepts
→ [concepts/linked-list.md](../../concepts/linked-list.md)

← [Back to problems](../README.md)
