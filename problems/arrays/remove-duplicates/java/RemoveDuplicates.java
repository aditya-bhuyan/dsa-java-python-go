public class RemoveDuplicates {

    /**
     * Remove duplicates from sorted array in-place.
     * 
     * Time Complexity: O(n) - single pass
     * Space Complexity: O(1) - in-place modification
     * 
     * @param nums sorted array with duplicates
     * @return number of unique elements
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 1, 2};
        int k1 = removeDuplicates(test1);
        System.out.println("Test 1: k=" + k1 + " (Expected: 2), array=[" + 
            arrayToString(test1, k1) + "]");

        int[] test2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = removeDuplicates(test2);
        System.out.println("Test 2: k=" + k2 + " (Expected: 5), array=[" + 
            arrayToString(test2, k2) + "]");

        int[] test3 = {1};
        int k3 = removeDuplicates(test3);
        System.out.println("Test 3: k=" + k3 + " (Expected: 1)");

        int[] test4 = {};
        int k4 = removeDuplicates(test4);
        System.out.println("Test 4: k=" + k4 + " (Expected: 0)");
    }

    private static String arrayToString(int[] arr, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k && i < arr.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
