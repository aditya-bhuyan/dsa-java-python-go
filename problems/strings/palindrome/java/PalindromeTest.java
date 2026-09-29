import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PalindromeTest {
    @Test void test1() { assertTrue(Palindrome.isPalindrome("A man, a plan, a canal: Panama")); }
    @Test void test2() { assertFalse(Palindrome.isPalindrome("race a car")); }
    @Test void test3() { assertTrue(Palindrome.isPalindrome(" ")); }
}
