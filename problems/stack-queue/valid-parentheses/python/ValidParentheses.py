"""
===============================================================================
Problem: Valid Parentheses
===============================================================================

Author : Aditya Bhuyan
Language : Python 3.x

Problem Statement
-----------------
Given a string s containing only '(', ')', '{', '}', '[', ']',
determine if the string is valid.

Valid means:
  1. Every open bracket is closed by the same type of bracket.
  2. Open brackets are closed in the correct order.
  3. Every close bracket has a corresponding open bracket.

Examples

"()"      → True
"()[]{}"  → True
"(]"      → False
"([)]"    → False
"{[]}"    → True

===============================================================================

Algorithm
---------

Stack-based single pass.

  - Open bracket  ( [ {  → push.
  - Close bracket ) ] }  →
        if stack is empty  → False
        pop the top
        if top doesn't match → False

After the loop, return True only if stack is empty.

Matching map:
  ')' → '('
  ']' → '['
  '}' → '{'

===============================================================================

Dry Run
-------

Input: "{[()]}"

{ → push    stack: [ '{' ]
[ → push    stack: [ '{', '[' ]
( → push    stack: [ '{', '[', '(' ]
) → top='(' match! pop   stack: [ '{', '[' ]
] → top='[' match! pop   stack: [ '{' ]
} → top='{' match! pop   stack: []

Empty → True

---------------------------------------

Input: "([)]"

( → push    stack: [ '(' ]
[ → push    stack: [ '(', '[' ]
) → top='[' NO match → False

===============================================================================

Complexity Analysis
-------------------

Time Complexity : O(n)
Space Complexity: O(n)

===============================================================================
"""


class ValidParentheses:
    """
    Solution class implementing stack-based bracket validation.
    """

    MATCHING: dict[str, str] = {
        ')': '(',
        ']': '[',
        '}': '{',
    }

    def is_valid(self, s: str) -> bool:
        """
        Returns True if the bracket string is valid.

        Parameters
        ----------
        s : str

            Input string containing only bracket characters.

        Returns
        -------
        bool

            True if valid, False otherwise.
        """

        stack: list[str] = []

        for ch in s:

            if ch in ('(', '[', '{'):
                #
                # Open bracket — push.
                #
                stack.append(ch)

            else:
                #
                # Close bracket — check top matches.
                #
                if not stack:
                    return False

                top = stack.pop()

                if top != self.MATCHING[ch]:
                    return False

        return len(stack) == 0


def main() -> None:
    """Demonstrates the Valid Parentheses algorithm."""

    solution = ValidParentheses()

    cases = [
        ("()",      True),
        ("()[]{}", True),
        ("(]",     False),
        ("([)]",   False),
        ("{[]}",   True),
        ("",       True),
    ]

    print("=" * 60)
    print("Valid Parentheses")
    print("=" * 60)

    for s, expected in cases:
        result = solution.is_valid(s)
        status = "✓" if result == expected else "✗"
        print(f"{status}  input={s!r:<14}  result={result}")


if __name__ == "__main__":
    main()
