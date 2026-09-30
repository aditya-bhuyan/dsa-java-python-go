"""
===============================================================================
Problem: Reverse Linked List
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given the head of a singly linked list, reverse the list and
return the new head.

Example

Input

1 → 2 → 3 → 4 → 5 → None

Output

5 → 4 → 3 → 2 → 1 → None

===============================================================================

Algorithm
---------

Iterative three-pointer approach.

prev    starts as None.
current starts at head.

Each iteration:

    1. next_node     = current.next   (save the forward link)
    2. current.next  = prev           (reverse the pointer)
    3. prev          = current        (advance prev)
    4. current       = next_node      (advance current)

When current is None, prev is the new head.

===============================================================================

Dry Run
-------

Input: [1] → [2] → [3] → None

Initial: prev = None, current = [1]

Step 1
  next_node    = [2]
  current.next = None       → [1] → None
  prev         = [1]
  current      = [2]

Step 2
  next_node    = [3]
  current.next = [1]        → [2] → [1] → None
  prev         = [2]
  current      = [3]

Step 3
  next_node    = None
  current.next = [2]        → [3] → [2] → [1] → None
  prev         = [3]
  current      = None

Return prev = [3]

===============================================================================

Complexity Analysis
-------------------

Time Complexity

O(n)

Each node is visited exactly once.

Space Complexity

O(1)

Only three pointer variables regardless of list length.

===============================================================================
"""

from __future__ import annotations
from typing import Optional


class ListNode:
    """Represents a node in a singly linked list."""

    def __init__(self, val: int = 0, next: Optional["ListNode"] = None) -> None:
        self.val = val
        self.next = next


class ReverseLinkedList:
    """
    Solution class implementing iterative linked list reversal.
    """

    def reverse(self, head: Optional[ListNode]) -> Optional[ListNode]:
        """
        Reverses a singly linked list.

        Parameters
        ----------
        head : Optional[ListNode]

            The head of the list (may be None).

        Returns
        -------
        Optional[ListNode]

            The new head of the reversed list.
        """

        prev: Optional[ListNode] = None
        current = head

        while current is not None:

            #
            # Save the next node before overwriting.
            #
            next_node = current.next

            #
            # Reverse the pointer.
            #
            current.next = prev

            #
            # Advance both pointers.
            #
            prev = current
            current = next_node

        #
        # prev is now the new head.
        #
        return prev


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def build_list(values: list[int]) -> Optional[ListNode]:
    """Constructs a linked list from a list of integers."""
    if not values:
        return None
    head = ListNode(values[0])
    current = head
    for v in values[1:]:
        current.next = ListNode(v)
        current = current.next
    return head


def list_to_array(head: Optional[ListNode]) -> list[int]:
    """Converts a linked list to a Python list."""
    result = []
    while head:
        result.append(head.val)
        head = head.next
    return result


def print_list(head: Optional[ListNode]) -> None:
    """Prints the linked list."""
    parts = []
    while head:
        parts.append(str(head.val))
        head = head.next
    print(" → ".join(parts) + " → null")


def main() -> None:
    """Demonstrates the Reverse Linked List algorithm."""

    solution = ReverseLinkedList()

    values = [1, 2, 3, 4, 5]
    head = build_list(values)

    print("=" * 60)
    print("Reverse Linked List")
    print("=" * 60)

    print("Input  : ", end="")
    print_list(head)

    reversed_head = solution.reverse(head)

    print("Output : ", end="")
    print_list(reversed_head)


if __name__ == "__main__":
    main()
