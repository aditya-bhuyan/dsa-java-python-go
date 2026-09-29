import org.junit.Test;
import static org.junit.Assert.*;

public class FirstUniqueCharacterTest {
    
    private FirstUniqueCharacter fuc = new FirstUniqueCharacter();
    
    @Test
    public void testFirstUniqLeetcode() {
        assertEquals(0, fuc.firstUniqChar("leetcode"));
    }
    
    @Test
    public void testFirstUniqLoveleetcode() {
        assertEquals(2, fuc.firstUniqChar("loveleetcode"));
    }
    
    @Test
    public void testAllDuplicates() {
        assertEquals(-1, fuc.firstUniqChar("aabb"));
    }
    
    @Test
    public void testSingleChar() {
        assertEquals(0, fuc.firstUniqChar("a"));
    }
    
    @Test
    public void testUniqueAtEnd() {
        assertEquals(4, fuc.firstUniqChar("aabbc"));
    }
    
    @Test
    public void testAllUnique() {
        assertEquals(0, fuc.firstUniqChar("abc"));
    }
    
    @Test
    public void testArrayApproach() {
        assertEquals(0, fuc.firstUniqCharArray("leetcode"));
        assertEquals(2, fuc.firstUniqCharArray("loveleetcode"));
    }
    
    @Test
    public void testLinkedApproach() {
        assertEquals(0, fuc.firstUniqCharLinked("leetcode"));
        assertEquals(2, fuc.firstUniqCharLinked("loveleetcode"));
    }
}
