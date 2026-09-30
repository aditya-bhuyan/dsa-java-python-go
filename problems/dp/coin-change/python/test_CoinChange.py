"""
=============================================================================
File    : test_CoinChange.py
Author  : Aditya Bhuyan
Date    : 2026-07-29
Problem : Coin Change (LeetCode #322)
=============================================================================
"""

import pytest
from CoinChange import CoinChange


@pytest.fixture
def solver():
    return CoinChange()


@pytest.mark.parametrize("coins, amount, expected", [
    ([1, 2, 5],          11,    3),
    ([2],                3,    -1),
    ([1],                0,     0),
    ([5],                5,     1),
    ([3],                9,     3),
    ([5, 10],            3,    -1),
    ([1, 5, 10, 25],     100,   4),
    ([186, 419, 83, 408], 6249, 20),
])
def test_coin_change(solver, coins, amount, expected):
    assert solver.coin_change(coins, amount) == expected
