class MoveZeroes:
    """Move all zeros to end while maintaining relative order."""
    
    @staticmethod
    def move_zeroes(nums):
        """
        Move all zeros to end in-place.
        
        Time Complexity: O(n)
        Space Complexity: O(1)
        
        Args:
            nums: List of integers to modify in-place
        """
        if not nums:
            return
        
        pos = 0  # Position for next non-zero element
        
        # Move all non-zero elements forward
        for num in nums:
            if num != 0:
                nums[pos] = num
                pos += 1
        
        # Fill remaining positions with zeros
        while pos < len(nums):
            nums[pos] = 0
            pos += 1


if __name__ == "__main__":
    test1 = [0, 1, 0, 3, 12]
    MoveZeroes.move_zeroes(test1)
    print(f"Test 1: {test1} (Expected: [1, 3, 12, 0, 0])")

    test2 = [0]
    MoveZeroes.move_zeroes(test2)
    print(f"Test 2: {test2} (Expected: [0])")

    test3 = [1, 2, 3]
    MoveZeroes.move_zeroes(test3)
    print(f"Test 3: {test3} (Expected: [1, 2, 3])")

    test4 = [0, 0, 1]
    MoveZeroes.move_zeroes(test4)
    print(f"Test 4: {test4} (Expected: [1, 0, 0])")
