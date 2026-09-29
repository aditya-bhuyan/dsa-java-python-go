class HappyNumber:
    
    # Approach 1: HashSet Cycle Detection - Optimal
    # Time: O(log n * m), Space: O(m)
    def isHappy(self, n: int) -> bool:
        seen = set()
        while n != 1 and n not in seen:
            seen.add(n)
            n = self.sumOfSquares(n)
        return n == 1
    
    # Approach 2: Floyd's Cycle Detection
    # Time: O(log n * m), Space: O(1)
    def isHappyFloyd(self, n: int) -> bool:
        slow = n
        fast = self.sumOfSquares(n)
        
        while fast != 1 and slow != fast:
            slow = self.sumOfSquares(slow)
            fast = self.sumOfSquares(self.sumOfSquares(fast))
        
        return fast == 1
    
    # Helper method to calculate sum of squares of digits
    def sumOfSquares(self, n: int) -> int:
        sum_val = 0
        while n > 0:
            digit = n % 10
            sum_val += digit * digit
            n //= 10
        return sum_val
