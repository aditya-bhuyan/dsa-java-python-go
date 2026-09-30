"""
===============================================================================
Problem: Minimum Window Substring (LeetCode 76)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Return the minimum window substring of s such that every character in t
(including duplicates) is included in the window. Return "" if none exists.

Algorithm
---------
Sliding window + match counter.
need = freq(t), required = len(set(t))
Expand right: add s[right] to have; if have[c]==need[c], matches++
When matches==required: record min window, shrink left

Dry Run (abbreviated)
---------------------
s = "ADOBECODEBANC",  t = "ABC"
need = {'A':1,'B':1,'C':1}, required = 3

Expand to s[0..5]="ADOBEC" → matches=3
  best = "ADOBEC" (len=6)
  Shrink: remove 'A' → matches=2, left=1
Expand... include 'A' again at right=10 → matches=3
  Window "DOBECODEBA" (len=10) → not better
  Shrink: ... eventually left=9, window="BANC" (len=4) → best ✓

Complexity
----------
Time:  O(m + n)
Space: O(|Σ|)
===============================================================================
"""

from collections import Counter


class MinimumWindow:
    """
    Solution for Minimum Window Substring using sliding window + match counter.

    Time:  O(m + n)  m=len(s), n=len(t)
    Space: O(|Σ|)
    """

    def min_window(self, s: str, t: str) -> str:
        """
        Return the minimum window substring of s containing all chars of t.

        Args:
            s: source string
            t: target string
        Returns:
            Shortest window string, or "" if no such window exists.
        """
        if not s or not t:
            return ""

        need: dict[str, int] = Counter(t)
        required = len(need)
        have: dict[str, int] = {}
        matches = 0
        left = 0
        best_len = float("inf")
        best_left = 0

        for right, c in enumerate(s):
            have[c] = have.get(c, 0) + 1
            if c in need and have[c] == need[c]:
                matches += 1

            while matches == required:
                window_len = right - left + 1
                if window_len < best_len:
                    best_len = window_len
                    best_left = left
                lc = s[left]
                have[lc] -= 1
                if lc in need and have[lc] < need[lc]:
                    matches -= 1
                left += 1

        return "" if best_len == float("inf") else s[best_left: best_left + best_len]


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = MinimumWindow()

    test_cases = [
        ("ADOBECODEBANC", "ABC", "BANC"),
        ("a", "a", "a"),
        ("a", "aa", ""),
        ("aa", "aa", "aa"),
        ("ab", "b", "b"),
    ]

    print("=" * 60)
    print("Minimum Window Substring")
    print("=" * 60)
    for i, (s, t, expected) in enumerate(test_cases, 1):
        result = solver.min_window(s, t)
        print(f"Test {i}: s={repr(s)} t={repr(t)} → {repr(result)} "
              f"(expected {repr(expected)}) {'PASS' if result == expected else 'FAIL'}")


if __name__ == "__main__":
    main()
