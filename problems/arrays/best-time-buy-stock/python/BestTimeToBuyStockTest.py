import unittest
from BestTimeToBuyStock import BestTimeToBuyStock


class TestBestTimeToBuyStock(unittest.TestCase):

    def test_basic_case_1(self):
        prices = [7, 1, 5, 3, 6, 4]
        self.assertEqual(5, BestTimeToBuyStock.max_profit(prices))

    def test_basic_case_2(self):
        prices = [7, 6, 4, 3, 1]
        self.assertEqual(0, BestTimeToBuyStock.max_profit(prices))

    def test_basic_case_3(self):
        prices = [2, 4, 1]
        self.assertEqual(2, BestTimeToBuyStock.max_profit(prices))

    def test_single_element(self):
        prices = [1]
        self.assertEqual(0, BestTimeToBuyStock.max_profit(prices))

    def test_two_elements(self):
        prices = [1, 2]
        self.assertEqual(1, BestTimeToBuyStock.max_profit(prices))

    def test_ascending_prices(self):
        prices = [1, 2, 3, 4, 5]
        self.assertEqual(4, BestTimeToBuyStock.max_profit(prices))

    def test_descending_prices(self):
        prices = [5, 4, 3, 2, 1]
        self.assertEqual(0, BestTimeToBuyStock.max_profit(prices))

    def test_all_same_prices(self):
        prices = [5, 5, 5, 5]
        self.assertEqual(0, BestTimeToBuyStock.max_profit(prices))

    def test_min_max_at_ends(self):
        prices = [1, 5, 0, 4]
        self.assertEqual(4, BestTimeToBuyStock.max_profit(prices))

    def test_empty_list(self):
        prices = []
        self.assertEqual(0, BestTimeToBuyStock.max_profit(prices))

    def test_none_input(self):
        self.assertEqual(0, BestTimeToBuyStock.max_profit(None))

    def test_large_numbers(self):
        prices = [1000000000, 1, 1000000000]
        self.assertEqual(999999999, BestTimeToBuyStock.max_profit(prices))

    def test_brute_force_comparison(self):
        test_cases = [
            [7, 1, 5, 3, 6, 4],
            [7, 6, 4, 3, 1],
            [2, 4, 1],
            [1, 2, 3, 4, 5],
            [5, 4, 3, 2, 1]
        ]

        for prices in test_cases:
            self.assertEqual(
                BestTimeToBuyStock.max_profit(prices),
                BestTimeToBuyStock.max_profit_brute_force(prices),
                f"Brute force should match greedy for: {prices}"
            )

    def test_one_liner_comparison(self):
        test_cases = [
            [7, 1, 5, 3, 6, 4],
            [7, 6, 4, 3, 1],
            [2, 4, 1],
            [1, 2, 3, 4, 5]
        ]

        for prices in test_cases:
            self.assertEqual(
                BestTimeToBuyStock.max_profit(prices),
                BestTimeToBuyStock.max_profit_one_liner(prices),
                f"One-liner should match greedy for: {prices}"
            )


if __name__ == '__main__':
    unittest.main()
