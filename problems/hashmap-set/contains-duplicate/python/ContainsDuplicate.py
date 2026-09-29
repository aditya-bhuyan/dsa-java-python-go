class ContainsDuplicate:
    
    # Approach 1: HashSet - Optimal
    # Time: O(n), Space: O(n)
    def containsDuplicate(self, nums: list[int]) -> bool:
        seen = set()
        for num in nums:
            if num in seen:
                return True
            seen.add(num)
        return False
    
    # Approach 2: Length comparison
    # Time: O(n), Space: O(n)
    def containsDuplicateLength(self, nums: list[int]) -> bool:
        return len(nums) != len(set(nums))
    
    # Approach 3: Sorting
    # Time: O(n log n), Space: O(1) or O(n) depending on sort
    def containsDuplicateSorting(self, nums: list[int]) -> bool:
        nums.sort()
        for i in range(len(nums) - 1):
            if nums[i] == nums[i + 1]:
                return True
        return False
