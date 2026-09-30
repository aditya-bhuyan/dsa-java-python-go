# Stack

> Category: **Data Structure**
> Difficulty: **Foundational**

---

# Table of Contents

1. What is a Stack?
2. Core Operations
3. Internal Implementations
4. The Monotonic Stack
5. Complexity Summary
6. When to Use a Stack
7. Common Mistakes
8. Key Takeaways

---

# What is a Stack?

A **Stack** is a linear data structure that follows the **LIFO** principle:

```
LIFO — Last In, First Out
```

The last element pushed onto the stack is the first one to be removed.

Think of a stack of plates:

```
        ┌──────────┐
        │  Top →3  │  ← push here / pop from here
        ├──────────┤
        │    2     │
        ├──────────┤
        │  Bottom1 │
        └──────────┘
```

You can only interact with the **top** of the stack.

---

## Real-World Analogies

| Analogy | LIFO Behaviour |
|---------|----------------|
| Stack of plates | Last plate placed is first removed |
| Browser back button | Last visited page is first recalled |
| Undo in a text editor | Last action is first undone |
| Function call stack | Last function called is first to return |

---

# Core Operations

## Push

Add an element to the top of the stack.

```
Stack before: [1, 2, 3]
push(4)
Stack after : [1, 2, 3, 4]
```

Time: O(1)

---

## Pop

Remove and return the top element.

```
Stack before: [1, 2, 3, 4]
pop() → 4
Stack after : [1, 2, 3]
```

Time: O(1)

---

## Peek / Top

View the top element without removing it.

```
Stack: [1, 2, 3]
peek() → 3
Stack: [1, 2, 3]   (unchanged)
```

Time: O(1)

---

## isEmpty

Check whether the stack has any elements.

```
Stack: [] → true
Stack: [1] → false
```

Time: O(1)

---

## Size

Return the number of elements.

Time: O(1)

---

# Internal Implementations

A stack is an **abstract data structure**. It can be implemented using different underlying containers.

## Array / Dynamic Array (Most Common)

The top of the stack maps to the end of the array. Push appends; pop removes from the end.

```
Java   : Deque<Integer> stack = new ArrayDeque<>();
Python : list  (append / pop)
Go     : []int (append / slice)
```

---

## Linked List

Each push prepends a new node. Each pop removes the head.

Useful when the maximum size is unknown and you want to avoid resizing.

---

## Language Idioms

**Java**

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);
int top = stack.peek();
int val = stack.pop();
```

> Prefer `ArrayDeque` over `Stack` (legacy class). `ArrayDeque` is faster and not synchronized.

**Python**

```python
stack = []
stack.append(1)    # push
top = stack[-1]    # peek
val = stack.pop()  # pop
```

**Go**

```go
stack := []int{}
stack = append(stack, 1)                  // push
top := stack[len(stack)-1]               // peek
stack = stack[:len(stack)-1]             // pop
```

---

# The Monotonic Stack

A **Monotonic Stack** is a stack that is maintained in a specific order — either always increasing or always decreasing from bottom to top.

It is one of the most useful patterns in competitive programming and interviews.

---

## Monotonic Increasing Stack

Elements are in non-decreasing order from bottom to top.

```
Bottom → [1, 3, 5, 8] ← Top
```

When you push a new element, you pop everything from the top that is **greater** than the new element first.

---

## Monotonic Decreasing Stack

Elements are in non-increasing order from bottom to top.

```
Bottom → [8, 5, 3, 1] ← Top
```

When you push a new element, you pop everything from the top that is **smaller** than the new element first.

---

## Why is This Useful?

A monotonic stack helps answer questions like:

- **Next Greater Element** — For each element, find the next element to the right that is larger.
- **Daily Temperatures** — For each day, find how many days until a warmer temperature.
- **Largest Rectangle in Histogram** — Find the maximum area rectangle.

The key insight: when an element is popped because a new element is larger (or smaller), that popping event **directly answers** the question for the popped element.

---

## Algorithm Template — Next Greater Element

```
stack = []     ← stores indices
result = [-1] * n

for i in range(n):

    while stack is not empty and nums[stack[-1]] < nums[i]:
        idx = stack.pop()
        result[idx] = nums[i]    ← nums[i] is the next greater for idx

    stack.append(i)

return result
```

---

## Step-by-Step Example

```
nums = [2, 1, 5, 6, 2, 3]

Index:  0  1  2  3  4  5
```

We want: for each element, what is the next element to its right that is greater?

```
i=0: push 0.  stack=[0]
i=1: 1 < 2 → push 1.   stack=[0,1]
i=2: 5 > 1 → pop 1 → result[1]=5
     5 > 2 → pop 0 → result[0]=5
     push 2.  stack=[2]
i=3: 6 > 5 → pop 2 → result[2]=6
     push 3.  stack=[3]
i=4: 2 < 6 → push 4.  stack=[3,4]
i=5: 3 > 2 → pop 4 → result[4]=3
     3 < 6 → push 5.  stack=[3,5]

Remaining in stack → no next greater → result stays -1

result = [5, 5, 6, -1, 3, -1]
```

---

## Complexity of Monotonic Stack Algorithms

| Metric | Complexity |
|--------|------------|
| Time | O(n) — each element is pushed and popped at most once |
| Space | O(n) — stack holds at most n elements |

---

# Complexity Summary

| Operation | Time | Space |
|-----------|------|-------|
| Push | O(1) | O(1) |
| Pop | O(1) | O(1) |
| Peek | O(1) | O(1) |
| isEmpty | O(1) | O(1) |
| Monotonic stack pass | O(n) total | O(n) |

---

# When to Use a Stack

Use a stack when:

- You need to **track history** and reverse it (undo, browser back).
- You need **balanced matching** (parentheses, brackets, HTML tags).
- You need to find the **next greater / smaller** element.
- You are evaluating expressions or parsing (infix → postfix, calculators).
- You need to simulate **recursion iteratively**.
- You need to process nodes in **DFS order** without recursion.

---

# Common Mistakes

## Popping an Empty Stack

Always check `isEmpty()` (Java), `len(stack) > 0` (Python), or `len(stack) > 0` (Go) before calling `pop` or `peek`.

```
Wrong:

val = stack.pop()   ← crashes if stack is empty

Correct:

if stack:
    val = stack.pop()
```

---

## Using `Stack` Instead of `ArrayDeque` in Java

`java.util.Stack` extends `Vector` and is synchronized, which adds unnecessary overhead. Always use:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

## Confusing Stack Direction

The **top** is the last element pushed. In a Python list, that is `stack[-1]`. In Go, that is `stack[len(stack)-1]`.

---

## Off-by-One in Monotonic Stack

When using a monotonic stack to find the **next greater element**, store **indices** in the stack, not values. Values are read from the array using those indices.

---

# Key Takeaways

After studying this concept, you should understand:

- The LIFO principle and its four core operations.
- How to implement a stack using arrays or linked lists in Java, Python, and Go.
- The monotonic stack pattern and why each element is processed at most twice (O(n) total).
- When to apply a stack: parentheses matching, next greater element, expression evaluation, DFS.

These ideas appear directly in problems such as Valid Parentheses, Min Stack, Daily Temperatures, and Next Greater Element — all covered in the problems section.

---

# Problems Covered

| Problem | Stack Pattern |
|---------|--------------|
| Valid Parentheses | Matching brackets with a plain stack |
| Min Stack | Augmented stack tracking running minimum |
| Daily Temperatures | Monotonic decreasing stack |
| Next Greater Element | Monotonic decreasing stack |
