import unittest
from HappyNumber import HappyNumber

class TestHappyNumber(unittest.TestCase):
    
    def setUp(self):
        self.hn = HappyNumber()
    
    def test_happy_number_7(self):
        self.assertTrue(self.hn.isHappy(7))
    
    def test_unhappy_number_2(self):
        self.assertFalse(self.hn.isHappy(2))
    
    def test_happy_number_19(self):
        self.assertTrue(self.hn.isHappy(19))
    
    def test_happy_number_1(self):
        self.assertTrue(self.hn.isHappy(1))
    
    def test_unhappy_number_3(self):
        self.assertFalse(self.hn.isHappy(3))
    
    def test_happy_number_10(self):
        self.assertTrue(self.hn.isHappy(10))
    
    def test_floyd_implementation(self):
        self.assertTrue(self.hn.isHappyFloyd(7))
        self.assertFalse(self.hn.isHappyFloyd(2))

if __name__ == '__main__':
    unittest.main()
