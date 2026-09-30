# Valid Parentheses

> Difficulty: **Easy**
> Topic: **Stack, String Matching**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Stack)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given a string `s` containing only the characters `(`, `)`, `{`, `}`, `[`, `]`, determine if the input string is **valid**.

A string is valid if:

1. Every open bracket is closed by the **same type** of bracket.
2. Open brackets are closed in the **correct order**.
3. Every close bracket has a corresponding open bracket of the same type.

---

# Examples

## Example 1

Input

```text
s = "()"
```

Output

```text
true
```

---

## Example 2

Input

```text
s = "()[]{}"
```

Output

```text
true
```

---

## Example 3

Input

```text
s = "(]"
```

Output

```text
false
```

---

## Example 4

Input

```text
s = "([)]"
```

Output

```text
false
```

Explanation

```
The brackets are not closed in the correct order.
( must be closed before [ is closed.
```

---

## Example 5

Input

```text
s = "{[]}"
```

Output

```text
true
```

---

# Constraints

```
1 <= s.length <= 10^4
s consists only of '(', ')', '{', '}', '[', ']'
```

---

# Understanding the Problem

Every open bracket must be matched with the correct close bracket, and in the correct nesting order.

The key insight: the most recently opened bracket must be the first one closed.

This is exactly the **LIFO** behaviour of a stack.

```
Input: { [ ( ) ] }

Push {  → stack: [  {  ]
Push [  → stack: [  {  [  ]
Push (  → stack: [  {  [  (  ]
See )   → top is ( → match! pop  → stack: [  {  [  ]
See ]   → top is [ → match! pop  → stack: [  {  ]
See }   → top is { → match! pop  → stack: []

Stack empty → valid
```

---

# Approach 1 — Brute Force (Repeated Replacement)

Repeatedly remove matched pairs `()`, `[]`, `{}` from the string until nothing changes or the string is empty.

```
"({[]})"
→ remove [] → "({  })"  → "({  })"
→ remove {} → "(      )"  
→ remove () → ""
→ empty → valid
```

### Disadvantages

- O(n²) — each scan is O(n) and there are up to n/2 scans.
- Complex implementation.

---

## Complexity

Time

```
O(n²)
```

Space

```
O(n)
```

---

# Approach 2 — Stack (Optimal)

For every character in the string:

- If it is an **open bracket** (`(`, `[`, `{`) → push it onto the stack.
- If it is a **close bracket** (`)`, `]`, `}`) → check the top of the stack:
  - If the top matches the corresponding open bracket → pop.
  - Otherwise → the string is invalid.

After processing all characters, the stack must be **empty** for the string to be valid.

### Algorithm

```
stack = []

for each char in s:

    if char is open bracket:
        push char

    else:
        if stack is empty:
            return false

        top = stack.pop()

        if top does not match char:
            return false

return stack is empty
```

---

# Dry Run

## Valid Input: `{[()]}`

```
char = {  → open → push    stack: [ { ]
char = [  → open → push    stack: [ { [ ]
char = (  → open → push    stack: [ { [ ( ]
char = )  → close → top=(  → match! pop    stack: [ { [ ]
char = ]  → close → top=[  → match! pop    stack: [ { ]
char = }  → close → top={  → match! pop    stack: []

Stack empty → return true
```

---

## Invalid Input: `([)]`

```
char = (  → open → push    stack: [ ( ]
char = [  → open → push    stack: [ ( [ ]
char = )  → close → top=[  → NO match ( [ vs ) → return false
```

---

## Invalid Input: `(`

```
char = (  → open → push    stack: [ ( ]

End of string.
Stack not empty → return false
```

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|--------|------------|
| Time | O(n²) |
| Space | O(n) |

---

## Stack

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(n) |

Each character is processed exactly once. The stack holds at most n/2 open brackets.

---

# Edge Cases

## Empty String

```
s = ""
```

Stack is empty from the start → return `true`.

---

## Only Close Brackets

```
s = "])}"
```

Stack is empty on first close bracket → return `false`.

---

## Only Open Brackets

```
s = "((("
```

Stack contains 3 elements after traversal → return `false`.

---

## Odd Length String

Any odd-length string is immediately invalid — you cannot have balanced pairs.

```
s = "([)"
```

Odd length → return `false` early.

---

# Interview Discussion

### Why a stack and not a counter?

A simple counter works for a **single** bracket type (e.g., only parentheses). With multiple bracket types, you need to know **which specific bracket** is at the top — a counter loses that information.

---

### How does the matching work?

Use a hash map:

```
) → (
] → [
} → {
```

When you encounter a close bracket, look up its expected open bracket and compare it with the top of the stack.

---

### Can you solve this without a stack?

For a single bracket type, yes — use a counter that increments on `(` and decrements on `)`, checking it never goes negative.

For multiple bracket types, no — you need the stack to remember the ordering of nested brackets.

---

# Common Mistakes

## Not Checking for Empty Stack Before Popping

```
Wrong:

top = stack.pop()    ← crashes if stack is empty

Correct:

if not stack:
    return false
top = stack.pop()
```

---

## Returning True Without Checking Stack is Empty

```
Wrong:

for char in s:
    ...

return true    ← may have unclosed brackets in stack

Correct:

return len(stack) == 0
```

---

## Comparing Characters Incorrectly

Use a match map to compare open/close pairs. Direct character comparison errors are common when extending to new bracket types.

---

# Follow-up Questions

- Minimum number of bracket removals to make a string valid.
- Generate all valid combinations of n pairs of parentheses.
- Longest valid parentheses substring.
- Check if a string with wildcard `*` (can be `(`, `)`, or empty) is valid.

---

# Key Takeaways

After solving this problem, you should understand:

- Why LIFO stack behaviour maps naturally to bracket nesting.
- The three-step pattern: push on open, check-and-pop on close, verify empty at end.
- How to use a hash map for O(1) bracket-type lookup.
- Why an empty-stack check before popping is essential.

---

# Next Problem

➡ **Min Stack**

This introduces an **augmented stack** that supports `getMin()` in O(1) by maintaining additional state alongside the main stack.
