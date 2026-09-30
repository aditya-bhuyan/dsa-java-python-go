import pytest

from DailyTemperatures import DailyTemperatures

solver = DailyTemperatures()


def test_example1():
    assert solver.wait_days([73, 74, 75, 71, 69, 72, 76, 73]) == [1, 1, 4, 2, 1, 1, 0, 0]

def test_all_increasing():
    assert solver.wait_days([30, 40, 50, 60]) == [1, 1, 1, 0]

def test_all_decreasing():
    assert solver.wait_days([60, 50, 40, 30]) == [0, 0, 0, 0]

def test_single_element():
    assert solver.wait_days([50]) == [0]

def test_all_same():
    assert solver.wait_days([50, 50, 50]) == [0, 0, 0]

def test_two_increasing():
    assert solver.wait_days([30, 60]) == [1, 0]
