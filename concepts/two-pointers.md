# Two Pointers

## Table of Contents

1. [Introduction](#introduction)
2. [Opposite-End Pointers](#opposite-end-pointers)
3. [Same-Direction Pointers](#same-direction-pointers)
4. [Fast & Slow Pointers](#fast--slow-pointers)
5. [Two Pointers on Two Arrays](#two-pointers-on-two-arrays)
6. [Two Pointers vs Sliding Window](#two-pointers-vs-sliding-window)
7. [Complexity Analysis](#complexity-analysis)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

The **Two Pointers** technique uses two indices that traverse a data structure (usually a sorted array or a linked list) from different starting positions or at different speeds to solve problems in O(n) time that would otherwise require O(n²) with nested loops.

**When to reach for Two Pointers:**
- The array is **sorted** (or can be sorted) and you need pairs/triplets summing to a target.
- You need to remove duplicates or move elements in-place.
- You need to detect cycles or find the middle of a linked list.
- You need to merge two sorted sequences.
- You need to check if a string is a palindrome.

---

## Opposite-End Pointers

Two pointers start at **opposite ends** and move toward each other.

### Template

```
left = 0
right = len(arr) - 1
while left < right:
    if condition(arr[left], arr[right]):
        process(left, right)
    elif too_small:
        left++
    else:
        right--
```

### Example — Two Sum (Sorted Array)

```
arr = [-3, 0, 2, 5, 8, 11],  target = 7

left=0 (-3), right=5 (11): -3+11=8 > 7 → right--
left=0 (-3), right=4 (8):  -3+8=5  < 7 → left++
left=1 (0),  right=4 (8):  0+8=8   > 7 → right--
left=1 (0),  right=3 (5):  0+5=5   < 7 → left++
left=2 (2),  right=3 (5):  2+5=7  == 7 → found! [2, 3]
```

### Example — Valid Palindrome

```
s = "racecar"

left=0 'r', right=6 'r' → match, left++, right--
left=1 'a', right=5 'a' → match
left=2 'c', right=4 'c' → match
left=3 'e', right=3 'e' → left >= right → return True ✓
```

### Example — Reverse an Array / String

```
while left < right:
    swap(arr[left], arr[right])
    left++; right--
```

### Example — Container With Most Water

```
# Move the pointer at the shorter height inward
# (moving the taller pointer can only decrease the width without gaining height)

while left < right:
    water = min(h[left], h[right]) * (right - left)
    result = max(result, water)
    if h[left] < h[right]: left++
    else: right--
```

---

## Same-Direction Pointers

Both pointers move in the **same direction**, but at different speeds or conditions. Also known as the **fast / slow** pointer pattern when used on linked lists, or the **read / write** pointer pattern when compacting arrays.

### Template — Read/Write Pointers (In-Place Compaction)

```
write = 0
for read in range(len(arr)):
    if keep(arr[read]):
        arr[write] = arr[read]
        write++
# arr[0..write-1] contains the filtered result
```

### Example — Remove Duplicates from Sorted Array

```
arr = [1, 1, 2, 3, 3, 4]

write=0
read=0: arr[0]=1, write≠0 or different → write arr[0]=1, write=1
read=1: arr[1]=1 == arr[0] → skip
read=2: arr[2]=2 != arr[write-1]=1 → arr[1]=2, write=2
read=3: arr[3]=3 != arr[write-1]=2 → arr[2]=3, write=3
read=4: arr[4]=3 == arr[write-1]=3 → skip
read=5: arr[5]=4 != arr[write-1]=3 → arr[3]=4, write=4

Result: [1, 2, 3, 4, _, _], write=4 ✓
```

### Example — Move Zeroes to End

```
write = 0
for read = 0 to n-1:
    if arr[read] != 0:
        arr[write] = arr[read]
        write++
# Fill remaining positions with 0
while write < n:
    arr[write] = 0
    write++
```

---

## Fast & Slow Pointers

Used primarily on **linked lists**:
- **Cycle detection** (Floyd's algorithm)
- **Finding the middle** of a linked list
- **Finding the start of a cycle**

### Cycle Detection

```
slow = head
fast = head
while fast and fast.next:
    slow = slow.next
    fast = fast.next.next
    if slow == fast:
        return True        # cycle detected
return False
```

**Why it works:** If there is a cycle, fast will lap slow (they meet in O(n) steps). If there is no cycle, fast reaches None.

### Find Middle Node

```
slow = fast = head
while fast and fast.next:
    slow = slow.next
    fast = fast.next.next
# slow is now at the middle
```

For even-length lists, slow lands on the **second middle** (e.g., `[1,2,3,4]` → `slow=3`). Adjust as needed.

### Find Cycle Start

After detecting the cycle (slow == fast):

```
# Reset one pointer to head; advance both one step at a time
pointer = head
while pointer != slow:
    pointer = pointer.next
    slow = slow.next
return pointer   # cycle start
```

---

## Two Pointers on Two Arrays

When merging or comparing two sorted arrays:

```
# Merge two sorted arrays (combine step of merge sort)
i = j = 0
while i < len(A) and j < len(B):
    if A[i] <= B[j]: result.append(A[i]); i++
    else:             result.append(B[j]); j++
result += A[i:] + B[j:]
```

```
# Intersection of two sorted arrays
i = j = 0
while i < len(A) and j < len(B):
    if A[i] == B[j]: result.append(A[i]); i++; j++
    elif A[i] < B[j]: i++
    else: j++
```

---

## Two Pointers vs Sliding Window

| Feature | Two Pointers | Sliding Window |
|---|---|---|
| Data type | Usually sorted array or linked list | Unsorted array / string |
| Pointer movement | Can move toward each other (opposite) | Right always advances; left only shrinks |
| State tracking | Typically just pointer positions | Maintains a window state (sum, map, count) |
| Classic problems | Two Sum sorted, palindrome, 3Sum | Longest substring, minimum window |

The two techniques overlap when both pointers move in the same direction over an array (e.g., longest subarray with constraint) — that is technically a sliding window.

---

## Complexity Analysis

| Pattern | Time | Space |
|---|---|---|
| Opposite-end two pointers | O(n) | O(1) |
| Same-direction (read/write) | O(n) | O(1) |
| Fast/slow (cycle detection) | O(n) | O(1) |
| Two sorted arrays merge | O(m + n) | O(m + n) output |
| 3Sum (sort + two pointers) | O(n²) | O(1) |

---

## Language Implementations

### Go

```go
// Opposite-end: check palindrome
func isPalindrome(s string) bool {
    left, right := 0, len(s)-1
    for left < right {
        if s[left] != s[right] { return false }
        left++; right--
    }
    return true
}

// Read/write: remove duplicates
func removeDuplicates(nums []int) int {
    if len(nums) == 0 { return 0 }
    write := 1
    for read := 1; read < len(nums); read++ {
        if nums[read] != nums[read-1] {
            nums[write] = nums[read]; write++
        }
    }
    return write
}
```

### Java

```java
// Two Sum (sorted array)
public int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) return new int[]{left, right};
        else if (sum < target) left++;
        else right--;
    }
    return new int[]{};
}
```

### Python

```python
# 3Sum — sort + two pointers for each fixed element
def three_sum(nums: list[int]) -> list[list[int]]:
    nums.sort()
    result = []
    for i in range(len(nums) - 2):
        if i > 0 and nums[i] == nums[i-1]: continue   # skip duplicates
        left, right = i + 1, len(nums) - 1
        while left < right:
            s = nums[i] + nums[left] + nums[right]
            if s == 0:
                result.append([nums[i], nums[left], nums[right]])
                while left < right and nums[left] == nums[left+1]: left++
                while left < right and nums[right] == nums[right-1]: right--
                left++; right--
            elif s < 0: left++
            else: right--
    return result
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Applying opposite-end pointers on unsorted array for sum problems | Sort first, or use a HashMap |
| `while left < right` vs `while left <= right` — wrong loop condition | For palindrome: `<` is correct (middle element needs no check) |
| Moving both pointers on a match (e.g., not skipping duplicates in 3Sum) | Explicitly skip duplicates after recording a match |
| Fast/slow: checking `fast == slow` before the first step | Both start at `head` — check at the **bottom** of the loop, not before |
| Using two pointers on linked list without null checks | Always check `fast and fast.next` before advancing fast two steps |

---

## Problems Covered

| Problem | Pattern | LeetCode |
|---|---|---|
| Two Sum II (sorted) | Opposite-end | #167 |
| Valid Palindrome | Opposite-end | #125 |
| Container With Most Water | Opposite-end | #11 |
| 3Sum | Sort + opposite-end | #15 |
| Remove Duplicates from Sorted Array | Read/write | #26 |
| Move Zeroes | Read/write | #283 |
| Linked List Cycle | Fast/slow | #141 |
| Middle of Linked List | Fast/slow | #876 |
| Merge Sorted Array | Two arrays | #88 |
