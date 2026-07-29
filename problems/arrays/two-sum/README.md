# Two Sum

> Difficulty: **Easy**  
> Topic: **Arrays, Hash Map**  
> Languages: **Java, Python, Go**

---

# Table of Contents

1. Problem Statement
2. Examples
3. Constraints
4. Understanding the Problem
5. Brute Force Approach
6. Optimized Approach (Hash Map)
7. Dry Run
8. Complexity Analysis
9. Edge Cases
10. Interview Discussion
11. Common Mistakes
12. Follow-up Questions
13. Key Takeaways

---

# Problem Statement

Given an array of integers `nums` and an integer `target`, return the **indices** of the two numbers such that they add up to the target.

You may assume:

- Exactly one valid solution exists.
- You may not use the same element twice.
- The answer can be returned in any order.

---

# Examples

## Example 1

Input

```text
nums = [2,7,11,15]

target = 9
```

Output

```text
[0,1]
```

Explanation

```
nums[0] + nums[1]

2 + 7 = 9
```

---

## Example 2

Input

```text
nums = [3,2,4]

target = 6
```

Output

```text
[1,2]
```

Explanation

```
2 + 4 = 6
```

---

## Example 3

Input

```text
nums = [3,3]

target = 6
```

Output

```text
[0,1]
```

---

# Constraints

```
2 <= nums.length <= 10^4

-10^9 <= nums[i] <= 10^9

-10^9 <= target <= 10^9

Exactly one valid answer exists.
```

---

# Understanding the Problem

We need to identify **two distinct elements** in the array whose sum equals the target.

Notice:

We are **not** returning the values.

We are returning their **indices**.

Example

```
Array

Index

0   1   2   3

Value

2   7  11  15
```

Target

```
9
```

Need

```
Return

[0,1]
```

---

# Approach 1 — Brute Force

The simplest approach is to compare every element with every other element.

Pseudo Algorithm

```
for each i

    for each j

        if nums[i] + nums[j] == target

            return i,j
```

Visualization

```
2

compare with

7

11

15

Then

7

compare with

11

15
```

Continue until a pair is found.

### Advantages

- Easy to understand.
- No extra memory.

### Disadvantages

- Very slow for large arrays.

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

# Approach 2 — Hash Map (Optimal)

Instead of searching the entire array every time, we store previously visited numbers in a Hash Map.

The Hash Map stores:

```
Number -> Index
```

For every element:

```
Complement = target - currentNumber
```

If the complement already exists in the map,

we have found our answer.

Otherwise,

store the current number.

---

## Algorithm

```
Create empty Hash Map

For each number

    complement = target - current

    if complement exists

        return

    else

        store current
```

---

# Dry Run

Input

```
nums

[2,7,11,15]

target = 9
```

---

Iteration 1

Current

```
2
```

Complement

```
7
```

HashMap

```
{}
```

Not found

Store

```
2 -> 0
```

HashMap

```
{2:0}
```

---

Iteration 2

Current

```
7
```

Complement

```
2
```

HashMap

```
{2:0}
```

Found

Return

```
[0,1]
```

Algorithm stops.

---

# Visualization

```
Target = 9

Current = 2

Need = 7

HashMap

{}

Store

2

↓

HashMap

2 → 0

------------------

Current = 7

Need = 2

HashMap

2 → 0

Found!

Return

0,1
```

---

# Complexity Analysis

## Brute Force

| Metric | Complexity |
|----------|------------|
| Time | O(n²) |
| Space | O(1) |

---

## Hash Map

| Metric | Complexity |
|----------|------------|
| Time | O(n) |
| Space | O(n) |

The Hash Map solution is preferred for interviews.

---

# Edge Cases

## Duplicate Numbers

```
[3,3]

target=6
```

Valid

```
[0,1]
```

---

## Negative Numbers

```
[-5,10,4]

target=5
```

Works correctly.

---

## Zero

```
[0,4,3,0]

target=0
```

Works correctly.

---

## Large Arrays

The Hash Map solution scales efficiently.

---

# Common Mistakes

## Using Same Element Twice

Incorrect

```
nums[i] + nums[i]
```

---

## Returning Values Instead of Indices

Incorrect

```
[2,7]
```

Correct

```
[0,1]
```

---

## Adding Before Checking

The order matters.

Correct order:

```
Check

↓

Store
```

Otherwise duplicate values may produce incorrect results.

---

# Interview Discussion

Interviewers often ask:

### Why Hash Map?

Because lookup is approximately O(1), reducing the overall time complexity from O(n²) to O(n).

---

### Why store indices?

The problem asks for indices, not values.

---

### Why check before inserting?

Consider:

```
nums

[3,3]

target=6
```

If you insert first and then check, you may accidentally match the same element with itself depending on the implementation.

---

### Could this be solved without extra memory?

Yes.

Sort the array and use Two Pointers.

However,

sorting changes the original indices.

You would need additional bookkeeping to recover the original positions.

---

# Related Problems

- Two Sum II
- Three Sum
- Four Sum
- Contains Duplicate
- Two Sum BST
- Target Sum

---

# Key Learnings

After solving this problem, you should understand:

- Arrays
- Hash Maps
- Complements
- Constant-time lookup
- Time vs Space trade-offs
- Index management

These concepts appear repeatedly in many coding interviews and form the basis for solving more advanced problems efficiently.

---

# Next Problem

➡ **Contains Duplicate**

This introduces the **Hash Set**, another essential data structure for efficient lookups and duplicate detection.