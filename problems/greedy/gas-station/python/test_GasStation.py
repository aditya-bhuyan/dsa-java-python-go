# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Gas Station — pytest tests

import pytest
from GasStation import GasStation


@pytest.fixture
def solver():
    return GasStation()


def test_example1(solver):
    assert solver.can_complete_circuit([1,2,3,4,5], [3,4,5,1,2]) == 3


def test_example2_no_solution(solver):
    assert solver.can_complete_circuit([2,3,4], [3,4,3]) == -1


def test_single_station_enough(solver):
    assert solver.can_complete_circuit([5], [4]) == 0


def test_single_station_exact(solver):
    assert solver.can_complete_circuit([1], [1]) == 0


def test_single_station_not_enough(solver):
    assert solver.can_complete_circuit([1], [2]) == -1


def test_start_at_last(solver):
    assert solver.can_complete_circuit([1,1,1,4], [2,2,2,1]) == 3


def test_start_at_0(solver):
    assert solver.can_complete_circuit([3,1,1], [1,2,2]) == 0


def test_all_zero_diff(solver):
    # Each station has equal gas and cost → any start works; greedy returns 0
    assert solver.can_complete_circuit([2,2,2], [2,2,2]) == 0
