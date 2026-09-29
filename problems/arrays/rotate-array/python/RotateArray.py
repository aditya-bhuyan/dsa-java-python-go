class RotateArray:
    """Rotate array to the right by k steps."""
    
    @staticmethod
    def rotate(nums, k):
        """
        Rotate array in-place using reversal technique.
        
        Time Complexity: O(n)
        Space Complexity: O(1)
        
        Args:
            nums: List of integers to rotate
            k: Number of steps to rotate right
        """
        if not nums or len(nums) <= 1:
            return
        
        # Normalize k
        k = k % len(nums)
        if k == 0:
            return
        
        # Reverse entire array
        RotateArray.reverse(nums, 0, len(nums) - 1)
        # Reverse first k elements
        RotateArray.reverse(nums, 0, k - 1)
        # Reverse remaining elements
        RotateArray.reverse(nums, k, len(nums) - 1)
    
    @staticmethod
    def reverse(nums, start, end):
        """Helper method to reverse array segment."""
        while start < end:
            nums[start], nums[end] = nums[end], nums[start]
            start += 1
            end -= 1


if __name__ == "__main__":
    test1 = [1, 2, 3, 4, 5, 6, 7]
    RotateArray.rotate(test1, 3)
    print(f"Test 1: {test1} (Expected: [5, 6, 7, 1, 2, 3, 4])")

    test2 = [-1, -100, 3, 99]
    RotateArray.rotate(test2, 2)
    print(f"Test 2: {test2} (Expected: [3, 99, -1, -100])")

    test3 = [1]
    RotateArray.rotate(test3, 0)
    print(f"Test 3: {test3} (Expected: [1])")

    test4 = [1, 2]
    RotateArray.rotate(test4, 3)
    print(f"Test 4: {test4} (Expected: [2, 1])")
