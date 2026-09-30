# Recursion

> Category: **Programming Technique**
> Difficulty: **Foundational**

---

# Table of Contents

1. What is Recursion?
2. Anatomy of a Recursive Function
3. The Call Stack
4. Recursion Patterns
5. Tail Recursion
6. Recursion vs Iteration
7. Complexity Analysis
8. Common Mistakes
9. Key Takeaways

---

# What is Recursion?

**Recursion** is a technique where a function calls **itself** to solve a smaller version of the same problem, until it reaches a condition simple enough to answer directly.

```
solve(problem):
    if problem is trivial:
        return base answer          ← base case
    smaller = reduce(problem)
    return combine( solve(smaller) )   ← recursive case
```

Every recursive solution has exactly two parts:

- **Base case** — the condition that stops the recursion.
- **Recursive case** — the call that reduces the problem toward the base case.

---

## Classic Example — Factorial

```
factorial(n):
    if n == 0:          ← base case
        return 1
    return n * factorial(n - 1)   ← recursive case
```

Expansion for `factorial(4)`:

```
factorial(4)
= 4 * factorial(3)
= 4 * 3 * factorial(2)
= 4 * 3 * 2 * factorial(1)
= 4 * 3 * 2 * 1 * factorial(0)
= 4 * 3 * 2 * 1 * 1
= 24
```

---

# Anatomy of a Recursive Function

Every well-formed recursive function has three responsibilities:

| Responsibility | Description |
|----------------|-------------|
| **Base case** | Return a known answer when no further recursion is needed |
| **Reduction** | Make the problem strictly smaller each call |
| **Combination** | Use the result of the smaller problem to build the full answer |

If any of these is missing:

- Missing base case → infinite recursion → stack overflow.
- Missing reduction → infinite recursion (same input every time).
- Missing combination → result is discarded.

---

# The Call Stack

Every function call occupies a **stack frame** in memory. Recursive calls push frames; returns pop them.

```
factorial(3)

Call Stack:

┌─────────────────┐
│ factorial(0) = 1│  ← top (most recent)
├─────────────────┤
│ factorial(1)    │
├─────────────────┤
│ factorial(2)    │
├─────────────────┤
│ factorial(3)    │  ← bottom (first call)
└─────────────────┘
```

When `factorial(0)` returns `1`, its frame is popped. `factorial(1)` receives `1`, computes `1 * 1 = 1`, and pops. This continues until `factorial(3)` receives `2` and returns `3 * 2 = 6`.

**Stack depth** = the number of nested recursive calls active at once. This equals the recursion depth, and directly determines O(n) call-stack space usage.

---

# Recursion Patterns

## Pattern 1 — Linear Recursion

One recursive call per invocation. The call chain is a straight line.

```
sum([1,2,3,4,5]):
    = 1 + sum([2,3,4,5])
    = 1 + 2 + sum([3,4,5])
    ...
```

Space: O(n) call stack.

---

## Pattern 2 — Binary / Tree Recursion

Two recursive calls per invocation. The call tree branches.

```
fibonacci(n):
    if n <= 1: return n
    return fibonacci(n-1) + fibonacci(n-2)
```

Call tree for `fibonacci(5)`:

```
         fib(5)
        /      \
    fib(4)    fib(3)
    /    \    /    \
 fib(3) fib(2) fib(2) fib(1)
  ...
```

Without memoization this results in exponential O(2ⁿ) calls. With memoization it becomes O(n).

---

## Pattern 3 — Divide and Conquer

Split the problem into two roughly equal halves, recurse on each, then merge.

```
mergeSort(arr):
    if len(arr) <= 1: return arr
    mid  = len(arr) // 2
    left  = mergeSort(arr[:mid])
    right = mergeSort(arr[mid:])
    return merge(left, right)
```

Time: O(n log n). Space: O(log n) call stack depth.

---

## Pattern 4 — Tree Traversal Recursion

The most common recursion pattern in interviews. Process a node, then recurse on children.

```
dfs(node):
    if node is null: return
    process(node)
    dfs(node.left)
    dfs(node.right)
```

The recursive structure mirrors the tree structure exactly.

---

## Pattern 5 — Backtracking

Explore choices recursively, undo a choice (backtrack) if it leads to a dead end.

```
solve(state):
    if goal reached: record solution
    for each choice:
        apply choice
        solve(state)
        undo choice
```

Used in: permutations, combinations, N-Queens, Sudoku.

---

# Tail Recursion

A function is **tail recursive** if the recursive call is the **last action** in the function — there is nothing left to compute after it returns.

```
# Not tail recursive — multiplication happens after the return
factorial(n):
    return n * factorial(n - 1)

# Tail recursive — accumulator carries the result
factorial(n, acc=1):
    if n == 0: return acc
    return factorial(n - 1, n * acc)
```

Tail-recursive functions can be optimized by the compiler (Tail Call Optimization, TCO) to reuse the same stack frame, giving O(1) space. **Python and Java do not perform TCO**; Go and many functional languages do.

---

# Recursion vs Iteration

| Aspect | Recursion | Iteration |
|--------|-----------|-----------|
| Code clarity | High (mirrors problem structure) | Can be verbose |
| Stack usage | O(depth) call stack | O(1) typically |
| Stack overflow risk | Yes, for deep inputs | No |
| Performance overhead | Function call cost | Minimal |
| Best for | Trees, graphs, divide-and-conquer | Arrays, simple loops |

**Rule of thumb**: if the problem has a naturally recursive structure (trees, graphs, subproblems), use recursion. Convert to iteration with an explicit stack if stack depth is a concern.

---

# Complexity Analysis

## Time Complexity

Count the total number of recursive calls and the work per call.

| Pattern | Time |
|---------|------|
| Linear recursion (factorial) | O(n) |
| Binary recursion without memo (fibonacci) | O(2ⁿ) |
| Binary recursion with memo | O(n) |
| Divide and conquer (merge sort) | O(n log n) |
| Tree traversal | O(n) — every node visited once |

---

## Space Complexity

Dominated by the call stack depth.

| Pattern | Space |
|---------|-------|
| Linear recursion | O(n) |
| Balanced tree traversal | O(log n) |
| Skewed tree traversal | O(n) |
| Divide and conquer | O(log n) |

---

## The Recurrence Relation

For divide-and-conquer algorithms, use the **Master Theorem**:

```
T(n) = a · T(n/b) + f(n)
```

Where:
- `a` = number of subproblems
- `n/b` = size of each subproblem
- `f(n)` = work done outside recursive calls

Example (merge sort): `T(n) = 2·T(n/2) + O(n)` → O(n log n)

---

# Common Mistakes

## Missing Base Case

```
def count_down(n):
    print(n)
    count_down(n - 1)    ← no base case → infinite recursion → RecursionError
```

Always define what happens when the input is 0, null, empty, or the trivial case.

---

## Not Reducing the Problem

```
def bad(n):
    return bad(n)    ← same input each time → infinite recursion
```

The recursive call must receive a **strictly smaller** input.

---

## Ignoring the Return Value

```
def find(node, target):
    if node is None: return False
    if node.val == target: return True
    find(node.left, target)    ← return value discarded!
    find(node.right, target)
```

Correct:

```
    return find(node.left, target) or find(node.right, target)
```

---

## Stack Overflow on Deep Inputs

Python's default recursion limit is 1000. Java's stack size depends on JVM settings. For input sizes that could exceed a few thousand levels of nesting, consider an iterative approach with an explicit stack.

---

## Repeated Subproblems Without Memoization

Fibonacci without memoization recomputes `fib(2)` exponentially many times. Cache results (top-down DP) or use bottom-up iteration.

---

# Key Takeaways

After studying this concept, you should understand:

- Every recursive function needs a base case and a reduction step.
- The call stack stores one frame per active recursive call — depth determines space usage.
- Linear, binary, divide-and-conquer, tree traversal, and backtracking are the five major recursion patterns.
- Tree problems are the most common application of recursion in interviews — the recursive structure maps directly onto the tree structure.
- Tail recursion is a space optimization not supported in Python or Java.
- When depth may be large, convert to iteration with an explicit stack.

---

# Problems Covered

Recursion appears in:

| Problem | Recursion Pattern |
|---------|------------------|
| Maximum Depth of Binary Tree | Post-order combination |
| Inorder Traversal | Left → Root → Right |
| Same Tree | Pairwise structural comparison |
| Symmetric Tree | Mirror comparison |
| Diameter of Binary Tree | Post-order with shared state |
