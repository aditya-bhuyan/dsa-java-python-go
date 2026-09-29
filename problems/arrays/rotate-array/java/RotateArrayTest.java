import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RotateArrayTest {

    @Test
    public void testBasicCase1() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        RotateArray.rotate(nums, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, nums);
    }

    @Test
    public void testBasicCase2() {
        int[] nums = {-1, -100, 3, 99};
        RotateArray.rotate(nums, 2);
        assertArrayEquals(new int[]{3, 99, -1, -100}, nums);
    }

    @Test
    public void testKZero() {
        int[] nums = {1};
        RotateArray.rotate(nums, 0);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    public void testKGreaterThanN() {
        int[] nums = {1, 2};
        RotateArray.rotate(nums, 3);
        assertArrayEquals(new int[]{2, 1}, nums);
    }

    @Test
    public void testKEqualsN() {
        int[] nums = {1, 2, 3};
        RotateArray.rotate(nums, 3);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        RotateArray.rotate(nums, 5);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        RotateArray.rotate(nums, 3);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    public void testNullArray() {
        RotateArray.rotate(null, 3); // Should not throw
    }
}
