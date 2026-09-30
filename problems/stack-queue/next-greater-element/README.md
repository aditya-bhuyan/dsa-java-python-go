# Next Greater Element

> Difficulty: **Medium**
> Topic: **Stack, Monotonic Decreasing Stack, Hash Map**
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Monotonic Stack + Hash Map)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given two distinct integer arrays `nums1` and `nums2`, where `nums1` is a **subset** of `nums2`:

For each element `nums1[i]`, find the **first greater element** to its **right** in `nums2`.

If no such element exists, return `-1` for that position.

Return an array `answer` where `answer[i]` is the next greater element of `nums1[i]` in `nums2`.

---

# Examples

## Example 1

Input

```text
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]
```

Output

```text
[-1, 3, -1]
```

Explanation

```
nums1[0] = 4  → in nums2, elements to the right of 4 are [2] → no greater → -1
nums1[1] = 1  → in nums2, elements to the right of 1 are [3, 4, 2] → 3 is first greater → 3
nums1[2] = 2  → in nums2, 2 is the last element → no greater → -1
```

---

## Example 2

Input

```text
nums1 = [2, 4]
nums2 = [1, 2, 3, 4]
```

Output

```text
[3, -1]
```

Explanation

```
nums1[0] = 2  → right of 2 in nums2 is [3, 4] → 3 is first greater → 3
nums1[1] = 4  → 4 is the last element → -1
```

---

# Constraints

```
1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 10^4
All integers in nums2 are unique.
All integers in nums1 also appear in nums2.
```

---

# Understanding the Problem

The question is: for each element in `nums1`, what is the first element to its **right in nums2** that is **larger**?

The key observation:

- `nums1` is just a set of query values.
- The actual ordering comes from `nums2`.
- We can precompute "next greater element for every value in nums2" in one pass, then answer each `nums1` query in O(1) using a hash map.

---

# Approach 1 — Brute Force

For each element in `nums1`, find its position in `nums2`, then scan right until a greater element is found.

```
for each x in nums1:
    find position of x in nums2
    for j from that position+1 to end:
        if nums2[j] > x:
            answer = nums2[j]; break
    else:
        answer = -1
```

Time: O(m × n) where m = len(nums1), n = len(nums2).

---

## Complexity

Time

```
O(m × n)
```

Space

```
O(1)
```

---

# Approach 2 — Monotonic Stack + Hash Map (Optimal)

**Step 1**: Process `nums2` with a monotonic decreasing stack to compute the next greater element for every value.

Store results in a hash map:

```
nextGreater[value] = first greater element to the right (or -1)
```

**Step 2**: For each element in `nums1`, look up its next greater element in the hash map — O(1).

### Algorithm

```
stack     = []
nextGreater = {}

for num in nums2:

    while stack is not empty and num > stack[-1]:
        popped = stack.pop()
        nextGreater[popped] = num

    stack.append(num)

# Remaining in stack have no next greater
for num in stack:
    nextGreater[num] = -1

return [ nextGreater[x] for x in nums1 ]
```

---

# Dry Run

Input

```
nums1 = [4, 1, 2]
nums2 = [1, 3, 4, 2]
```

---

Processing nums2 with monotonic stack:

```
num=1  stack empty → push 1        stack: [1]

num=3  3 > 1 → pop 1, nextGreater[1] = 3
       stack empty → push 3        stack: [3]

num=4  4 > 3 → pop 3, nextGreater[3] = 4
       stack empty → push 4        stack: [4]

num=2  2 < 4 → push 2             stack: [4, 2]

End: stack = [4, 2]
  nextGreater[4] = -1
  nextGreater[2] = -1
```

---

Final map:

```
nextGreater = { 1:3, 3:4, 4:-1, 2:-1 }
```

---

Answer for nums1:

```
nums1[0] = 4 → nextGreater[4] = -1
nums1[1] = 1 → nextGreater[1] =  3
nums1[2] = 2 → nextGreater[2] = -1

answer = [-1, 3, -1]
```

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|--------|------------|
| Time | O(m × n) |
| Space | O(1) |

---

## Monotonic Stack + Hash Map

| Metric | Complexity |
|--------|------------|
| Time | O(m + n) |
| Space | O(n) |

Each element of `nums2` is pushed once and popped at most once — O(n) total. Each query into the map is O(1).

---

# Edge Cases

## Element at the End of nums2

```
nums2 = [1, 2, 3]
query: 3 → no element to the right → -1
```

---

## All Elements Decreasing in nums2

```
nums2 = [5, 4, 3, 2, 1]
```

Stack never gets any pops. All answers are `-1`.

---

## All Elements Increasing in nums2

```
nums2 = [1, 2, 3, 4, 5]
```

Every push triggers a pop of all previous elements. Stack is empty at end.

---

# Interview Discussion

### Why precompute over all of nums2 and not just the nums1 values?

Precomputing over nums2 handles all values in one O(n) pass. Individual lookups for each nums1 value would require scanning from each value's position in nums2, giving O(m × n) in the worst case.

---

### Why store values (not indices) in the stack here?

Unlike Daily Temperatures (which needs index differences), here we only need the value of the next greater element. Values suffice.

---

### Does this extend to a circular array?

Yes. Process `nums2` twice (concatenate or use modulo index). Elements in the first pass see elements in the "second round" as potential next-greater candidates.

---

# Common Mistakes

## Forgetting to Mark Remaining Stack Elements as -1

After the loop, everything left in the stack has no next greater element. Mark them all `-1` before building the result.

---

## Looking Up the Wrong Direction

"Next greater" means to the **right** in `nums2`, not globally. The monotonic stack processes left-to-right, so this is handled correctly.

---

# Follow-up Questions

- Next Greater Element II (circular array).
- Previous Greater Element (process right-to-left).
- Next Smaller Element.
- Daily Temperatures (same pattern, answer is distance not value).

---

# Key Takeaways

After solving this problem, you should understand:

- The monotonic decreasing stack answers "next greater" for every element in O(n) total.
- Precomputing into a hash map separates the O(n) preprocessing from the O(m) query phase.
- The pattern: push candidates, pop (and answer) when a larger element arrives.
- This same technique underlies Daily Temperatures, Next Greater Element II, and Largest Rectangle in Histogram.
