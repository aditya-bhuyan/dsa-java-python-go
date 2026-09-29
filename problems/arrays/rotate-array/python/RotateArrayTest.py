import unittest
from RotateArray import RotateArray


class TestRotateArray(unittest.TestCase):

    def test_basic_case_1(self):
        nums = [1, 2, 3, 4, 5, 6, 7]
        RotateArray.rotate(nums, 3)
        self.assertEqual([5, 6, 7, 1, 2, 3, 4], nums)

    def test_basic_case_2(self):
        nums = [-1, -100, 3, 99]
        RotateArray.rotate(nums, 2)
        self.assertEqual([3, 99, -1, -100], nums)

    def test_k_zero(self):
        nums = [1]
        RotateArray.rotate(nums, 0)
        self.assertEqual([1], nums)

    def test_k_greater_than_n(self):
        nums = [1, 2]
        RotateArray.rotate(nums, 3)
        self.assertEqual([2, 1], nums)

    def test_k_equals_n(self):
        nums = [1, 2, 3]
        RotateArray.rotate(nums, 3)
        self.assertEqual([1, 2, 3], nums)

    def test_single_element(self):
        nums = [1]
        RotateArray.rotate(nums, 5)
        self.assertEqual([1], nums)

    def test_empty_list(self):
        nums = []
        RotateArray.rotate(nums, 3)
        self.assertEqual([], nums)

    def test_none_input(self):
        RotateArray.rotate(None, 3)  # Should not raise


if __name__ == '__main__':
    unittest.main()
