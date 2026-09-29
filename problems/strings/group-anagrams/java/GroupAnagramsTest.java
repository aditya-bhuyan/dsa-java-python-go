import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GroupAnagramsTest {
    @Test void test1() { 
        List<List<String>> result = GroupAnagrams.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        assertEquals(3, result.size());
    }
    @Test void test2() { 
        List<List<String>> result = GroupAnagrams.groupAnagrams(new String[]{""});
        assertEquals(1, result.size());
    }
}
