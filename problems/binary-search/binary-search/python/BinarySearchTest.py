import pytest
from BinarySearch import BinarySearch

s = BinarySearch()

def test_found():           assert s.search([-1,0,3,5,9,12], 9) == 4
def test_not_found():       assert s.search([-1,0,3,5,9,12], 2) == -1
def test_single_found():    assert s.search([5], 5) == 0
def test_single_not_found():assert s.search([5], 3) == -1
def test_first_element():   assert s.search([1,2,3,4,5], 1) == 0
def test_last_element():    assert s.search([1,2,3,4,5], 5) == 4
def test_beyond_range():    assert s.search([1,2,3,4,5], 6) == -1
