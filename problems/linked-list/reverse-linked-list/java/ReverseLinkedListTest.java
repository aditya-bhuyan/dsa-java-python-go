package linkedlist.reverselinkedlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ReverseLinkedListTest {

    private final ReverseLinkedList solution = new ReverseLinkedList();

    // -------------------------------------------------------------------------
    // Helper
    // -------------------------------------------------------------------------

    private int[] listToArray(ReverseLinkedList.ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // -------------------------------------------------------------------------
    // Tests
    // -------------------------------------------------------------------------

    @Test
    void testBasicExample() {
        ReverseLinkedList.ListNode head = ReverseLinkedList.buildList(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(
                new int[]{5, 4, 3, 2, 1},
                listToArray(solution.reverse(head))
        );
    }

    @Test
    void testTwoNodes() {
        ReverseLinkedList.ListNode head = ReverseLinkedList.buildList(new int[]{1, 2});
        assertArrayEquals(
                new int[]{2, 1},
                listToArray(solution.reverse(head))
        );
    }

    @Test
    void testSingleNode() {
        ReverseLinkedList.ListNode head = ReverseLinkedList.buildList(new int[]{1});
        assertArrayEquals(
                new int[]{1},
                listToArray(solution.reverse(head))
        );
    }

    @Test
    void testEmptyList() {
        assertNull(solution.reverse(null));
    }

    @Test
    void testAlreadyReversed() {
        ReverseLinkedList.ListNode head = ReverseLinkedList.buildList(new int[]{5, 4, 3, 2, 1});
        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5},
                listToArray(solution.reverse(head))
        );
    }

    @Test
    void testNegativeValues() {
        ReverseLinkedList.ListNode head = ReverseLinkedList.buildList(new int[]{-3, -2, -1});
        assertArrayEquals(
                new int[]{-1, -2, -3},
                listToArray(solution.reverse(head))
        );
    }
}
