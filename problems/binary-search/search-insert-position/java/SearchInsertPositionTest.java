package binarysearch.searchinsertposition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SearchInsertPositionTest {
    private final SearchInsertPosition s = new SearchInsertPosition();
    private final int[] nums = {1, 3, 5, 6};

    @Test void targetPresent()    { assertEquals(2, s.searchInsert(nums, 5)); }
    @Test void insertMiddle()     { assertEquals(1, s.searchInsert(nums, 2)); }
    @Test void insertEnd()        { assertEquals(4, s.searchInsert(nums, 7)); }
    @Test void insertBeginning()  { assertEquals(0, s.searchInsert(nums, 0)); }
    @Test void firstElement()     { assertEquals(0, s.searchInsert(nums, 1)); }
    @Test void lastElement()      { assertEquals(3, s.searchInsert(nums, 6)); }
    @Test void singleEqual()      { assertEquals(0, s.searchInsert(new int[]{3}, 3)); }
    @Test void singleSmaller()    { assertEquals(0, s.searchInsert(new int[]{3}, 1)); }
    @Test void singleLarger()     { assertEquals(1, s.searchInsert(new int[]{3}, 5)); }
}
