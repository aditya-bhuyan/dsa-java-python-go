"""First Bad Version — Template 2 left boundary. Time: O(log n)  Space: O(1)"""


class FirstBadVersion:
    """
    In the real problem isBadVersion is provided by the platform.
    Accept it as a constructor argument for testability.
    """

    def __init__(self, first_bad: int) -> None:
        self._first_bad = first_bad

    def is_bad_version(self, version: int) -> bool:
        return version >= self._first_bad

    def first_bad_version(self, n: int) -> int:
        left, right = 1, n
        while left < right:
            mid = left + (right - left) // 2
            if self.is_bad_version(mid):
                right = mid        # preserve candidate
            else:
                left = mid + 1     # good version; search right
        return left


def main():
    sol = FirstBadVersion(first_bad=4)
    print(f"n=5, bad=4 → {sol.first_bad_version(5)}  (expected 4)")
    sol2 = FirstBadVersion(first_bad=1)
    print(f"n=1, bad=1 → {sol2.first_bad_version(1)}  (expected 1)")

if __name__ == "__main__": main()
