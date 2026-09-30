package stackqueue.nextgreaterelement;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================================
 * Problem: Next Greater Element
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given nums1 (a subset of nums2), for each element in nums1 return the
 * first greater element to its right in nums2, or -1 if none exists.
 *
 * Example
 * -------
 *
 * nums1 = [4, 1, 2]
 * nums2 = [1, 3, 4, 2]
 * Output: [-1, 3, -1]
 *
 * ============================================================================
 *
 * Approach — Monotonic Stack + Hash Map
 * ----------------------------------------
 *
 * Phase 1: one pass over nums2 with a monotonic decreasing stack.
 *          Pop and record in a map when a larger element arrives.
 *
 * Phase 2: look up each nums1 element in the map (O(1) per query).
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(m + n)
 * Space Complexity: O(n)
 *
 * ============================================================================
 */
public class NextGreaterElement {

    /**
     * Returns the next greater element in nums2 for each value in nums1.
     *
     * @param nums1  Query values (subset of nums2).
     * @param nums2  Reference array.
     * @return       Array of next greater elements.
     */
    public int[] findNextGreater(int[] nums1, int[] nums2) {

        Map<Integer, Integer> nextGreater = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();  // stores values

        // Phase 1: build the next-greater map from nums2.
        for (int num : nums2) {

            while (!stack.isEmpty() && num > stack.peek()) {
                nextGreater.put(stack.pop(), num);
            }

            stack.push(num);
        }

        // Remaining elements have no next greater element.
        while (!stack.isEmpty()) {
            nextGreater.put(stack.pop(), -1);
        }

        // Phase 2: answer each query.
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.get(nums1[i]);
        }

        return result;
    }

    /**
     * Demonstrates the algorithm.
     */
    public static void main(String[] args) {

        NextGreaterElement solution = new NextGreaterElement();

        System.out.println("============================================================");
        System.out.println("Next Greater Element");
        System.out.println("============================================================");

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("nums1    : " + Arrays.toString(nums1));
        System.out.println("nums2    : " + Arrays.toString(nums2));
        System.out.println("Output   : " + Arrays.toString(solution.findNextGreater(nums1, nums2)));
        System.out.println("Expected : [-1, 3, -1]");
    }
}
