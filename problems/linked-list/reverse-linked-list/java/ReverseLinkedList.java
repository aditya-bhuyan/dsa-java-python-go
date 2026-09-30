package linkedlist.reverselinkedlist;

/**
 * ============================================================================
 * Problem: Reverse Linked List
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given the head of a singly linked list, reverse the list and
 * return the new head.
 *
 * Example
 * -------
 *
 * Input  : 1 → 2 → 3 → 4 → 5 → null
 * Output : 5 → 4 → 3 → 2 → 1 → null
 *
 * ============================================================================
 *
 * Approach
 * --------
 *
 * Iterative three-pointer reversal.
 *
 * prev    starts as null.
 * current starts at head.
 *
 * Each iteration:
 *
 *      1. next         = current.next  (save forward link)
 *      2. current.next = prev          (reverse the pointer)
 *      3. prev         = current       (advance prev)
 *      4. current      = next          (advance current)
 *
 * When current is null, prev is the new head.
 *
 * ============================================================================
 *
 * Dry Run
 * -------
 *
 * Input: [1] → [2] → [3] → null
 *
 * Initial: prev = null, current = [1]
 *
 * Step 1
 *   next         = [2]
 *   current.next = null    →  [1] → null
 *   prev         = [1]
 *   current      = [2]
 *
 * Step 2
 *   next         = [3]
 *   current.next = [1]     →  [2] → [1] → null
 *   prev         = [2]
 *   current      = [3]
 *
 * Step 3
 *   next         = null
 *   current.next = [2]     →  [3] → [2] → [1] → null
 *   prev         = [3]
 *   current      = null
 *
 * Return prev = [3]
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(n)  — one pass through the list.
 * Space Complexity: O(1)  — three pointer variables only.
 *
 * ============================================================================
 */
public class ReverseLinkedList {

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
     * Reverses a singly linked list.
     *
     * @param head
     *         The head of the list (may be null).
     *
     * @return
     *         The new head of the reversed list.
     */
    public ListNode reverse(ListNode head) {

        ListNode prev    = null;
        ListNode current = head;

        while (current != null) {

            /*
             * Save the next node before we overwrite current.next.
             */
            ListNode next = current.next;

            /*
             * Reverse the pointer.
             */
            current.next = prev;

            /*
             * Advance both pointers.
             */
            prev    = current;
            current = next;
        }

        /*
         * prev is now the new head.
         */
        return prev;
    }

    // -------------------------------------------------------------------------
    // Helper utilities
    // -------------------------------------------------------------------------

    /**
     * Builds a linked list from an integer array.
     *
     * @param values  Array of values.
     * @return        Head of the constructed list.
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
     * Prints the linked list to standard output.
     *
     * @param head  Head of the list.
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
     * Demonstrates the reversal algorithm.
     */
    public static void main(String[] args) {

        ReverseLinkedList solution = new ReverseLinkedList();

        int[] values = {1, 2, 3, 4, 5};

        ListNode head = buildList(values);

        System.out.println("============================================================");
        System.out.println("Reverse Linked List");
        System.out.println("============================================================");

        System.out.print("Input  : ");
        printList(head);

        ListNode reversed = solution.reverse(head);

        System.out.print("Output : ");
        printList(reversed);
    }
}
