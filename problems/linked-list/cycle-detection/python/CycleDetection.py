"""
===============================================================================
Problem: Linked List Cycle Detection
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given the head of a linked list, return True if the list has a cycle,
False otherwise.

A cycle exists if some node can be reached again by following next pointers.

Example 1 — Cycle

3 → 2 → 0 → -4 → (points back to node 2)
Returns: True

Example 2 — No Cycle

1 → 2 → None
Returns: False

===============================================================================

Algorithm — Floyd's Tortoise and Hare
--------------------------------------

slow moves 1 step per iteration.
fast moves 2 steps per iteration.

If there is a cycle, fast will eventually lap slow (they will be
the same object — identity, not equality).

If there is no cycle, fast reaches None.

Loop:

    while fast is not None and fast.next is not None:
        slow = slow.next
        fast = fast.next.next

        if slow is fast:
            return True

    return False

===============================================================================

Dry Run — No Cycle
------------------

Input: [1] → [2] → [3] → None

Initial: slow=[1], fast=[1]

Step 1: slow=[2], fast=[3]   → not same
Step 2: fast.next = None → loop ends

Return False

---------------------------------------

Dry Run — Cycle
---------------

Input: [3] → [2] → [0] → [-4] → (back to [2])

Initial: slow=[3], fast=[3]

Step 1: slow=[2], fast=[0]   → not same
Step 2: slow=[0], fast=[2]   → not same   (fast: [-4] → [2])
Step 3: slow=[-4], fast=[-4] → same!

Return True

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n)
Space Complexity: O(1)

===============================================================================
"""

from __future__ import annotations
from typing import Optional


class ListNode:
    """Represents a node in a singly linked list."""

    def __init__(self, val: int = 0, next: Optional["ListNode"] = None) -> None:
        self.val = val
        self.next = next


class CycleDetection:
    """
    Solution class implementing Floyd's Tortoise and Hare
    algorithm for cycle detection.
    """

    def has_cycle(self, head: Optional[ListNode]) -> bool:
        """
        Returns True if the linked list contains a cycle.

        Parameters
        ----------
        head : Optional[ListNode]

            Head of the linked list (may be None).

        Returns
        -------
        bool

            True if a cycle exists, False otherwise.
        """

        slow = head
        fast = head

        while fast is not None and fast.next is not None:

            slow = slow.next
            fast = fast.next.next

            #
            # Identity check — same node object in memory.
            #
            if slow is fast:
                return True

        return False


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def build_list(values: list[int], cycle_index: int = -1) -> Optional[ListNode]:
    """
    Constructs a linked list from a list of integers.
    cycle_index >= 0 connects the tail to the node at that index (creates cycle).
    cycle_index < 0 creates a normal list with no cycle.
    """
    if not values:
        return None

    nodes = [ListNode(v) for v in values]

    for i in range(len(nodes) - 1):
        nodes[i].next = nodes[i + 1]

    if 0 <= cycle_index < len(nodes):
        nodes[-1].next = nodes[cycle_index]

    return nodes[0]


def main() -> None:
    """Demonstrates cycle detection."""

    solution = CycleDetection()

    print("=" * 60)
    print("Linked List Cycle Detection")
    print("=" * 60)

    # No cycle
    list1 = build_list([1, 2, 3, 4])
    print(f"No cycle   : has_cycle = {solution.has_cycle(list1)}")

    # Cycle: tail → index 1
    list2 = build_list([3, 2, 0, -4], cycle_index=1)
    print(f"With cycle : has_cycle = {solution.has_cycle(list2)}")


if __name__ == "__main__":
    main()
