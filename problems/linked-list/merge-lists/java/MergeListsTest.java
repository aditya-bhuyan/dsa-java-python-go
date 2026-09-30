package linkedlist.mergelists;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MergeListsTest {

    private final MergeLists solution = new MergeLists();

    // -------------------------------------------------------------------------
    // Helper
    // -------------------------------------------------------------------------

    private int[] listToArray(MergeLists.ListNode head) {
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
        MergeLists.ListNode l1 = MergeLists.buildList(new int[]{1, 2, 4});
        MergeLists.ListNode l2 = MergeLists.buildList(new int[]{1, 3, 4});
        assertArrayEquals(
                new int[]{1, 1, 2, 3, 4, 4},
                listToArray(solution.merge(l1, l2))
        );
    }

    @Test
    void testBothEmpty() {
        assertNull(solution.merge(null, null));
    }

    @Test
    void testFirstEmpty() {
        MergeLists.ListNode l2 = MergeLists.buildList(new int[]{0});
        assertArrayEquals(
                new int[]{0},
                listToArray(solution.merge(null, l2))
        );
    }

    @Test
    void testSecondEmpty() {
        MergeLists.ListNode l1 = MergeLists.buildList(new int[]{1, 3});
        assertArrayEquals(
                new int[]{1, 3},
                listToArray(solution.merge(l1, null))
        );
    }

    @Test
    void testDifferentLengths() {
        MergeLists.ListNode l1 = MergeLists.buildList(new int[]{1, 3, 5, 7});
        MergeLists.ListNode l2 = MergeLists.buildList(new int[]{2, 4});
        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 7},
                listToArray(solution.merge(l1, l2))
        );
    }

    @Test
    void testAllSameValues() {
        MergeLists.ListNode l1 = MergeLists.buildList(new int[]{1, 1, 1});
        MergeLists.ListNode l2 = MergeLists.buildList(new int[]{1, 1});
        assertArrayEquals(
                new int[]{1, 1, 1, 1, 1},
                listToArray(solution.merge(l1, l2))
        );
    }

    @Test
    void testInterleaved() {
        MergeLists.ListNode l1 = MergeLists.buildList(new int[]{1, 5, 9});
        MergeLists.ListNode l2 = MergeLists.buildList(new int[]{2, 3, 10});
        assertArrayEquals(
                new int[]{1, 2, 3, 5, 9, 10},
                listToArray(solution.merge(l1, l2))
        );
    }
}
