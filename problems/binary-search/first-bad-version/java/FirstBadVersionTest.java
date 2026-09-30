package binarysearch.firstbadversion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FirstBadVersionTest {

    private FirstBadVersion make(int firstBad) {
        return new FirstBadVersion() {
            @Override protected boolean isBadVersion(int v) { return v >= firstBad; }
        };
    }

    @Test void example1()     { assertEquals(4, make(4).firstBadVersion(5)); }
    @Test void example2()     { assertEquals(1, make(1).firstBadVersion(1)); }
    @Test void firstIsBad()   { assertEquals(1, make(1).firstBadVersion(5)); }
    @Test void lastIsBad()    { assertEquals(5, make(5).firstBadVersion(5)); }
    @Test void midIsBad()     { assertEquals(6, make(6).firstBadVersion(10)); }
    @Test void largeN() {
        assertEquals(2147483647, make(2147483647).firstBadVersion(2147483647));
    }
}
