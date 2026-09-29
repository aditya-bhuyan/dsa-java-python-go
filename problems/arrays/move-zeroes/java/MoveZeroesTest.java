import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveZeroesTest {

    @Test
    public void testBasicCase1() {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
    }

    @Test
    public void testBasicCase2() {
        int[] nums = {0};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[]{0}, nums);
    }

    @Test
    public void testNoZeroes() {
        int[] nums = {1, 2, 3};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    public void testAllZeroes() {
        int[] nums = {0, 0, 0};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[]{0, 0, 0}, nums);
    }

    @Test
    public void testZeroesAtStart() {
        int[] nums = {0, 0, 1};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[]{1, 0, 0}, nums);
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        MoveZeroes.moveZeroes(nums);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    public void testNullArray() {
        MoveZeroes.moveZeroes(null); // Should not throw
    }
}
