import unittest
from MoveZeroes import MoveZeroes


class TestMoveZeroes(unittest.TestCase):

    def test_basic_case_1(self):
        nums = [0, 1, 0, 3, 12]
        MoveZeroes.move_zeroes(nums)
        self.assertEqual([1, 3, 12, 0, 0], nums)

    def test_basic_case_2(self):
        nums = [0]
        MoveZeroes.move_zeroes(nums)
        self.assertEqual([0], nums)

    def test_no_zeroes(self):
        nums = [1, 2, 3]
        MoveZeroes.move_zeroes(nums)
        self.assertEqual([1, 2, 3], nums)

    def test_all_zeroes(self):
        nums = [0, 0, 0]
        MoveZeroes.move_zeroes(nums)
        self.assertEqual([0, 0, 0], nums)

    def test_zeroes_at_start(self):
        nums = [0, 0, 1]
        MoveZeroes.move_zeroes(nums)
        self.assertEqual([1, 0, 0], nums)

    def test_empty_list(self):
        nums = []
        MoveZeroes.move_zeroes(nums)
        self.assertEqual([], nums)

    def test_none_input(self):
        MoveZeroes.move_zeroes(None)  # Should not raise


if __name__ == '__main__':
    unittest.main()
