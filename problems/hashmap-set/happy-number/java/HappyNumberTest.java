import org.junit.Test;
import static org.junit.Assert.*;

public class HappyNumberTest {
    
    private HappyNumber hn = new HappyNumber();
    
    @Test
    public void testHappyNumber7() {
        assertTrue(hn.isHappy(7));
    }
    
    @Test
    public void testUnhappyNumber2() {
        assertFalse(hn.isHappy(2));
    }
    
    @Test
    public void testHappyNumber19() {
        assertTrue(hn.isHappy(19));
    }
    
    @Test
    public void testHappyNumber1() {
        assertTrue(hn.isHappy(1));
    }
    
    @Test
    public void testUnhappyNumber3() {
        assertFalse(hn.isHappy(3));
    }
    
    @Test
    public void testHappyNumber10() {
        assertTrue(hn.isHappy(10));
    }
    
    @Test
    public void testLargeHappyNumber() {
        assertTrue(hn.isHappy(2147483647));
    }
    
    @Test
    public void testFloydImplementation() {
        assertTrue(hn.isHappyFloyd(7));
        assertFalse(hn.isHappyFloyd(2));
    }
}
