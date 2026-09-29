import org.junit.Test;
import static org.junit.Assert.*;

public class ContainsDuplicateTest {
    
    private ContainsDuplicate cd = new ContainsDuplicate();
    
    @Test
    public void testDuplicateAtStart() {
        assertTrue(cd.containsDuplicate(new int[]{1, 2, 3, 1}));
    }
    
    @Test
    public void testAllUnique() {
        assertFalse(cd.containsDuplicate(new int[]{1, 2, 3, 4}));
    }
    
    @Test
    public void testAdjacentDuplicates() {
        assertTrue(cd.containsDuplicate(new int[]{99, 99}));
    }
    
    @Test
    public void testSingleElement() {
        assertFalse(cd.containsDuplicate(new int[]{1}));
    }
    
    @Test
    public void testNegativeNumbers() {
        assertTrue(cd.containsDuplicate(new int[]{-1, -1, 0, 1}));
    }
    
    @Test
    public void testLargeNumbers() {
        assertTrue(cd.containsDuplicate(new int[]{1000000, 1000000}));
    }
    
    @Test
    public void testLargeArray() {
        int[] arr = new int[100000];
        for (int i = 0; i < 99999; i++) {
            arr[i] = i;
        }
        arr[99999] = 99998;
        assertTrue(cd.containsDuplicate(arr));
    }
}
