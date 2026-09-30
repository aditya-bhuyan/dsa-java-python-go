# Daily Temperatures

> Difficulty: **Medium**
> Topic: **Stack, Monotonic Decreasing Stack**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Monotonic Decreasing Stack)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given an array of integers `temperatures` representing daily temperatures, return an array `answer` where `answer[i]` is the number of days you have to wait after the `i`-th day to get a warmer temperature.

If there is no future day with a warmer temperature, set `answer[i] = 0`.

---

# Examples

## Example 1

Input

```text
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
```

Output

```text
[1, 1, 4, 2, 1, 1, 0, 0]
```

Explanation

```
Day 0 (73°) → next warmer is day 1 (74°) → 1 day wait
Day 1 (74°) → next warmer is day 2 (75°) → 1 day wait
Day 2 (75°) → next warmer is day 6 (76°) → 4 days wait
Day 3 (71°) → next warmer is day 5 (72°) → 2 days wait
Day 4 (69°) → next warmer is day 5 (72°) → 1 day wait
Day 5 (72°) → next warmer is day 6 (76°) → 1 day wait
Day 6 (76°) → no warmer day ahead       → 0
Day 7 (73°) → no warmer day ahead       → 0
```

---

## Example 2

Input

```text
temperatures = [30, 40, 50, 60]
```

Output

```text
[1, 1, 1, 0]
```

---

## Example 3

Input

```text
temperatures = [30, 60, 90]
```

Output

```text
[1, 1, 0]
```

---

# Constraints

```
1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100
```

---

# Understanding the Problem

For each day `i`, we want the smallest `j > i` such that `temperatures[j] > temperatures[i]`. The answer for day `i` is `j - i`.

If no such `j` exists, the answer is `0`.

---

# Approach 1 — Brute Force

For each day, scan every future day until a warmer one is found.

```
for i in range(n):
    for j in range(i+1, n):
        if temperatures[j] > temperatures[i]:
            answer[i] = j - i
            break
```

Time: O(n²) — fails for large inputs.

---

## Complexity

Time

```
O(n²)
```

Space

```
O(1)
```

---

# Approach 2 — Monotonic Decreasing Stack (Optimal)

Use a **monotonic decreasing stack** of indices.

The stack always maintains indices whose temperatures are in **decreasing** order from bottom to top.

When we encounter a day that is **warmer** than the day at the top of the stack, that new day is the answer for the top day. We pop it and record `j - i`.

### Algorithm

```
stack = []    ← stores indices
answer = [0] * n

for i in range(n):

    while stack is not empty and temperatures[i] > temperatures[stack[-1]]:
        prev_index = stack.pop()
        answer[prev_index] = i - prev_index

    stack.append(i)

return answer
```

Remaining indices in the stack have no warmer future day → answer stays `0`.

---

# Dry Run

Input

```
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
Index           0   1   2   3   4   5   6   7
```

---

```
i=0  T=73  stack empty → push 0       stack: [0]
i=1  T=74  74 > T[0]=73 → pop 0, answer[0]=1-0=1
           stack empty → push 1       stack: [1]
i=2  T=75  75 > T[1]=74 → pop 1, answer[1]=2-1=1
           stack empty → push 2       stack: [2]
i=3  T=71  71 < T[2]=75 → push 3     stack: [2,3]
i=4  T=69  69 < T[3]=71 → push 4     stack: [2,3,4]
i=5  T=72  72 > T[4]=69 → pop 4, answer[4]=5-4=1
           72 > T[3]=71 → pop 3, answer[3]=5-3=2
           72 < T[2]=75 → push 5     stack: [2,5]
i=6  T=76  76 > T[5]=72 → pop 5, answer[5]=6-5=1
           76 > T[2]=75 → pop 2, answer[2]=6-2=4
           stack empty → push 6      stack: [6]
i=7  T=73  73 < T[6]=76 → push 7    stack: [6,7]

Remaining in stack (indices 6 and 7) → answer stays 0

answer = [1, 1, 4, 2, 1, 1, 0, 0]
```

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|--------|------------|
| Time | O(n²) |
| Space | O(1) |

---

## Monotonic Stack

| Metric | Complexity |
|--------|------------|
| Time | O(n) |
| Space | O(n) |

Each index is pushed once and popped at most once. Total operations: at most 2n.

---

# Edge Cases

## All Temperatures Decreasing

```
[90, 80, 70, 60]
```

Nothing gets popped. Stack ends with all indices. Answer: `[0, 0, 0, 0]`.

---

## All Temperatures Increasing

```
[60, 70, 80, 90]
```

Every push triggers pops of all previous indices. Answer: `[1, 1, 1, 0]`.

---

## Single Element

```
[42]
```

No future days. Answer: `[0]`.

---

# Interview Discussion

### Why store indices instead of values?

The answer requires `j - i` (the number of days), not the temperature value itself. Storing indices lets you compute this directly.

---

### Why does this work in O(n)?

Each element is pushed **once** and popped **at most once**. The total number of push + pop operations is at most `2n`, giving O(n) overall.

---

### What makes the stack "monotonic decreasing"?

We never let a larger temperature sit above a smaller one. Before pushing index `i`, we pop all indices with temperatures smaller than `temperatures[i]`. This maintains the decreasing invariant from bottom to top.

---

# Common Mistakes

## Storing Values Instead of Indices

You can't compute the day difference without the indices.

---

## Wrong Comparison Direction

```
Wrong:  temperatures[i] < temperatures[stack[-1]]   ← monotonic increasing (different problem)
Correct: temperatures[i] > temperatures[stack[-1]]  ← we want WARMER (greater)
```

---

## Not Initializing Answer Array to Zero

Indices left in the stack at the end have `answer[i] = 0`. If you initialize with another value, you need an extra cleanup step.

---

# Follow-up Questions

- Next Greater Element (same pattern, circular array variant).
- Largest Rectangle in Histogram (monotonic stack on widths).
- Trapping Rain Water (monotonic stack variant).

---

# Key Takeaways

After solving this problem, you should understand:

- The monotonic decreasing stack pattern: maintain a stack of "unanswered" candidates.
- Why popping the stack directly answers the question for the popped element.
- Why O(n) total work is achieved even though there is a nested while loop.
- How storing indices (not values) enables computing differences.

---

# Next Problem

➡ **Next Greater Element**

Applies the same monotonic decreasing stack to a lookup problem across two separate arrays.
