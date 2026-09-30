package binarysearch.searchrotatedarray;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SearchRotatedArrayTest {
    private final SearchRotatedArray s = new SearchRotatedArray();

    @Test void example1()     { assertEquals(4,  s.search(new int[]{4,5,6,7,0,1,2}, 0)); }
    @Test void example2()     { assertEquals(-1, s.search(new int[]{4,5,6,7,0,1,2}, 3)); }
    @Test void singleNotFound(){ assertEquals(-1, s.search(new int[]{1}, 0)); }
    @Test void singleFound()  { assertEquals(0,  s.search(new int[]{1}, 1)); }
    @Test void twoElements()  { assertEquals(1,  s.search(new int[]{1,3}, 3)); }
    @Test void rotatedTwo()   { assertEquals(1,  s.search(new int[]{3,1}, 1)); }
    @Test void atPivot()      { assertEquals(0,  s.search(new int[]{4,5,6,7,0,1,2}, 4)); }
    @Test void atEnd()        { assertEquals(6,  s.search(new int[]{4,5,6,7,0,1,2}, 2)); }
    @Test void noRotation()   { assertEquals(0,  s.search(new int[]{0,1,2,4,5,6,7}, 0)); }
}
