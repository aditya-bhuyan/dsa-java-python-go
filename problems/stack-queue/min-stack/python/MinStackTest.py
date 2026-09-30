import pytest

from MinStack import MinStack


def test_basic_operations():
    ms = MinStack()
    ms.push(-2)
    ms.push(0)
    ms.push(-3)
    assert ms.get_min() == -3
    ms.pop()
    assert ms.top() == 0
    assert ms.get_min() == -2


def test_duplicate_minimums():
    ms = MinStack()
    ms.push(1)
    ms.push(1)
    ms.pop()
    assert ms.get_min() == 1


def test_min_updates_on_pop():
    ms = MinStack()
    ms.push(5)
    ms.push(3)
    ms.push(7)
    assert ms.get_min() == 3
    ms.pop()      # remove 7
    assert ms.get_min() == 3
    ms.pop()      # remove 3
    assert ms.get_min() == 5


def test_single_element():
    ms = MinStack()
    ms.push(42)
    assert ms.top() == 42
    assert ms.get_min() == 42


def test_increasing_values():
    ms = MinStack()
    ms.push(1)
    ms.push(2)
    ms.push(3)
    assert ms.get_min() == 1


def test_decreasing_values():
    ms = MinStack()
    ms.push(3)
    ms.push(2)
    ms.push(1)
    assert ms.get_min() == 1
    ms.pop()
    assert ms.get_min() == 2
