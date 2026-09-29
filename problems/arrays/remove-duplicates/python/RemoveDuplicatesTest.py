import unittest
from RemoveDuplicates import RemoveDuplicates


class TestRemoveDuplicates(unittest.TestCase):

    def test_basic_case_1(self):
        nums = [1, 1, 2]
        k = RemoveDuplicates.remove_duplicates(nums)
        self.assertEqual(2, k)
        self.assertEqual(1, nums[0])
        self.assertEqual(2, nums[1])

    def test_basic_case_2(self):
        nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
        k = RemoveDuplicates.remove_duplicates(nums)
        self.assertEqual(5, k)

    def test_single_element(self):
        nums = [1]
        k = RemoveDuplicates.remove_duplicates(nums)
        self.assertEqual(1, k)

    def test_all_duplicates(self):
        nums = [1, 1, 1, 1]
        k = RemoveDuplicates.remove_duplicates(nums)
        self.assertEqual(1, k)

    def test_no_duplicates(self):
        nums = [1, 2, 3, 4, 5]
        k = RemoveDuplicates.remove_duplicates(nums)
        self.assertEqual(5, k)

    def test_empty_list(self):
        nums = []
        k = RemoveDuplicates.remove_duplicates(nums)
        self.assertEqual(0, k)

    def test_none_input(self):
        k = RemoveDuplicates.remove_duplicates(None)
        self.assertEqual(0, k)


if __name__ == '__main__':
    unittest.main()
