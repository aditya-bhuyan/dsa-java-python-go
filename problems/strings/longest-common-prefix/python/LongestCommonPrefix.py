class LongestCommonPrefix:
    @staticmethod
    def longest_common_prefix(strs):
        if not strs:
            return ""
        min_len = min(len(s) for s in strs)
        for i in range(min_len):
            c = strs[0][i]
            for s in strs:
                if s[i] != c:
                    return strs[0][:i]
        return strs[0][:min_len]

if __name__ == "__main__":
    print(LongestCommonPrefix.longest_common_prefix(["flower","flow","flight"]))
