"""
===============================================================================
Problem: Min Stack
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Design a stack with push, pop, top, and getMin — all in O(1).

===============================================================================

Approach — Auxiliary Min Stack
--------------------------------

Two lists:
  _main      — all pushed values.
  _min_stack — running minimum at each level.

push(val):
  _main.append(val)
  _min_stack.append( min(val, _min_stack[-1]) )   (or val if empty)

pop():
  _main.pop()
  _min_stack.pop()

top()     → _main[-1]
get_min() → _min_stack[-1]

===============================================================================

Complexity
----------

All operations: O(1).
Total space   : O(n).

===============================================================================
"""


class MinStack:
    """
    Stack with O(1) push, pop, top, and get_min.
    """

    def __init__(self) -> None:
        self._main: list[int] = []
        self._min_stack: list[int] = []

    def push(self, val: int) -> None:
        """Pushes val onto the stack."""
        self._main.append(val)
        if not self._min_stack or val <= self._min_stack[-1]:
            self._min_stack.append(val)
        else:
            self._min_stack.append(self._min_stack[-1])

    def pop(self) -> None:
        """Removes the top element."""
        self._main.pop()
        self._min_stack.pop()

    def top(self) -> int:
        """Returns the top element without removing it."""
        return self._main[-1]

    def get_min(self) -> int:
        """Returns the minimum element currently in the stack."""
        return self._min_stack[-1]


def main() -> None:
    """Demonstrates the MinStack."""

    ms = MinStack()
    ms.push(-2)
    ms.push(0)
    ms.push(-3)

    print("=" * 60)
    print("Min Stack")
    print("=" * 60)

    print("After push(-2), push(0), push(-3)")
    print(f"get_min() = {ms.get_min()}  (expected -3)")

    ms.pop()

    print("After pop()")
    print(f"top()     = {ms.top()}    (expected 0)")
    print(f"get_min() = {ms.get_min()}  (expected -2)")


if __name__ == "__main__":
    main()
