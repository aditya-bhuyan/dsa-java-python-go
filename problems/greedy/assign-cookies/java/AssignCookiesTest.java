// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Assign Cookies — Java JUnit 5 Tests

package greedy.assigncookies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssignCookiesTest {

    private AssignCookies solver;

    @BeforeEach
    void setUp() { solver = new AssignCookies(); }

    @Test
    void testExample1() {
        assertEquals(1, solver.findContentChildren(new int[]{1,2,3}, new int[]{1,1}));
    }

    @Test
    void testExample2AllSatisfied() {
        assertEquals(2, solver.findContentChildren(new int[]{1,2}, new int[]{1,2,3}));
    }

    @Test
    void testCookiesTooSmall() {
        assertEquals(2, solver.findContentChildren(new int[]{10,9,8,7}, new int[]{5,6,7,8}));
    }

    @Test
    void testNoCookies() {
        assertEquals(0, solver.findContentChildren(new int[]{1,2,3}, new int[]{}));
    }

    @Test
    void testNoChildren() {
        assertEquals(0, solver.findContentChildren(new int[]{}, new int[]{1,2,3}));
    }

    @Test
    void testSingleMatch() {
        assertEquals(1, solver.findContentChildren(new int[]{1}, new int[]{1}));
    }

    @Test
    void testSingleNoMatch() {
        assertEquals(0, solver.findContentChildren(new int[]{2}, new int[]{1}));
    }

    @Test
    void testAllSameSize() {
        assertEquals(3, solver.findContentChildren(new int[]{1,1,1}, new int[]{1,1,1}));
    }
}
