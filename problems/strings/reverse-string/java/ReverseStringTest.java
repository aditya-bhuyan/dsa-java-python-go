import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReverseStringTest {
    @Test void test1() { 
        char[] s = {'h','e','l','l','o'};
        ReverseString.reverseString(s);
        assertArrayEquals(new char[]{'o','l','l','e','h'}, s);
    }
}
