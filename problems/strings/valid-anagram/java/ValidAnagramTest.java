import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidAnagramTest {
    @Test void test1() { assertTrue(ValidAnagram.isAnagramArray("anagram", "nagaram")); }
    @Test void test2() { assertFalse(ValidAnagram.isAnagramArray("rat", "car")); }
    @Test void test3() { assertTrue(ValidAnagram.isAnagramArray("", "")); }
}
