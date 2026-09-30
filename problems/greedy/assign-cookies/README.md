# Assign Cookies

## Problem Statement

Assume you are an awesome parent and want to give your children some cookies. But you should give each child **at most one** cookie.

Each child `i` has a greed factor `g[i]`, which is the minimum size of a cookie that the child will be content with. Each cookie `j` has a size `s[j]`.

If `s[j] >= g[i]`, we can assign cookie `j` to child `i`, and the child will be content. Your goal is to **maximize the number of your content children** and output the maximum number.

**LeetCode:** [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/)  
**Difficulty:** Easy  
**Topic Tags:** Array, Two Pointers, Greedy, Sorting

---

## Examples

### Example 1
```
Input:  g = [1, 2, 3], s = [1, 1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors are 1, 2, 3.
             Cookie size 1 satisfies child with greed 1 only.
             Maximum content children = 1.
```

### Example 2
```
Input:  g = [1, 2], s = [1, 2, 3]
Output: 2
Explanation: Both children are content — cookie 1 for child 1, cookie 2 for child 2.
```

---

## Constraints

- `1 <= g.length <= 3 × 10⁴`
- `0 <= s.length <= 3 × 10⁴`
- `1 <= g[i], s[j] <= 2³¹ - 1`

---

## Understanding the Problem

We want to maximise the count of `(child, cookie)` pairs where `s[cookie] >= g[child]`. Each cookie and each child can be used at most once.

### Key Observations
1. **Sorted order is optimal:** Give the smallest sufficient cookie to the least greedy child. This leaves larger cookies for greedier children.
2. Why not give a big cookie to a small-greed child? — Wastes capacity that could satisfy a greedier child.
3. Why not give the smallest cookie to the greediest child? — May not satisfy them, wasting both the child slot and a better cookie opportunity.

### Visual
```
g = [1, 2, 3],  s = [1, 1, 2]   (sorted)

Cookie 1 (size 1) → Child g=1 ✓  (satisfied)
Cookie 2 (size 1) → Child g=2 ✗  (too small, skip)
Cookie 3 (size 2) → Child g=2 ✓  (satisfied)
No more cookies for child g=3

Answer: 2
```

---

## Approach 1: Brute Force

**Idea:** Try every possible assignment of cookies to children; track the maximum satisfying count.

**Time:** O(n! × m) — completely infeasible.

---

## Approach 2: Greedy — Sort + Two Pointers (Optimal)

**Idea:**
1. Sort `g` (children) and `s` (cookies) in ascending order.
2. Use two pointers: `child` and `cookie`.
3. For each cookie:
   - If `s[cookie] >= g[child]` → child is satisfied, advance both.
   - Otherwise → cookie is too small for this child, try the next (bigger) cookie.
4. Count satisfied children.

### Algorithm
```
sort(g); sort(s)
child = cookie = 0
while child < len(g) and cookie < len(s):
    if s[cookie] >= g[child]:
        child++    # child satisfied
    cookie++       # always advance cookie
return child
```

### Why Always Advance Cookie?
A cookie that is too small for the current (least greedy) child is too small for all remaining (greedier) children — it can never satisfy anyone. Discard it.

### Dry Run — Example 1
```
g = [1, 2, 3] (sorted),  s = [1, 1] (sorted)
child=0, cookie=0

cookie=0: s[0]=1 >= g[0]=1 → child satisfied → child=1
cookie=1: s[1]=1 < g[1]=2  → cookie too small → cookie=2
Loop ends (cookie=2 == len(s))

return child = 1 ✓
```

### Dry Run — Example 2
```
g = [1, 2] (sorted),  s = [1, 2, 3] (sorted)
child=0, cookie=0

cookie=0: s[0]=1 >= g[0]=1 → child=1, cookie=1
cookie=1: s[1]=2 >= g[1]=2 → child=2, cookie=2
Loop ends (child=2 == len(g))

return child = 2 ✓
```

### ASCII Visualization
```
Sorted g: [ 1   2   3 ]
             ↑ child pointer

Sorted s: [ 1   1   2 ]
             ↑ cookie pointer

Step 1: s[0]=1 >= g[0]=1 → match!  child=1, cookie=1
Step 2: s[1]=1 < g[1]=2  → no match, cookie=2
Step 3: s[2]=2 >= g[1]=2 → match!  child=2, cookie=3

Return 2
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n! × m) | O(1) |
| Greedy | O(n log n + m log m) | O(1) |

Sorting dominates; the two-pointer scan is O(n + m).

---

## Edge Cases

| Case | Expected |
|---|---|
| No cookies `s = []` | `0` |
| No children `g = []` | `0` |
| All cookies too small | `0` |
| All children satisfied | `min(len(g), len(s))` |
| Single child, single matching cookie | `1` |

---

## Interview Discussion Points

1. **Why sort both arrays?** — Sorting enables the greedy argument: the smallest sufficient cookie for the least greedy child is always the optimal match. Without sorting, you'd need to search for every pairing.
2. **Can we sort in descending order?** — Yes — match the largest cookie to the greediest child first. Produces the same result; ascending is more natural.
3. **Why advance `cookie` even on no-match?** — A too-small cookie can never satisfy any (remaining or current) child; keeping it would block better matches.
4. **Does this extend to "assign at most k cookies per child"?** → No — that's a different problem requiring DP or flow-based thinking.

---

## Common Mistakes

- Not sorting one or both arrays before the two-pointer scan.
- Advancing `child` when the cookie is too small — only advance `child` on a successful match.
- Forgetting to always advance `cookie` — leads to an infinite loop on a too-small cookie.

---

## Key Takeaways

- Sort + two-pointer is the canonical greedy pairing pattern.
- The invariant: *we always try to satisfy the least-greedy unsatisfied child with the smallest available cookie.*
- Any time you need to optimally pair two sorted sequences, think greedy two-pointer first.

---

## Next Problem

➡️ [Back to Greedy Concepts](../../concepts/greedy.md)
