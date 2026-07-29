import pytest

from TwoSum import TwoSum

solver = TwoSum()


def test_basic_example():
    assert solver.two_sum([2,7,11,15],9) == [0,1]


def test_second_example():
    assert solver.two_sum([3,2,4],6) == [1,2]


def test_duplicates():
    assert solver.two_sum([3,3],6) == [0,1]


def test_negative_numbers():
    assert solver.two_sum([-1,-2,-3,-4,-5],-8) == [2,4]


def test_mixed_numbers():
    assert solver.two_sum([-3,4,3,90],0) == [0,2]


def test_zero():
    assert solver.two_sum([0,4,3,0],0) == [0,3]


def test_minimum_array():
    assert solver.two_sum([5,8],13) == [0,1]


def test_no_solution():
    with pytest.raises(ValueError):
        solver.two_sum([1,2,3],100)