import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    
    // Approach 1: HashSet - Optimal
    // Time: O(n), Space: O(n)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
    
    // Approach 2: HashSet with add return value
    // Time: O(n), Space: O(n)
    public boolean containsDuplicateOptimized(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
    
    // Approach 3: Sorting (Space efficient)
    // Time: O(n log n), Space: O(1)
    public boolean containsDuplicateSorting(int[] nums) {
        java.util.Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
