"""
===============================================================================
Problem: Longest Substring Without Repeating Characters (LeetCode 3)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Given a string s, find the length of the longest substring without
repeating characters.

Algorithm
---------
Variable sliding window with index map (char → last seen index).
For each s[right]:
  - If s[right] in seen AND seen[s[right]] >= left, jump left = seen+1.
  - seen[s[right]] = right.
  - best = max(best, right - left + 1).

Dry Run
-------
s = "abcabcbb"

right=0 'a': seen={},      left=0, best=1
right=1 'b': seen+,        left=0, best=2
right=2 'c': seen+,        left=0, best=3
right=3 'a': seen['a']=0 >= left=0 → left=1, seen['a']=3, best=3
right=4 'b': seen['b']=1 >= left=1 → left=2, seen['b']=4, best=3
right=5 'c': seen['c']=2 >= left=2 → left=3, seen['c']=5, best=3
right=6 'b': seen['b']=4 >= left=3 → left=5, seen['b']=6, best=3
right=7 'b': seen['b']=6 >= left=5 → left=7, seen['b']=7, best=3

Answer: 3

Complexity
----------
Time:  O(n)
Space: O(min(n, |Σ|))
===============================================================================
"""


class LongestSubstring:
    """
    Solution for Longest Substring Without Repeating Characters.

    Time:  O(n)
    Space: O(min(n, |Σ|))
    """

    def length_of_longest_substring(self, s: str) -> int:
        """
        Return the length of the longest substring without repeating characters.

        Args:
            s: input string (may contain any ASCII characters)
        Returns:
            Length of the longest valid substring.
        """
        seen: dict[str, int] = {}
        left = best = 0
        for right, ch in enumerate(s):
            if ch in seen and seen[ch] >= left:
                left = seen[ch] + 1
            seen[ch] = right
            if right - left + 1 > best:
                best = right - left + 1
        return best


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = LongestSubstring()

    test_cases = [
        ("abcabcbb", 3),
        ("bbbbb", 1),
        ("pwwkew", 3),
        ("", 0),
        ("a", 1),
        ("abcdefg", 7),
        (" ", 1),
    ]

    print("=" * 60)
    print("Longest Substring Without Repeating Characters")
    print("=" * 60)
    for i, (s, expected) in enumerate(test_cases, 1):
        result = solver.length_of_longest_substring(s)
        print(f"Test {i}: s={repr(s)} → {result} "
              f"(expected {expected}) {'PASS' if result == expected else 'FAIL'}")


if __name__ == "__main__":
    main()
