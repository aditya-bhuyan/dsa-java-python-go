"""
===============================================================================
Problem: Assign Cookies (LeetCode 455)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Given children's greed factors g[] and cookie sizes s[], assign each child
at most one cookie where s[j] >= g[i]. Maximise content children.

Algorithm
---------
Sort both g and s ascending.
Two pointers child and cookie:
  - If s[cookie] >= g[child]: child satisfied → child++
  - Always: cookie++
Return child (count of satisfied children).

Dry Run
-------
g=[1,2,3], s=[1,1,2]  (sorted)

cookie=0: s[0]=1 >= g[0]=1 → child=1, cookie=1
cookie=1: s[1]=1 < g[1]=2  → cookie=2
cookie=2: s[2]=2 >= g[1]=2 → child=2, cookie=3
return 2 ✓

Complexity
----------
Time:  O(n log n + m log m)
Space: O(1)
===============================================================================
"""


class AssignCookies:
    """
    Solution for Assign Cookies using greedy sort + two-pointer pairing.

    Time:  O(n log n + m log m)
    Space: O(1)
    """

    def find_content_children(self, g: list[int], s: list[int]) -> int:
        """
        Return the maximum number of content children.

        Args:
            g: greed factors of children (list is sorted in place)
            s: sizes of cookies (list is sorted in place)
        Returns:
            Count of children that can be satisfied.
        """
        g.sort()
        s.sort()
        child = cookie = 0
        while child < len(g) and cookie < len(s):
            if s[cookie] >= g[child]:
                child += 1
            cookie += 1
        return child


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = AssignCookies()

    test_cases = [
        ([1, 2, 3], [1, 1],    1),
        ([1, 2],    [1, 2, 3], 2),
        ([10,9,8,7],[5,6,7,8], 2),
        ([1, 2, 3], [],        0),
        ([],        [1, 2, 3], 0),
    ]

    print("=" * 60)
    print("Assign Cookies")
    print("=" * 60)
    for i, (g, s, expected) in enumerate(test_cases, 1):
        result = solver.find_content_children(list(g), list(s))
        print(f"Test {i}: g={g} s={s} → {result} "
              f"(expected {expected}) {'PASS' if result == expected else 'FAIL'}")


if __name__ == "__main__":
    main()
