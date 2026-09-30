import pytest

from ValidParentheses import ValidParentheses

solver = ValidParentheses()


def test_single_pair():              assert solver.is_valid("()") is True
def test_multiple_pairs():           assert solver.is_valid("()[]{}") is True
def test_nested():                   assert solver.is_valid("{[]}") is True
def test_deeply_nested():            assert solver.is_valid("{[()]}") is True
def test_mismatched_type():          assert solver.is_valid("(]") is False
def test_wrong_order():              assert solver.is_valid("([)]") is False
def test_unclosed_open():            assert solver.is_valid("(") is False
def test_extra_close():              assert solver.is_valid(")") is False
def test_empty_string():             assert solver.is_valid("") is True
def test_only_open_brackets():       assert solver.is_valid("(((") is False
def test_only_close_brackets():      assert solver.is_valid(")))") is False
def test_long_valid():               assert solver.is_valid("(((({}))))") is True
