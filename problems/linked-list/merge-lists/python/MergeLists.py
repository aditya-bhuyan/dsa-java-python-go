"""
===============================================================================
Problem: Merge Two Sorted Lists
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given the heads of two sorted linked lists list1 and list2,
merge them into one sorted list by splicing together the existing nodes.
Return the head of the merged list.

Example

Input

list1 : 1 → 2 → 4 → None
list2 : 1 → 3 → 4 → None

Output

1 → 1 → 2 → 3 → 4 → 4 → None

===============================================================================

Algorithm
---------

Use a dummy head node and a current pointer.

While both lists have nodes:

    Compare front nodes.
    Attach the smaller (or equal) node to current.
    Advance that list's pointer.
    Advance current.

After the loop, attach whichever list still has remaining nodes.

Return dummy.next.

===============================================================================

Dry Run
-------

list1 : [1] → [2] → [4] → None
list2 : [1] → [3] → [4] → None

Initial: dummy → None, current = dummy

Step 1: 1 <= 1 → attach list1[1], list1=[2]
Step 2: 2 >  1 → attach list2[1], list2=[3]
Step 3: 2 <= 3 → attach list1[2], list1=[4]
Step 4: 4 >  3 → attach list2[3], list2=[4]
Step 5: 4 <= 4 → attach list1[4], list1=None

list1 exhausted → attach remaining list2[4]

Result: 1 → 1 → 2 → 3 → 4 → 4 → None

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(m + n)  — one pass through both lists.
Space Complexity: O(1)      — dummy node + current pointer only.

===============================================================================
"""

from __future__ import annotations
from typing import Optional


class ListNode:
    """Represents a node in a singly linked list."""

    def __init__(self, val: int = 0, next: Optional["ListNode"] = None) -> None:
        self.val = val
        self.next = next


class MergeLists:
    """
    Solution class implementing two-pointer iterative merge
    of two sorted linked lists.
    """

    def merge(
        self,
        list1: Optional[ListNode],
        list2: Optional[ListNode],
    ) -> Optional[ListNode]:
        """
        Merges two sorted linked lists into one sorted list.

        Parameters
        ----------
        list1 : Optional[ListNode]

            Head of the first sorted list.

        list2 : Optional[ListNode]

            Head of the second sorted list.

        Returns
        -------
        Optional[ListNode]

            Head of the merged sorted list.
        """

        #
        # Dummy head eliminates special handling for
        # initializing the merged list head.
        #
        dummy = ListNode(0)
        current = dummy

        #
        # Compare front nodes, attach the smaller one.
        #
        while list1 is not None and list2 is not None:

            if list1.val <= list2.val:
                current.next = list1
                list1 = list1.next
            else:
                current.next = list2
                list2 = list2.next

            current = current.next

        #
        # Attach remaining nodes.
        #
        current.next = list1 if list1 is not None else list2

        return dummy.next


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
    print(" → ".join(parts) + " → null" if parts else "null")


def main() -> None:
    """Demonstrates the Merge Two Sorted Lists algorithm."""

    solution = MergeLists()

    list1 = build_list([1, 2, 4])
    list2 = build_list([1, 3, 4])

    print("=" * 60)
    print("Merge Two Sorted Lists")
    print("=" * 60)

    print("list1  : ", end="")
    print_list(list1)

    print("list2  : ", end="")
    print_list(list2)

    merged = solution.merge(list1, list2)

    print("Merged : ", end="")
    print_list(merged)


if __name__ == "__main__":
    main()
