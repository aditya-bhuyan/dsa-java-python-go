# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Course Schedule — pytest tests

import pytest
from CourseSchedule import CourseSchedule


@pytest.fixture
def solver():
    return CourseSchedule()


def test_two_courses_possible(solver):
    assert solver.can_finish(2, [[1, 0]]) is True


def test_two_courses_cycle(solver):
    assert solver.can_finish(2, [[1, 0], [0, 1]]) is False


def test_no_prerequisites_one_course(solver):
    assert solver.can_finish(1, []) is True


def test_no_prerequisites_many_courses(solver):
    assert solver.can_finish(5, []) is True


def test_long_chain_no_cycle(solver):
    assert solver.can_finish(5, [[1, 0], [2, 1], [3, 2], [4, 3]]) is True


def test_three_node_cycle(solver):
    assert solver.can_finish(3, [[0, 1], [1, 2], [2, 0]]) is False


def test_self_loop(solver):
    assert solver.can_finish(2, [[0, 0]]) is False


def test_disconnected_no_cycle(solver):
    assert solver.can_finish(4, [[1, 0], [3, 2]]) is True
