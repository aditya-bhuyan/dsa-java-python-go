# Contains Duplicate

## Problem Statement
Given an integer array `nums`, return `true` if any value appears **at least twice** in the array, and return `false` if every element is distinct.

## Examples

### Example 1
```
Input: nums = [1,2,3,1]
Output: true
```

### Example 2
```
Input: nums = [1,2,3,4]
Output: false
```

### Example 3
```
Input: nums = [99,99]
Output: true
```

## Constraints
- 1 ≤ nums.length ≤ 10^5
- -10^9 ≤ nums[i] ≤ 10^9

## Algorithms

### 1. HashSet Approach (Optimal)
**Time:** O(n) | **Space:** O(n)

Iterate through the array and store each element in a HashSet. If we encounter an element already in the set, return true. If we complete the iteration, return false.

**Advantage:** Single pass, simple logic
**When to use:** Most common use case, good memory is available

### 2. Sorting Approach
**Time:** O(n log n) | **Space:** O(1)

Sort the array and check if adjacent elements are equal. If any pair of adjacent elements are the same, return true.

**Advantage:** Space efficient
**When to use:** Memory is limited, can modify original array

### 3. HashMap Approach
**Time:** O(n) | **Space:** O(n)

Store element count in HashMap. If any element count exceeds 1, return true.

**Advantage:** Flexible for variations
**When to use:** Need count information

## Edge Cases
- Single element: `[1]` → false
- Two identical elements: `[1, 1]` → true
- Negative numbers: `[-1, -1]` → true
- Large duplicates: `[1000000, 1000000]` → true
- All unique: `[1, 2, 3, 4, 5]` → false

## Key Insights
1. **Early termination:** Return true as soon as duplicate is found
2. **Space-time tradeoff:** HashSet trades memory for O(n) time
3. **Sorting tradeoff:** Saves space but slower and modifies array
4. **Set behavior:** Automatically rejects duplicates

## Related Problems
- Valid Anagram (Strings topic)
- First Unique Character (HashMap-Set topic)
- Isomorphic Strings (HashMap-Set topic)
