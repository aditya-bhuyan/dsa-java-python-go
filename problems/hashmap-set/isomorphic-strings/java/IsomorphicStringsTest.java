import org.junit.Test;
import static org.junit.Assert.*;

public class IsomorphicStringsTest {
    
    private IsomorphicStrings is = new IsomorphicStrings();
    
    @Test
    public void testIsomorphicEggAdd() {
        assertTrue(is.isIsomorphic("egg", "add"));
    }
    
    @Test
    public void testNotIsomorphicFooBar() {
        assertFalse(is.isIsomorphic("foo", "bar"));
    }
    
    @Test
    public void testNotIsomorphicBadcBaba() {
        assertFalse(is.isIsomorphic("badc", "baba"));
    }
    
    @Test
    public void testIsomorphicSingleChar() {
        assertTrue(is.isIsomorphic("a", "b"));
    }
    
    @Test
    public void testIsomorphicSameString() {
        assertTrue(is.isIsomorphic("egg", "egg"));
    }
    
    @Test
    public void testIsomorphicPaperTitle() {
        assertTrue(is.isIsomorphic("paper", "title"));
    }
    
    @Test
    public void testPatternApproach() {
        assertTrue(is.isIsomorphicPattern("egg", "add"));
        assertFalse(is.isIsomorphicPattern("foo", "bar"));
    }
}
