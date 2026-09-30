"""
===============================================================================
Problem: Daily Temperatures
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given temperatures[], return answer[] where answer[i] = number of days
to wait after day i for a warmer temperature (0 if none).

Example

Input  : [73, 74, 75, 71, 69, 72, 76, 73]
Output : [1, 1, 4, 2, 1, 1, 0, 0]

===============================================================================

Algorithm — Monotonic Decreasing Stack
----------------------------------------

stack stores indices; temperatures at those indices are decreasing.

for i, temp in enumerate(temperatures):

    while stack and temperatures[i] > temperatures[stack[-1]]:
        prev_idx = stack.pop()
        answer[prev_idx] = i - prev_idx

    stack.append(i)

===============================================================================

Complexity
----------

Time : O(n) — each index pushed/popped at most once.
Space: O(n) — stack + answer list.

===============================================================================
"""


class DailyTemperatures:
    """
    Solution class using a monotonic decreasing stack.
    """

    def wait_days(self, temperatures: list[int]) -> list[int]:
        """
        Returns the number of days to wait for a warmer temperature.

        Parameters
        ----------
        temperatures : list[int]

            Daily temperature readings.

        Returns
        -------
        list[int]

            Wait days for each day (0 if no warmer day exists).
        """

        n = len(temperatures)
        answer = [0] * n
        stack: list[int] = []   # stores indices

        for i, temp in enumerate(temperatures):

            #
            # Pop all days that are colder than today.
            # Today is the "next warmer day" for them.
            #
            while stack and temp > temperatures[stack[-1]]:
                prev_idx = stack.pop()
                answer[prev_idx] = i - prev_idx

            stack.append(i)

        #
        # Remaining indices have no warmer future day.
        # answer[i] stays 0.
        #
        return answer


def main() -> None:
    """Demonstrates the Daily Temperatures algorithm."""

    solution = DailyTemperatures()

    temps = [73, 74, 75, 71, 69, 72, 76, 73]

    print("=" * 60)
    print("Daily Temperatures")
    print("=" * 60)
    print(f"Input    : {temps}")
    print(f"Output   : {solution.wait_days(temps)}")
    print(f"Expected : [1, 1, 4, 2, 1, 1, 0, 0]")


if __name__ == "__main__":
    main()
