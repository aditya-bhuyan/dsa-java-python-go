# Remove Duplicates from Sorted Array

## Problem Statement

Given an integer array `nums` sorted in non-decreasing order, remove the duplicates **in-place** such that each unique element appears only **once**. The relative order of the elements should be kept the same.

Return the number of unique elements in `nums`.

**Note:** Do not allocate extra space for another array. You must do this by **modifying the input array in-place** with O(1) extra memory.

## Problem Details

- **Input:** A sorted array of integers (may contain duplicates)
- **Output:** The number of unique elements (modify array in-place)
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - in-place modification

## Examples

### Example 1
```
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

### Example 2
```
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k.
```

## Algorithm Approach

### Two Pointer Technique (Optimal)
Use two pointers: one (slow) tracking the position for the next unique element, and one (fast) scanning through the array.

**Algorithm:**
1. Start with `slow = 0` for tracking unique elements
2. Iterate `fast` from 1 to end:
   - If `nums[fast] != nums[slow]`, increment slow and copy nums[fast] to nums[slow]
   - If `nums[fast] == nums[slow]`, skip (already seen)
3. Return `slow + 1` as the count of unique elements

**Time Complexity:** O(n) - single pass  
**Space Complexity:** O(1) - no extra space

## Key Observations

1. Array is already sorted (simplifies duplicate detection)
2. In-place modification is required
3. Only need to return the count, not reorganize the rest
4. Two-pointer technique is ideal for this constraint

## Edge Cases

- Array with single element: return 1
- Array with all duplicates: return 1
- Array with no duplicates: return n
- Empty array: return 0
- All elements are same: return 1
