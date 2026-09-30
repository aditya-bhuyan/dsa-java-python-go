package binarysearch.searchinsertposition;

/**
 * Search Insert Position — Template 2 left boundary.
 * Time: O(log n)  Space: O(1)
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length; // right = n (open bound)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) right = mid;
            else                     left  = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();
        int[] nums = {1, 3, 5, 6};
        System.out.println("target=5 → " + s.searchInsert(nums, 5) + "  (expected 2)");
        System.out.println("target=2 → " + s.searchInsert(nums, 2) + "  (expected 1)");
        System.out.println("target=7 → " + s.searchInsert(nums, 7) + "  (expected 4)");
        System.out.println("target=0 → " + s.searchInsert(nums, 0) + "  (expected 0)");
    }
}
