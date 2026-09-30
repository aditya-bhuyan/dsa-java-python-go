import pytest
from SearchRotatedArray import SearchRotatedArray

s = SearchRotatedArray()

def test_example1():      assert s.search([4,5,6,7,0,1,2], 0) == 4
def test_example2():      assert s.search([4,5,6,7,0,1,2], 3) == -1
def test_single_not():    assert s.search([1], 0) == -1
def test_single_found():  assert s.search([1], 1) == 0
def test_two_elements():  assert s.search([1,3], 3) == 1
def test_rotated_two():   assert s.search([3,1], 1) == 1
def test_at_pivot():      assert s.search([4,5,6,7,0,1,2], 4) == 0
def test_at_end():        assert s.search([4,5,6,7,0,1,2], 2) == 6
def test_no_rotation():   assert s.search([0,1,2,4,5,6,7], 0) == 0
