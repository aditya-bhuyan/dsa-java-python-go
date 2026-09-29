# Happy Number

## Problem Statement
Write an algorithm to determine if a number `n` is happy.

A **happy number** is defined by the following process:
1. Start with any positive integer
2. Replace the number by the sum of the squares of its digits
3. Repeat the process until the number equals 1 (happy) or it loops endlessly in a cycle which does not include 1 (unhappy)

Return `true` if `n` is a happy number, and `false` if not.

## Examples

### Example 1
```
Input: n = 7
Output: true
Explanation: 7 → 49 → 97 → 130 → 10 → 1
```

### Example 2
```
Input: n = 2
Output: false
Explanation: 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 (cycle detected)
```

### Example 3
```
Input: n = 19
Output: true
Explanation: 19 → 82 → 68 → 100 → 1
```

## Constraints
- 1 ≤ n ≤ 2^31 - 1

## Algorithms

### 1. HashSet Cycle Detection (Optimal)
**Time:** O(log n * m) | **Space:** O(m)

Keep a HashSet of seen numbers. At each step, compute sum of squares of digits. If we reach 1, it's happy. If we see a repeated number, it's unhappy.

**Advantage:** Simple, detects cycles easily
**When to use:** Most common approach

### 2. Floyd's Cycle Detection (Space Efficient)
**Time:** O(log n * m) | **Space:** O(1)

Use two pointers (slow and fast) to detect cycles. Similar to linked list cycle detection. If pointers meet at 1, it's happy. If they meet elsewhere, it's unhappy.

**Advantage:** O(1) space, elegant solution
**When to use:** Memory constrained

### 3. Recursive with Memoization
**Time:** O(log n * m) | **Space:** O(m)

Use recursion with memoization to detect if a number leads to 1.

**Advantage:** Clean recursive code
**When to use:** Functional programming style preferred

## Edge Cases
- Single digit happy: `1` → true, `7` → true
- Single digit unhappy: `2` → false, `3` → false
- Large numbers: `2147483647` → true/false
- Two-digit cycles: `2` → false

## Key Insights
1. **Cycle detection is key:** Must detect when no progress is made
2. **Bounded iterations:** Even large numbers converge quickly
3. **Sum converges:** Sum of squares decreases for large numbers
4. **Small cycle set:** Cycles typically occur within single/double digits

## Related Problems
- Contains Duplicate (Hashmap-Set topic)
- Isomorphic Strings (HashMap-Set topic)
- First Unique Character (HashMap-Set topic)
