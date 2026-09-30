import pytest
from FirstBadVersion import FirstBadVersion

def sol(bad): return FirstBadVersion(first_bad=bad)

def test_example1():    assert sol(4).first_bad_version(5) == 4
def test_example2():    assert sol(1).first_bad_version(1) == 1
def test_first_bad():   assert sol(1).first_bad_version(5) == 1
def test_last_bad():    assert sol(5).first_bad_version(5) == 5
def test_mid_bad():     assert sol(6).first_bad_version(10) == 6
def test_large_n():     assert sol(2**31-1).first_bad_version(2**31-1) == 2**31-1
