package linkedlist.cycledetection;

/**
 * ============================================================================
 * Problem: Linked List Cycle Detection
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given the head of a linked list, return true if the list has a cycle,
 * false otherwise.
 *
 * Example 1 — Cycle
 * -----------------
 *
 * 3 → 2 → 0 → -4 → (points back to node with value 2)
 * Returns: true
 *
 * Example 2 — No Cycle
 * --------------------
 *
 * 1 → 2 → null
 * Returns: false
 *
 * ============================================================================
 *
 * Approach — Floyd's Tortoise and Hare
 * -------------------------------------
 *
 * slow moves 1 step per iteration.
 * fast moves 2 steps per iteration.
 *
 * If there is a cycle, fast will eventually lap slow — they will
 * point to the same node (reference equality).
 *
 * If there is no cycle, fast reaches null.
 *
 * ============================================================================
 *
 * Dry Run — No Cycle
 * ------------------
 *
 * Input: [1] → [2] → [3] → null
 *
 * Initial: slow=[1], fast=[1]
 * Step 1:  slow=[2], fast=[3]  → not equal
 * Step 2:  fast.next = null    → loop ends
 *
 * Return false
 *
 * ============================================================================
 *
 * Dry Run — Cycle
 * ---------------
 *
 * Input: [3] → [2] → [0] → [-4] → (back to [2])
 *
 * Initial: slow=[3], fast=[3]
 * Step 1:  slow=[2], fast=[0]   → not equal
 * Step 2:  slow=[0], fast=[2]   → not equal  (fast: [-4]→[2])
 * Step 3:  slow=[-4], fast=[-4] → equal!
 *
 * Return true
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 *
 * ============================================================================
 */
public class CycleDetection {

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
     * Determines whether the linked list contains a cycle.
     *
     * @param head
     *         Head of the linked list (may be null).
     *
     * @return
     *         true if a cycle exists, false otherwise.
     */
    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            /*
             * Reference equality — same object in memory.
             */
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /**
     * Builds a linked list from an integer array.
     * cycleIndex >= 0 connects the tail to the node at that index (creates cycle).
     * cycleIndex < 0 creates a normal list with no cycle.
     *
     * @param values       Node values.
     * @param cycleIndex   Index to create cycle at, or -1 for no cycle.
     * @return             Head of the constructed list.
     */
    public static ListNode buildList(int[] values, int cycleIndex) {

        if (values == null || values.length == 0) {
            return null;
        }

        ListNode[] nodes = new ListNode[values.length];

        for (int i = 0; i < values.length; i++) {
            nodes[i] = new ListNode(values[i]);
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        if (cycleIndex >= 0 && cycleIndex < nodes.length) {
            nodes[nodes.length - 1].next = nodes[cycleIndex];
        }

        return nodes[0];
    }

    /**
     * Demonstrates cycle detection.
     */
    public static void main(String[] args) {

        CycleDetection solution = new CycleDetection();

        System.out.println("============================================================");
        System.out.println("Linked List Cycle Detection");
        System.out.println("============================================================");

        // No cycle
        ListNode list1 = buildList(new int[]{1, 2, 3, 4}, -1);
        System.out.println("No cycle   : hasCycle = " + solution.hasCycle(list1));

        // Cycle: tail → index 1
        ListNode list2 = buildList(new int[]{3, 2, 0, -4}, 1);
        System.out.println("With cycle : hasCycle = " + solution.hasCycle(list2));
    }
}
