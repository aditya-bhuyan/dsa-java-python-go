"""
===============================================================================
Problem: Permutation in String (LeetCode 567)
===============================================================================

Author   : Aditya Bhuyan
Date     : 2026-07-29
Language : Python 3

Problem Statement
-----------------
Return True if s2 contains a permutation of s1.

Algorithm
---------
Fixed sliding window of size len(s1) over s2.
Use list[int] of size 26 for O(1) frequency operations (lowercase only).
Track matches = chars with have[c]==need[c].
Return True when matches==required.

Dry Run
-------
s1="ab", s2="eidbaooo"
need=[1,1,0,...] (indices for a,b), required=2, k=2

right=0 'e': have['e']=1, 'e' not in need
right=1 'i': have['i']=1; right(1)>=k(2)? No
right=2 'd': have['d']=1; remove s2[0]='e' (need['e']=0 → no match change)
right=3 'b': have['b']=1==need['b']=1 → matches=1; remove 'i' (need=0)
right=4 'a': have['a']=1==need['a']=1 → matches=2; remove 'd' (need=0)
  matches==required → return True ✓

Complexity
----------
Time:  O(m + n)
Space: O(1) — fixed 26-element arrays
===============================================================================
"""


class PermutationInString:
    """
    Solution for Permutation in String using fixed sliding window + match counter.

    Time:  O(m + n)  m=len(s2), n=len(s1)
    Space: O(1)  — fixed 26-char alphabet
    """

    def check_inclusion(self, s1: str, s2: str) -> bool:
        """
        Return True if any permutation of s1 is a substring of s2.

        Args:
            s1: pattern string (lowercase letters)
            s2: text string (lowercase letters)
        Returns:
            True if a permutation window is found.
        """
        if len(s1) > len(s2):
            return False

        need = [0] * 26
        have = [0] * 26

        for ch in s1:
            need[ord(ch) - ord('a')] += 1

        required = sum(1 for v in need if v > 0)
        matches = 0
        k = len(s1)

        for right, ch in enumerate(s2):
            c = ord(ch) - ord('a')
            have[c] += 1
            if have[c] == need[c]:
                matches += 1

            if right >= k:
                lc = ord(s2[right - k]) - ord('a')
                if have[lc] == need[lc]:
                    matches -= 1
                have[lc] -= 1

            if matches == required:
                return True

        return False


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = PermutationInString()

    test_cases = [
        ("ab", "eidbaooo", True),
        ("ab", "eidboaoo", False),
        ("a", "ab", True),
        ("abc", "bbbca", True),
        ("adc", "dcda", True),
    ]

    print("=" * 60)
    print("Permutation in String")
    print("=" * 60)
    for i, (s1, s2, expected) in enumerate(test_cases, 1):
        result = solver.check_inclusion(s1, s2)
        print(f"Test {i}: s1={repr(s1)} s2={repr(s2)} → {result} "
              f"(expected {expected}) {'PASS' if result == expected else 'FAIL'}")


if __name__ == "__main__":
    main()
