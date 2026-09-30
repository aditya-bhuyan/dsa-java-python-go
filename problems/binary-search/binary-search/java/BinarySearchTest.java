package binarysearch.binarysearch;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTest {
    private final BinarySearch s = new BinarySearch();

    @Test void found()          { assertEquals(4,  s.search(new int[]{-1,0,3,5,9,12}, 9)); }
    @Test void notFound()       { assertEquals(-1, s.search(new int[]{-1,0,3,5,9,12}, 2)); }
    @Test void singleFound()    { assertEquals(0,  s.search(new int[]{5}, 5)); }
    @Test void singleNotFound() { assertEquals(-1, s.search(new int[]{5}, 3)); }
    @Test void firstElement()   { assertEquals(0,  s.search(new int[]{1,2,3,4,5}, 1)); }
    @Test void lastElement()    { assertEquals(4,  s.search(new int[]{1,2,3,4,5}, 5)); }
    @Test void beyondRange()    { assertEquals(-1, s.search(new int[]{1,2,3,4,5}, 6)); }
}
