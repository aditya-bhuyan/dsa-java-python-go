package arrays.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * Problem: Two Sum
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given an integer array 'nums' and an integer 'target',
 * return the indices of the two numbers such that they add up to the target.
 *
 * Assumptions:
 *  - Exactly one valid solution exists.
 *  - The same element cannot be used twice.
 *  - The answer may be returned in any order.
 *
 * Example
 * -------
 *
 * nums   = [2, 7, 11, 15]
 * target = 9
 *
 * Output:
 *
 * [0, 1]
 *
 * Explanation:
 *
 * nums[0] + nums[1]
 * 2 + 7 = 9
 *
 * ============================================================================
 *
 * Approach
 * --------
 *
 * This solution uses a HashMap.
 *
 * The HashMap stores:
 *
 *      Number -> Index
 *
 * For every element:
 *
 *      complement = target - currentNumber
 *
 * If the complement already exists in the map,
 * then we have found the answer.
 *
 * Otherwise,
 * insert the current number into the map.
 *
 * ============================================================================
 *
 * Dry Run
 * -------
 *
 * nums = [2,7,11,15]
 * target = 9
 *
 * Step 1
 * -------
 *
 * current = 2
 * complement = 7
 *
 * map = {}
 *
 * Not found
 *
 * Store:
 *
 * 2 -> 0
 *
 * Step 2
 * -------
 *
 * current = 7
 * complement = 2
 *
 * map = {2=0}
 *
 * Found!
 *
 * Return:
 *
 * [0,1]
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity:
 *
 * O(n)
 *
 * Every element is processed only once.
 *
 * Space Complexity:
 *
 * O(n)
 *
 * HashMap stores at most n elements.
 *
 * ============================================================================
 */
public class TwoSum {

    /**
     * Finds the indices of two numbers that add up to the target value.
     *
     * @param nums
     *         Input integer array.
     *
     * @param target
     *         Desired sum.
     *
     * @return
     *         Integer array containing the indices of the two numbers.
     *
     * @throws IllegalArgumentException
     *         If no valid solution exists.
     */
    public int[] twoSum(int[] nums, int target) {

        /*
         * HashMap structure:
         *
         * Key   -> Number
         * Value -> Index
         */
        Map<Integer, Integer> map = new HashMap<>();

        /*
         * Traverse the array only once.
         */
        for (int index = 0; index < nums.length; index++) {

            int currentNumber = nums[index];

            /*
             * Calculate the complement.
             *
             * Example:
             *
             * target = 9
             * current = 2
             *
             * complement = 7
             */
            int complement = target - currentNumber;

            /*
             * If the complement already exists,
             * we have found the answer.
             */
            if (map.containsKey(complement)) {

                return new int[]{
                        map.get(complement),
                        index
                };
            }

            /*
             * Store the current number
             * for future lookups.
             */
            map.put(currentNumber, index);
        }

        /*
         * According to the problem statement,
         * this line should never execute.
         *
         * It is included for defensive programming.
         */
        throw new IllegalArgumentException(
                "No valid solution exists."
        );
    }

    /**
     * Demonstrates the usage of the Two Sum algorithm.
     */
    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] numbers = {
                2,
                7,
                11,
                15
        };

        int target = 9;

        int[] result = solution.twoSum(numbers, target);

        System.out.println("Input Array:");

        for (int number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        System.out.println("Target : " + target);

        System.out.println(
                "Indices : ["
                        + result[0]
                        + ", "
                        + result[1]
                        + "]"
        );

        System.out.println(
                "Values  : "
                        + numbers[result[0]]
                        + " + "
                        + numbers[result[1]]
                        + " = "
                        + target
        );
    }
}