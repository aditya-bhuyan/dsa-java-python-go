# Greedy Algorithms

## Table of Contents

1. [Introduction](#introduction)
2. [The Greedy Choice Property](#the-greedy-choice-property)
3. [Optimal Substructure](#optimal-substructure)
4. [When Greedy Works — and When It Doesn't](#when-greedy-works--and-when-it-doesnt)
5. [Classic Greedy Patterns](#classic-greedy-patterns)
6. [Complexity Analysis](#complexity-analysis)
7. [Language Implementations](#language-implementations)
8. [Common Mistakes](#common-mistakes)
9. [Problems Covered](#problems-covered)

---

## Introduction

A **greedy algorithm** builds a solution piece by piece, always choosing the option that looks **best at the current moment** without reconsidering past choices.

The key contrast with other paradigms:
- **Brute Force** — tries all combinations.
- **Dynamic Programming** — solves overlapping subproblems, stores results.
- **Greedy** — makes a locally optimal choice at each step and never backtracks.

When greedy works, it is usually the **simplest and most efficient** solution — often O(n) or O(n log n).

---

## The Greedy Choice Property

A problem has the **greedy choice property** if a globally optimal solution can be reached by always making the locally optimal (greedy) choice.

**Formal statement:** At every decision point, there exists an optimal solution that includes the greedy choice. Therefore, we can always safely commit to the greedy choice without losing the global optimum.

### Example — Coin Change (specific denominations)
With coins `{1, 5, 10, 25}` and target `41¢`:
```
Greedy: pick largest coin ≤ remaining
  25¢ → remaining = 16
  10¢ → remaining = 6
   5¢ → remaining = 1
   1¢ → remaining = 0
Result: 4 coins ✓ (optimal for standard US coins)
```
*Note: greedy fails for arbitrary coin denominations — e.g., `{1, 3, 4}` target `6` → greedy picks `4+1+1=3` coins, but `3+3=2` is optimal.*

---

## Optimal Substructure

A problem has **optimal substructure** if the optimal solution to the whole problem contains optimal solutions to its sub-problems.

Greedy requires both:
1. **Greedy choice property** — locally optimal choices lead to the global optimum.
2. **Optimal substructure** — after making a greedy choice, the remaining problem has the same structure.

---

## When Greedy Works — and When It Doesn't

### ✅ Works

| Problem | Greedy Strategy |
|---|---|
| Activity Selection | Always pick the activity finishing earliest |
| Fractional Knapsack | Sort by value/weight ratio; fill greedily |
| Huffman Coding | Always merge two least-frequent nodes |
| Dijkstra's Shortest Path | Always expand the nearest unvisited node |
| Jump Game | Track the maximum reachable index |
| Gas Station | Track cumulative fuel surplus |
| Assign Cookies | Match smallest sufficient cookie to smallest child |
| Interval Scheduling | Sort by end time |

### ❌ Doesn't Work

| Problem | Why Greedy Fails |
|---|---|
| 0/1 Knapsack | Items are indivisible; greedy may leave gaps |
| Coin Change (arbitrary denoms) | No guaranteed greedy choice property |
| Longest Path in DAG | Locally longest edge ≠ globally longest path |
| Edit Distance | Greedy edits don't account for future characters |

---

## Classic Greedy Patterns

### Pattern 1 — Scan and Track Global Best

Maintain a running variable that represents the "best reachable state" seen so far.

```
# Jump Game pattern
max_reach = 0
for i in range(n):
    if i > max_reach: return False   # can't reach index i
    max_reach = max(max_reach, i + nums[i])
return True
```

### Pattern 2 — Sort + Greedy Pairing

Sort one or both sequences, then greedily pair/assign from smallest to largest (or largest to smallest).

```
# Assign Cookies pattern
sort(children_greed)
sort(cookie_sizes)
child = cookie = 0
while child < len(children) and cookie < len(cookies):
    if cookies[cookie] >= children[child]:
        child += 1   # child satisfied
    cookie += 1      # move to next cookie regardless
return child
```

### Pattern 3 — Running Sum / Circular Reset

Track a cumulative sum; reset or record at the point it becomes negative.

```
# Gas Station pattern
total = current = start = 0
for i in range(n):
    total   += gas[i] - cost[i]
    current += gas[i] - cost[i]
    if current < 0:
        start = i + 1    # current segment is bad; try next start
        current = 0
return start if total >= 0 else -1
```

---

## Complexity Analysis

| Problem | Time | Space | Key Operation |
|---|---|---|---|
| Jump Game | O(n) | O(1) | Single scan, track max reach |
| Gas Station | O(n) | O(1) | Single scan, track surplus + start |
| Assign Cookies | O(n log n + m log m) | O(1) | Sort both arrays, two-pointer scan |
| Activity Selection | O(n log n) | O(1) | Sort by end time, greedy pick |
| Fractional Knapsack | O(n log n) | O(1) | Sort by ratio, fill greedily |

---

## Language Implementations

### Go

```go
// Jump Game — O(n) time, O(1) space
func canJump(nums []int) bool {
    maxReach := 0
    for i, v := range nums {
        if i > maxReach { return false }
        if i+v > maxReach { maxReach = i + v }
    }
    return true
}

// Assign Cookies — O(n log n) time
func findContentChildren(g, s []int) int {
    sort.Ints(g); sort.Ints(s)
    child, cookie := 0, 0
    for child < len(g) && cookie < len(s) {
        if s[cookie] >= g[child] { child++ }
        cookie++
    }
    return child
}
```

### Java

```java
// Jump Game
public boolean canJump(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i > maxReach) return false;
        maxReach = Math.max(maxReach, i + nums[i]);
    }
    return true;
}

// Gas Station
public int canCompleteCircuit(int[] gas, int[] cost) {
    int total = 0, current = 0, start = 0;
    for (int i = 0; i < gas.length; i++) {
        int diff = gas[i] - cost[i];
        total += diff; current += diff;
        if (current < 0) { start = i + 1; current = 0; }
    }
    return total >= 0 ? start : -1;
}
```

### Python

```python
# Jump Game
def can_jump(nums: list[int]) -> bool:
    max_reach = 0
    for i, v in enumerate(nums):
        if i > max_reach:
            return False
        max_reach = max(max_reach, i + v)
    return True

# Assign Cookies
def find_content_children(g: list[int], s: list[int]) -> int:
    g.sort(); s.sort()
    child = cookie = 0
    while child < len(g) and cookie < len(s):
        if s[cookie] >= g[child]:
            child += 1
        cookie += 1
    return child
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Applying greedy without verifying the greedy choice property | Prove or recall that the problem is a known greedy problem |
| Forgetting to sort before greedy pairing | Sort both sequences first; greedy only works on sorted order |
| Confusing greedy with DP | If you need to consider multiple sub-choices, you need DP |
| Off-by-one in jump game (checking `i >= maxReach` vs `i > maxReach`) | `i > maxReach` means index `i` is unreachable; `i == maxReach` means it is still reachable |
| Gas station: not checking `total >= 0` at the end | Even if a valid start is found, the circuit must be completable overall |
| Greedy pairing: not advancing the cookie pointer when the cookie is too small | Always advance `cookie` — small cookies serve no one but still consume a slot |

---

## Problems Covered

| Problem | Pattern | LeetCode |
|---|---|---|
| Jump Game | Scan + track max reach | #55 |
| Gas Station | Running sum + reset | #134 |
| Assign Cookies | Sort + two-pointer pairing | #455 |
