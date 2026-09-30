# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Flood Fill — pytest tests

import copy
import pytest
from FloodFill import FloodFill


@pytest.fixture
def solver():
    return FloodFill()


def test_basic_3x3(solver):
    image = [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
    assert solver.flood_fill(image, 1, 1, 2) == [[2, 2, 2], [2, 2, 0], [2, 0, 1]]


def test_same_color_no_op(solver):
    image = [[0, 0, 0], [0, 0, 0]]
    result = solver.flood_fill(copy.deepcopy(image), 0, 0, 0)
    assert result == [[0, 0, 0], [0, 0, 0]]


def test_single_pixel(solver):
    assert solver.flood_fill([[1]], 0, 0, 5) == [[5]]


def test_isolated_seed(solver):
    assert solver.flood_fill([[1, 0, 1]], 0, 0, 3) == [[3, 0, 1]]


def test_entire_grid_same_color(solver):
    assert solver.flood_fill([[1, 1], [1, 1]], 0, 0, 9) == [[9, 9], [9, 9]]


def test_corner_seed(solver):
    image = [[1, 1, 0], [1, 0, 0], [0, 0, 0]]
    assert solver.flood_fill(image, 0, 0, 7) == [[7, 7, 0], [7, 0, 0], [0, 0, 0]]


def test_no_spread_different_colors(solver):
    image = [[0, 1, 0]]
    assert solver.flood_fill(image, 0, 1, 3) == [[0, 3, 0]]
