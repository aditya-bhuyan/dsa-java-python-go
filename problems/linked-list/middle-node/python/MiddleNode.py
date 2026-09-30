"""
===============================================================================
Problem: Middle of the Linked List
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given the head of a singly linked list, return the middle node.
If there are two middle nodes, return the second middle node.

Example 1

Input  : [1] → [2] → [3] → [4] → [5] → None
Output : Node with value 3

Example 2

Input  : [1] → [2] → [3] → [4] → [5] → [6] → None
Output : Node with value 4  (second middle)

===============================================================================

Algorithm
---------

Fast and Slow Pointer.

slow moves 1 step per iteration.
fast moves 2 steps per iteration.

Loop condition:

    while fast is not None and fast.next is not None:

When the loop ends, slow is the middle node.

===============================================================================

Dry Run (odd length)
--------------------

Input: [1] → [2] → [3] → [4] → [5] → None

Initial: slow=[1], fast=[1]

Step 1: slow=[2], fast=[3]
Step 2: slow=[3], fast=[5]

fast.next = None → loop stops

Return slow = [3]

--------------------------------------

Dry Run (even length)
---------------------

Input: [1] → [2] → [3] → [4] → [5] → [6] → None

Initial: slow=[1], fast=[1]

Step 1: slow=[2], fast=[3]
Step 2: slow=[3], fast=[5]
Step 3: slow=[4], fast=None  (moved to [5].next.next = None)

Loop stops

Return slow = [4]

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n)  — single pass.
Space Complexity: O(1)  — two pointer variables.

===============================================================================
"""

from __future__ import annotations
from typing import Optional


class ListNode:
    """Represents a node in a singly linked list."""

    def __init__(self, val: int = 0, next: Optional["ListNode"] = None) -> None:
        self.val = val
        self.next = next


class MiddleNode:
    """
    Solution class implementing the fast and slow pointer
    approach to find the middle of a linked list.
    """

    def find_middle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        """
        Returns the middle node of the linked list.
        For even-length lists, returns the second middle node.

        Parameters
        ----------
        head : Optional[ListNode]

            The head of the list.

        Returns
        -------
        Optional[ListNode]

            The middle node.
        """

        slow = head
        fast = head

        while fast is not None and fast.next is not None:
            slow = slow.next
            fast = fast.next.next

        return slow


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


def print_list(head: Optional[ListNode]) -> None:
    """Prints the linked list."""
    parts = []
    while head:
        parts.append(str(head.val))
        head = head.next
    print(" → ".join(parts) + " → null")


def main() -> None:
    """Demonstrates the Middle Node algorithm."""

    solution = MiddleNode()

    print("=" * 60)
    print("Middle of the Linked List")
    print("=" * 60)

    # Odd-length list
    list1 = build_list([1, 2, 3, 4, 5])
    print("Input  : ", end="")
    print_list(list1)
    print(f"Middle : {solution.find_middle(list1).val}")

    print()

    # Even-length list
    list2 = build_list([1, 2, 3, 4, 5, 6])
    print("Input  : ", end="")
    print_list(list2)
    print(f"Middle : {solution.find_middle(list2).val}")


if __name__ == "__main__":
    main()
