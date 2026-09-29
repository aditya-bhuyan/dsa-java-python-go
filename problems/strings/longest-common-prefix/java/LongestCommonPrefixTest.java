import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LongestCommonPrefixTest {
    @Test void test1() { assertEquals("fl", LongestCommonPrefix.longestCommonPrefix(new String[]{"flower","flow","flight"})); }
    @Test void test2() { assertEquals("", LongestCommonPrefix.longestCommonPrefix(new String[]{"dog","racecar","car"})); }
}
