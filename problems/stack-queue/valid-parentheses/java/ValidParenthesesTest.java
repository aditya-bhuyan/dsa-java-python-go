package stackqueue.validparentheses;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ValidParenthesesTest {

    private final ValidParentheses solution = new ValidParentheses();

    @Test void testSinglePair()              { assertTrue(solution.isValid("()")); }
    @Test void testMultiplePairs()           { assertTrue(solution.isValid("()[]{}")); }
    @Test void testNested()                  { assertTrue(solution.isValid("{[]}")); }
    @Test void testDeeplyNested()            { assertTrue(solution.isValid("{[()]}")); }
    @Test void testMismatchedType()          { assertFalse(solution.isValid("(]")); }
    @Test void testWrongOrder()              { assertFalse(solution.isValid("([)]")); }
    @Test void testUnclosedOpen()            { assertFalse(solution.isValid("(")); }
    @Test void testExtraClose()             { assertFalse(solution.isValid(")")); }
    @Test void testEmptyString()             { assertTrue(solution.isValid("")); }
    @Test void testOnlyOpenBrackets()        { assertFalse(solution.isValid("((({{{"));}
    @Test void testOnlyCloseBrackets()       { assertFalse(solution.isValid(")))]]]"));}
    @Test void testLongValid()               { assertTrue(solution.isValid("(((({}))))")); }
}
