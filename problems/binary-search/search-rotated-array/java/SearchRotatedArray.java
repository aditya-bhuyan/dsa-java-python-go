package binarysearch.searchrotatedarray;

/**
 * Search in Rotated Sorted Array.
 * One half is always sorted — use that to narrow the search.
 * Time: O(log n)  Space: O(1)
 */
public class SearchRotatedArray {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {          // left half sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;                // target in sorted left half
                } else {
                    left = mid + 1;                 // target in right half
                }
            } else {                                // right half sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;                 // target in sorted right half
                } else {
                    right = mid - 1;                // target in left half
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRotatedArray s = new SearchRotatedArray();
        System.out.println("[4,5,6,7,0,1,2] target=0 → " + s.search(new int[]{4,5,6,7,0,1,2}, 0) + "  (expected 4)");
        System.out.println("[4,5,6,7,0,1,2] target=3 → " + s.search(new int[]{4,5,6,7,0,1,2}, 3) + "  (expected -1)");
    }
}
