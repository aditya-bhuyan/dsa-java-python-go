# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Permutation in String — pytest tests

import pytest
from PermutationInString import PermutationInString


@pytest.fixture
def solver():
    return PermutationInString()


def test_basic_true(solver):
    assert solver.check_inclusion("ab", "eidbaooo") is True


def test_basic_false(solver):
    assert solver.check_inclusion("ab", "eidboaoo") is False


def test_single_char_present(solver):
    assert solver.check_inclusion("a", "ab") is True


def test_single_char_absent(solver):
    assert solver.check_inclusion("z", "ab") is False


def test_s1_longer_than_s2(solver):
    assert solver.check_inclusion("abc", "ab") is False


def test_s1_equals_s2(solver):
    assert solver.check_inclusion("abc", "abc") is True


def test_duplicates_satisfied(solver):
    assert solver.check_inclusion("aa", "aab") is True


def test_duplicates_not_satisfied(solver):
    assert solver.check_inclusion("aa", "ab") is False


def test_adc_in_dcda(solver):
    assert solver.check_inclusion("adc", "dcda") is True


def test_permutation_at_end(solver):
    assert solver.check_inclusion("ab", "oooab") is True
