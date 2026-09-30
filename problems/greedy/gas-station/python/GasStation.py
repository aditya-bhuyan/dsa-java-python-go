"""
===============================================================================
Problem: Gas Station (LeetCode 134)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Given gas[] and cost[] arrays for n circular stations, return the starting
station index from which a full clockwise circuit can be completed, or -1.

Algorithm
---------
Greedy — single pass, running sum + reset.
diff[i] = gas[i] - cost[i]
Maintain total (overall sum) and current (tank from current start).
If current < 0 at index i: start = i+1, current = 0.
After loop: return start if total >= 0 else -1.

Dry Run
-------
gas=[1,2,3,4,5], cost=[3,4,5,1,2] → diff=[-2,-2,-2,3,3]

i=0: total=-2, current=-2 < 0 → start=1, current=0
i=1: total=-4, current=-2 < 0 → start=2, current=0
i=2: total=-6, current=-2 < 0 → start=3, current=0
i=3: total=-3, current=3
i=4: total=0,  current=6
total=0 >= 0 → return 3 ✓

Complexity
----------
Time:  O(n)
Space: O(1)
===============================================================================
"""


class GasStation:
    """
    Solution for Gas Station using greedy single-pass running sum.

    Time:  O(n)
    Space: O(1)
    """

    def can_complete_circuit(self, gas: list[int], cost: list[int]) -> int:
        """
        Return the starting station index for a complete clockwise circuit,
        or -1 if no valid starting point exists.

        Args:
            gas:  gas available at each station
            cost: gas cost to travel to the next station
        Returns:
            Starting station index, or -1.
        """
        total = current = start = 0
        for i, (g, c) in enumerate(zip(gas, cost)):
            diff = g - c
            total += diff
            current += diff
            if current < 0:
                start = i + 1
                current = 0
        return start if total >= 0 else -1


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = GasStation()

    test_cases = [
        ([1,2,3,4,5], [3,4,5,1,2], 3),
        ([2,3,4],     [3,4,3],     -1),
        ([5],         [4],          0),
        ([1],         [1],          0),
        ([3,1,1],     [1,2,2],      0),
    ]

    print("=" * 60)
    print("Gas Station")
    print("=" * 60)
    for i, (gas, cost, expected) in enumerate(test_cases, 1):
        result = solver.can_complete_circuit(gas, cost)
        print(f"Test {i}: gas={gas} cost={cost} → {result} "
              f"(expected {expected}) {'PASS' if result == expected else 'FAIL'}")


if __name__ == "__main__":
    main()
