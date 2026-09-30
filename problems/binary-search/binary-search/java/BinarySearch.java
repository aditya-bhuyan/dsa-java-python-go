package binarysearch.binarysearch;

/**
 * Binary Search — Template 1 exact match.
 * Time: O(log n)  Space: O(1)
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if      (nums[mid] == target) return mid;
            else if (nums[mid] <  target) left  = mid + 1;
            else                          right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch s = new BinarySearch();
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println("search(9) = " + s.search(nums, 9) + "  (expected 4)");
        System.out.println("search(2) = " + s.search(nums, 2) + "  (expected -1)");
    }
}
