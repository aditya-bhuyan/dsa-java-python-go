package linkedlist.mergelists;

/**
 * ============================================================================
 * Problem: Merge Two Sorted Lists
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given the heads of two sorted linked lists list1 and list2,
 * merge them into one sorted list by splicing together the existing nodes.
 * Return the head of the merged list.
 *
 * Example
 * -------
 *
 * list1 : 1 → 2 → 4 → null
 * list2 : 1 → 3 → 4 → null
 * Output: 1 → 1 → 2 → 3 → 4 → 4 → null
 *
 * ============================================================================
 *
 * Approach
 * --------
 *
 * Two-pointer iterative merge with a dummy head.
 *
 * dummy head eliminates special-case code for initializing the head
 * of the result list.
 *
 * While both lists have nodes:
 *
 *      Compare front nodes.
 *      Attach the smaller (or equal) node to current.
 *      Advance that list's pointer.
 *      Advance current.
 *
 * After the loop, attach the remaining nodes.
 *
 * ============================================================================
 *
 * Dry Run
 * -------
 *
 * list1 : [1] → [2] → [4]
 * list2 : [1] → [3] → [4]
 *
 * Step 1: 1 <= 1 → attach list1[1], list1=[2]
 * Step 2: 2 >  1 → attach list2[1], list2=[3]
 * Step 3: 2 <= 3 → attach list1[2], list1=[4]
 * Step 4: 4 >  3 → attach list2[3], list2=[4]
 * Step 5: 4 <= 4 → attach list1[4], list1=null
 *
 * list1 exhausted → attach remaining list2[4]
 *
 * Result: 1 → 1 → 2 → 3 → 4 → 4 → null
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(m + n)  — one pass through both lists.
 * Space Complexity: O(1)      — dummy node + current pointer only.
 *
 * ============================================================================
 */
public class MergeLists {

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
     * Merges two sorted linked lists into one sorted list.
     *
     * @param list1
     *         Head of the first sorted list (may be null).
     *
     * @param list2
     *         Head of the second sorted list (may be null).
     *
     * @return
     *         Head of the merged sorted list.
     */
    public ListNode merge(ListNode list1, ListNode list2) {

        /*
         * Dummy head simplifies the initial attachment.
         */
        ListNode dummy   = new ListNode(0);
        ListNode current = dummy;

        /*
         * Compare front nodes, attach the smaller one.
         */
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        /*
         * Attach remaining nodes.
         */
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
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
     * Demonstrates the merge algorithm.
     */
    public static void main(String[] args) {

        MergeLists solution = new MergeLists();

        ListNode list1 = buildList(new int[]{1, 2, 4});
        ListNode list2 = buildList(new int[]{1, 3, 4});

        System.out.println("============================================================");
        System.out.println("Merge Two Sorted Lists");
        System.out.println("============================================================");

        System.out.print("list1  : ");
        printList(list1);

        System.out.print("list2  : ");
        printList(list2);

        ListNode merged = solution.merge(list1, list2);

        System.out.print("Merged : ");
        printList(merged);
    }
}
