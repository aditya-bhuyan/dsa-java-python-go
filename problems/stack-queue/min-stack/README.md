# Min Stack

> Difficulty: **Medium**
> Topic: **Stack, Design**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Auxiliary Min Stack)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Design a stack that supports push, pop, top, and retrieving the **minimum element** in constant time.

Implement the `MinStack` class:

- `push(val)` — Pushes the element `val` onto the stack.
- `pop()` — Removes the element on the top of the stack.
- `top()` — Gets the top element of the stack.
- `getMin()` — Retrieves the minimum element in the stack.

Each function must run in **O(1)** time.

---

# Examples

## Example 1

```text
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   → -3
minStack.pop();
minStack.top();      → 0
minStack.getMin();   → -2
```

---

# Constraints

```
-2^31 <= val <= 2^31 - 1
pop, top, getMin will always be called on a non-empty stack.
At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
```

---

# Understanding the Problem

A normal stack gives you O(1) push, pop, and top.

The challenge is `getMin()` in O(1).

**Naive approach**: scan the whole stack to find the minimum → O(n). This fails the O(1) requirement.

**Key insight**: at every point in time, we need to know the minimum of the elements currently in the stack. When we pop an element, the minimum might change. We need to track this efficiently.

---

# Approach 1 — Brute Force (Scan for Minimum)

Store all elements in a regular stack. For `getMin()`, scan all elements.

```
push: O(1)
pop:  O(1)
top:  O(1)
getMin: O(n)  ← violates constraint
```

This doesn't meet the O(1) requirement.

---

# Approach 2 — Auxiliary Min Stack (Optimal)

Maintain **two stacks**:

1. **Main stack** — stores all pushed values.
2. **Min stack** — at every level, stores the minimum value of all elements currently in the main stack.

When you push a value:

- Push it to the main stack.
- Push `min(value, current_min)` to the min stack.

When you pop:

- Pop from both stacks simultaneously.

`getMin()` returns the top of the min stack in O(1).

### Algorithm

```
push(val):
    main.push(val)
    if minStack is empty:
        minStack.push(val)
    else:
        minStack.push( min(val, minStack.top()) )

pop():
    main.pop()
    minStack.pop()

top():
    return main.top()

getMin():
    return minStack.top()
```

---

# Dry Run

Operations:

```
push(-2)
push(0)
push(-3)
getMin()
pop()
top()
getMin()
```

---

Step by step:

```
push(-2)

Main stack   : [-2]
Min stack    : [-2]    min = -2


push(0)

Main stack   : [-2, 0]
Min stack    : [-2, -2]    min stays -2  (0 > -2)


push(-3)

Main stack   : [-2, 0, -3]
Min stack    : [-2, -2, -3]    min is now -3


getMin()   → top of minStack = -3  ✓


pop()      → removes -3 from both stacks

Main stack   : [-2, 0]
Min stack    : [-2, -2]


top()      → top of main stack = 0  ✓


getMin()   → top of minStack = -2  ✓
```

---

# Complexity Analysis

| Operation | Time | Space |
|-----------|------|-------|
| push | O(1) | O(1) per call, O(n) total |
| pop | O(1) | O(1) |
| top | O(1) | O(1) |
| getMin | O(1) | O(1) |

Total space: **O(n)** — the min stack mirrors the main stack.

---

# Edge Cases

## Pushing the Same Minimum Twice

```
push(1)
push(1)
pop()
getMin()   → must still return 1
```

The min stack must push the new minimum even when it equals the current min. Otherwise, after one pop, the min stack would have the wrong value.

Always push `min(val, current_min)` — not only when `val < current_min`.

---

## Single Element

```
push(5)
getMin()   → 5
top()      → 5
pop()
```

Works correctly — both stacks have exactly one element.

---

# Interview Discussion

### Why not just track a single `min` variable?

A single variable works until you pop the current minimum. At that point, you've lost track of the previous minimum — you'd have to scan the stack.

The min stack solves this by recording the running minimum at every level.

---

### Can you save space by only pushing to minStack when the value changes?

Yes — only push when `val <= minStack.top()`:

```
push(val):
    main.push(val)
    if minStack is empty or val <= minStack.top():
        minStack.push(val)

pop():
    if main.top() == minStack.top():
        minStack.pop()
    main.pop()
```

This uses less space when many consecutive pushes don't decrease the minimum. However, it requires comparing values on pop, which is slightly more complex.

The simpler approach (always push to min stack) is preferred in interviews for clarity.

---

### What if you need `getMax()` too?

Maintain a third auxiliary **max stack** using the same pattern.

---

# Common Mistakes

## Not Popping from Both Stacks

```
Wrong:

pop():
    main.pop()         ← min stack now out of sync

Correct:

pop():
    main.pop()
    minStack.pop()
```

---

## Only Pushing to minStack When Value is Strictly Less

```
Wrong:

if val < minStack.top():
    minStack.push(val)

Correct:

minStack.push( min(val, minStack.top()) )
```

With the wrong version, pushing duplicate minimums then popping one leaves the min stack short.

---

# Follow-up Questions

- Implement a MaxStack supporting `getMax()` in O(1).
- Implement a stack that supports `getMedian()` (requires two heaps).
- Design a queue with `getMin()` in O(1) (use two stacks).

---

# Key Takeaways

After solving this problem, you should understand:

- The auxiliary min stack pattern: mirror the main stack with running minimums.
- Why a single `min` variable is insufficient — it can't recover after a pop.
- The trade-off between the "always push" approach (simpler) and the "push only on decrease" approach (less space).
- How this design pattern extends to max stacks and other running aggregates.

---

# Next Problem

➡ **Daily Temperatures**

This introduces the **monotonic decreasing stack** to find the next warmer day for each element in a single O(n) pass.
