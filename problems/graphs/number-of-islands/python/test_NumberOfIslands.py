# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Number of Islands — pytest tests

import copy
import pytest
from NumberOfIslands import NumberOfIslands


@pytest.fixture
def solver():
    return NumberOfIslands()


def g(grid):
    """Deep-copy grid so each test gets a fresh copy."""
    return copy.deepcopy(grid)


GRID_SINGLE_ISLAND = [
    ["1","1","1","1","0"],
    ["1","1","0","1","0"],
    ["1","1","0","0","0"],
    ["0","0","0","0","0"],
]

GRID_THREE_ISLANDS = [
    ["1","1","0","0","0"],
    ["1","1","0","0","0"],
    ["0","0","1","0","0"],
    ["0","0","0","1","1"],
]


def test_single_island(solver):
    assert solver.num_islands(g(GRID_SINGLE_ISLAND)) == 1


def test_three_islands(solver):
    assert solver.num_islands(g(GRID_THREE_ISLANDS)) == 3


def test_all_water(solver):
    assert solver.num_islands([["0","0"],["0","0"]]) == 0


def test_all_land(solver):
    assert solver.num_islands([["1","1"],["1","1"]]) == 1


def test_single_land_cell(solver):
    assert solver.num_islands([["1"]]) == 1


def test_single_water_cell(solver):
    assert solver.num_islands([["0"]]) == 0


def test_diagonal_are_separate(solver):
    assert solver.num_islands([["1","0"],["0","1"]]) == 2


def test_alternating_row(solver):
    assert solver.num_islands([["1","0","1","0","1"]]) == 3


def test_empty_grid(solver):
    assert solver.num_islands([]) == 0
