package string;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = new Solution().longestPalindrome("babaabad");
        System.out.println("s = " + s);
    }

    static class Solution {
        public String longestPalindrome(String s) {
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                // 如果输入相同的 l 和 r，就相当于寻找长度为奇数的回文串，
                // 如果输入相邻的 l 和 r，则相当于寻找长度为偶数的回文串。

                // 以 s[i] 为中心的最长回文子串
                String s1 = palindrome(s, i, i);
                // 以 s[i] 和 s[i+1] 为中心的最长回文子串
                String s2 = palindrome(s, i, i + 1);

                // res = longest(res, s1, s2)
                res = res.length() > s1.length() ? res : s1;
                res = res.length() > s2.length() ? res : s2;
            }
            return res;
        }

        // 在 s 中寻找以 s[l] 和 s[r] 为中心的最长回文串
        private String palindrome(String s, int l, int r) {
            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) != s.charAt(r)) {
                    break;
                }
                l--;
                r++;
            }
            return s.substring(l + 1, r);
        }
    }
}
