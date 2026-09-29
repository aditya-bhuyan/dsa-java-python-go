import unittest
from ContainsDuplicate import ContainsDuplicate

class TestContainsDuplicate(unittest.TestCase):
    
    def setUp(self):
        self.cd = ContainsDuplicate()
    
    def test_duplicate_at_start(self):
        self.assertTrue(self.cd.containsDuplicate([1, 2, 3, 1]))
    
    def test_all_unique(self):
        self.assertFalse(self.cd.containsDuplicate([1, 2, 3, 4]))
    
    def test_adjacent_duplicates(self):
        self.assertTrue(self.cd.containsDuplicate([99, 99]))
    
    def test_single_element(self):
        self.assertFalse(self.cd.containsDuplicate([1]))
    
    def test_negative_numbers(self):
        self.assertTrue(self.cd.containsDuplicate([-1, -1, 0, 1]))
    
    def test_large_numbers(self):
        self.assertTrue(self.cd.containsDuplicate([1000000, 1000000]))
    
    def test_large_array(self):
        arr = list(range(99999)) + [99998]
        self.assertTrue(self.cd.containsDuplicate(arr))

if __name__ == '__main__':
    unittest.main()
