package linkedlist.middlenode;

/**
 * ============================================================================
 * Problem: Middle of the Linked List
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given the head of a singly linked list, return the middle node.
 * If there are two middle nodes, return the second middle node.
 *
 * Example 1
 * ---------
 *
 * Input  : 1 → 2 → 3 → 4 → 5 → null
 * Output : Node with value 3
 *
 * Example 2
 * ---------
 *
 * Input  : 1 → 2 → 3 → 4 → 5 → 6 → null
 * Output : Node with value 4  (second middle)
 *
 * ============================================================================
 *
 * Approach
 * --------
 *
 * Fast and Slow Pointer.
 *
 * slow moves 1 step per iteration.
 * fast moves 2 steps per iteration.
 *
 * Loop condition:
 *
 *      while (fast != null && fast.next != null)
 *
 * When the loop ends, slow points to the middle.
 *
 * ============================================================================
 *
 * Dry Run (odd length)
 * --------------------
 *
 * Input: [1] → [2] → [3] → [4] → [5] → null
 *
 * Initial: slow=[1], fast=[1]
 * Step 1 : slow=[2], fast=[3]
 * Step 2 : slow=[3], fast=[5]
 *
 * fast.next = null → loop stops
 * Return slow = [3]
 *
 * ============================================================================
 *
 * Dry Run (even length)
 * ---------------------
 *
 * Input: [1] → [2] → [3] → [4] → [5] → [6] → null
 *
 * Initial: slow=[1], fast=[1]
 * Step 1 : slow=[2], fast=[3]
 * Step 2 : slow=[3], fast=[5]
 * Step 3 : slow=[4], fast=null
 *
 * fast = null → loop stops
 * Return slow = [4]
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(n)  — single pass.
 * Space Complexity: O(1)  — two pointer variables.
 *
 * ============================================================================
 */
public class MiddleNode {

    /**
     * Represents a node in a singly linked list.
     */
    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Returns the middle node of the linked list.
     * For even-length lists, returns the second middle node.
     *
     * @param head
     *         Head of the list (may be null).
     *
     * @return
     *         The middle ListNode.
     */
    public ListNode findMiddle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /**
     * Builds a linked list from an integer array.
     */
    public static ListNode buildList(int[] values) {

        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head    = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Prints the linked list.
     */
    public static void printList(ListNode head) {

        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" → ");
            } else {
                sb.append(" → null");
            }
            head = head.next;
        }

        System.out.println(sb);
    }

    /**
     * Demonstrates the algorithm.
     */
    public static void main(String[] args) {

        MiddleNode solution = new MiddleNode();

        System.out.println("============================================================");
        System.out.println("Middle of the Linked List");
        System.out.println("============================================================");

        // Odd-length list
        ListNode list1 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Input  : ");
        printList(list1);
        System.out.println("Middle : " + solution.findMiddle(list1).val);

        System.out.println();

        // Even-length list
        ListNode list2 = buildList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Input  : ");
        printList(list2);
        System.out.println("Middle : " + solution.findMiddle(list2).val);
    }
}
