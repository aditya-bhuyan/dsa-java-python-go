import pytest
from SearchInsertPosition import SearchInsertPosition

s = SearchInsertPosition()
nums = [1, 3, 5, 6]

def test_target_present():   assert s.search_insert(nums, 5) == 2
def test_insert_middle():    assert s.search_insert(nums, 2) == 1
def test_insert_end():       assert s.search_insert(nums, 7) == 4
def test_insert_beginning(): assert s.search_insert(nums, 0) == 0
def test_first_element():    assert s.search_insert(nums, 1) == 0
def test_last_element():     assert s.search_insert(nums, 6) == 3
def test_single_equal():     assert s.search_insert([3], 3) == 0
def test_single_smaller():   assert s.search_insert([3], 1) == 0
def test_single_larger():    assert s.search_insert([3], 5) == 1
