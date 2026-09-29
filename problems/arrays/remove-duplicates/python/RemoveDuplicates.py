class RemoveDuplicates:
    """Remove duplicates from sorted array in-place."""
    
    @staticmethod
    def remove_duplicates(nums):
        """
        Remove duplicates from sorted array in-place.
        
        Time Complexity: O(n)
        Space Complexity: O(1)
        
        Args:
            nums: Sorted list with duplicates
        
        Returns:
            Number of unique elements
        """
        if not nums:
            return 0
        
        slow = 0
        for fast in range(1, len(nums)):
            if nums[fast] != nums[slow]:
                slow += 1
                nums[slow] = nums[fast]
        
        return slow + 1


if __name__ == "__main__":
    test1 = [1, 1, 2]
    k1 = RemoveDuplicates.remove_duplicates(test1)
    print(f"Test 1: k={k1} (Expected: 2), array={test1[:k1]}")

    test2 = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
    k2 = RemoveDuplicates.remove_duplicates(test2)
    print(f"Test 2: k={k2} (Expected: 5), array={test2[:k2]}")

    test3 = [1]
    k3 = RemoveDuplicates.remove_duplicates(test3)
    print(f"Test 3: k={k3} (Expected: 1)")

    test4 = []
    k4 = RemoveDuplicates.remove_duplicates(test4)
    print(f"Test 4: k={k4} (Expected: 0)")
