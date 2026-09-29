import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveDuplicatesTest {

    @Test
    public void testBasicCase1() {
        int[] nums = {1, 1, 2};
        int k = RemoveDuplicates.removeDuplicates(nums);
        assertEquals(2, k);
        assertEquals(1, nums[0]);
        assertEquals(2, nums[1]);
    }

    @Test
    public void testBasicCase2() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = RemoveDuplicates.removeDuplicates(nums);
        assertEquals(5, k);
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        int k = RemoveDuplicates.removeDuplicates(nums);
        assertEquals(1, k);
    }

    @Test
    public void testAllDuplicates() {
        int[] nums = {1, 1, 1, 1};
        int k = RemoveDuplicates.removeDuplicates(nums);
        assertEquals(1, k);
    }

    @Test
    public void testNoDuplicates() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = RemoveDuplicates.removeDuplicates(nums);
        assertEquals(5, k);
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int k = RemoveDuplicates.removeDuplicates(nums);
        assertEquals(0, k);
    }

    @Test
    public void testNullArray() {
        int k = RemoveDuplicates.removeDuplicates(null);
        assertEquals(0, k);
    }
}
