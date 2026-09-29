public class MoveZeroes {

    /**
     * Move all zeros to end while maintaining relative order of non-zero elements.
     * 
     * Time Complexity: O(n) - single pass
     * Space Complexity: O(1) - in-place modification
     * 
     * @param nums array to modify
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int pos = 0; // Position for next non-zero element
        
        // Move all non-zero elements forward
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        
        // Fill remaining positions with zeros
        while (pos < nums.length) {
            nums[pos] = 0;
            pos++;
        }
    }

    public static void main(String[] args) {
        int[] test1 = {0, 1, 0, 3, 12};
        moveZeroes(test1);
        System.out.println("Test 1: " + arrayToString(test1) + " (Expected: [1,3,12,0,0])");

        int[] test2 = {0};
        moveZeroes(test2);
        System.out.println("Test 2: " + arrayToString(test2) + " (Expected: [0])");

        int[] test3 = {1, 2, 3};
        moveZeroes(test3);
        System.out.println("Test 3: " + arrayToString(test3) + " (Expected: [1,2,3])");

        int[] test4 = {0, 0, 1};
        moveZeroes(test4);
        System.out.println("Test 4: " + arrayToString(test4) + " (Expected: [1,0,0])");
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
