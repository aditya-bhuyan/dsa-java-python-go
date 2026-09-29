import unittest
from IsomorphicStrings import IsomorphicStrings

class TestIsomorphicStrings(unittest.TestCase):
    
    def setUp(self):
        self.is_obj = IsomorphicStrings()
    
    def test_isomorphic_egg_add(self):
        self.assertTrue(self.is_obj.isIsomorphic("egg", "add"))
    
    def test_not_isomorphic_foo_bar(self):
        self.assertFalse(self.is_obj.isIsomorphic("foo", "bar"))
    
    def test_not_isomorphic_badc_baba(self):
        self.assertFalse(self.is_obj.isIsomorphic("badc", "baba"))
    
    def test_isomorphic_single_char(self):
        self.assertTrue(self.is_obj.isIsomorphic("a", "b"))
    
    def test_isomorphic_same_string(self):
        self.assertTrue(self.is_obj.isIsomorphic("egg", "egg"))
    
    def test_isomorphic_paper_title(self):
        self.assertTrue(self.is_obj.isIsomorphic("paper", "title"))
    
    def test_pattern_approach(self):
        self.assertTrue(self.is_obj.isIsomorphicPattern("egg", "add"))
        self.assertFalse(self.is_obj.isIsomorphicPattern("foo", "bar"))

if __name__ == '__main__':
    unittest.main()
