public class RotateArray {

    /**
     * Rotate array to the right by k steps using reversal technique.
     * 
     * Time Complexity: O(n) - three passes through array
     * Space Complexity: O(1) - in-place modification
     * 
     * @param nums array to rotate
     * @param k number of steps to rotate right
     */
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        // Normalize k to avoid unnecessary full rotations
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        
        // Reverse entire array
        reverse(nums, 0, nums.length - 1);
        // Reverse first k elements
        reverse(nums, 0, k - 1);
        // Reverse remaining elements
        reverse(nums, k, nums.length - 1);
    }

    /**
     * Helper method to reverse array from start to end index.
     */
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5, 6, 7};
        rotate(test1, 3);
        System.out.println("Test 1: " + arrayToString(test1) + " (Expected: [5,6,7,1,2,3,4])");

        int[] test2 = {-1, -100, 3, 99};
        rotate(test2, 2);
        System.out.println("Test 2: " + arrayToString(test2) + " (Expected: [3,99,-1,-100])");

        int[] test3 = {1};
        rotate(test3, 0);
        System.out.println("Test 3: " + arrayToString(test3) + " (Expected: [1])");

        int[] test4 = {1, 2};
        rotate(test4, 3);
        System.out.println("Test 4: " + arrayToString(test4) + " (Expected: [2,1])");
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
