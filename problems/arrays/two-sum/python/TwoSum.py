"""
===============================================================================
Problem: Two Sum
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given an array of integers 'nums' and an integer 'target',
return the indices of the two numbers such that they add up to
the target.

Assumptions

1. Exactly one valid solution exists.
2. The same element cannot be used twice.
3. The answer may be returned in any order.

Example

Input

nums   = [2, 7, 11, 15]
target = 9

Output

[0, 1]

Explanation

nums[0] + nums[1]

2 + 7 = 9

===============================================================================

Algorithm
---------

Use a dictionary (hash map).

The dictionary stores

Number -> Index

For every element

1. Compute the complement.

    complement = target - current_number

2. Check whether the complement already exists.

3. If yes

    return both indices

4. Otherwise

    store the current number.

===============================================================================

Dry Run
-------

nums = [2,7,11,15]

target = 9

Iteration 1

Current Number = 2

Complement = 7

Dictionary

{}

Not found

Store

2 -> 0

Dictionary

{2:0}

------------------------------------------------

Iteration 2

Current Number = 7

Complement = 2

Dictionary

{2:0}

Found!

Return

[0,1]

===============================================================================

Complexity Analysis
-------------------

Time Complexity

O(n)

Every element is visited only once.

Space Complexity

O(n)

The dictionary stores at most n elements.

===============================================================================
"""


class TwoSum:
    """
    Solution class implementing the Two Sum algorithm.

    The algorithm uses a dictionary to achieve
    linear time complexity.
    """

    def two_sum(self, nums: list[int], target: int) -> list[int]:
        """
        Finds two indices whose values add up to the target.

        Parameters
        ----------
        nums : list[int]

            Input array.

        target : int

            Desired sum.

        Returns
        -------
        list[int]

            List containing the two indices.

        Raises
        ------
        ValueError

            If no valid solution exists.
        """

        #
        # Dictionary Structure
        #
        # Key   -> Number
        # Value -> Index
        #
        number_to_index = {}

        #
        # Traverse the array once.
        #
        for index, current_number in enumerate(nums):

            #
            # Calculate the required complement.
            #
            complement = target - current_number

            #
            # Check if the complement
            # has already been seen.
            #
            if complement in number_to_index:

                return [
                    number_to_index[complement],
                    index
                ]

            #
            # Store current number.
            #
            number_to_index[current_number] = index

        #
        # Defensive programming.
        #
        raise ValueError("No valid solution exists.")


def main() -> None:
    """
    Demonstrates the Two Sum algorithm.
    """

    solution = Solution()

    numbers = [
        2,
        7,
        11,
        15
    ]

    target = 9

    result = solution.two_sum(
        numbers,
        target
    )

    print("=" * 60)
    print("Two Sum")
    print("=" * 60)

    print(f"Input Array : {numbers}")
    print(f"Target      : {target}")
    print(f"Indices     : {result}")

    print(
        f"Values      : "
        f"{numbers[result[0]]} + "
        f"{numbers[result[1]]} = "
        f"{target}"
    )


if __name__ == "__main__":
    main()