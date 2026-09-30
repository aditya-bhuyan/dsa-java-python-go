"""
===============================================================================
Problem: Next Greater Element
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given nums1 (a subset of nums2), for each element in nums1 return the
first greater element to its right in nums2, or -1 if none exists.

Example

nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]
Output: [-1, 3, -1]

===============================================================================

Algorithm — Monotonic Stack + Hash Map
----------------------------------------

Phase 1: process nums2 with a monotonic decreasing stack (store values).
         When num > stack[-1], pop and record next_greater[popped] = num.

Phase 2: look up each nums1 value in the map.

===============================================================================

Dry Run
-------

nums2 = [1, 3, 4, 2]

num=1  push 1              stack:[1]
num=3  3>1 → map[1]=3      stack:[]  push 3   stack:[3]
num=4  4>3 → map[3]=4      stack:[]  push 4   stack:[4]
num=2  2<4 → push 2        stack:[4,2]

stack remaining: map[4]=-1, map[2]=-1

map = {1:3, 3:4, 4:-1, 2:-1}

answer for [4,1,2] = [-1, 3, -1]

===============================================================================

Complexity
----------

Time : O(m + n)
Space: O(n)

===============================================================================
"""


class NextGreaterElement:
    """
    Solution using a monotonic decreasing stack and a hash map.
    """

    def find_next_greater(self, nums1: list[int], nums2: list[int]) -> list[int]:
        """
        Returns the next greater element in nums2 for each value in nums1.

        Parameters
        ----------
        nums1 : list[int]

            Query values (subset of nums2).

        nums2 : list[int]

            Reference array.

        Returns
        -------
        list[int]

            Next greater element for each nums1[i], or -1 if none.
        """

        next_greater: dict[int, int] = {}
        stack: list[int] = []   # stores values

        # Phase 1: build the map from nums2.
        for num in nums2:

            while stack and num > stack[-1]:
                popped = stack.pop()
                next_greater[popped] = num

            stack.append(num)

        # Remaining have no next greater.
        for num in stack:
            next_greater[num] = -1

        # Phase 2: answer queries.
        return [next_greater[x] for x in nums1]


def main() -> None:
    """Demonstrates the Next Greater Element algorithm."""

    solution = NextGreaterElement()

    print("=" * 60)
    print("Next Greater Element")
    print("=" * 60)

    nums1 = [4, 1, 2]
    nums2 = [1, 3, 4, 2]
    print(f"nums1    : {nums1}")
    print(f"nums2    : {nums2}")
    print(f"Output   : {solution.find_next_greater(nums1, nums2)}")
    print(f"Expected : [-1, 3, -1]")

    print()

    nums1b = [2, 4]
    nums2b = [1, 2, 3, 4]
    print(f"nums1    : {nums1b}")
    print(f"nums2    : {nums2b}")
    print(f"Output   : {solution.find_next_greater(nums1b, nums2b)}")
    print(f"Expected : [3, -1]")


if __name__ == "__main__":
    main()
