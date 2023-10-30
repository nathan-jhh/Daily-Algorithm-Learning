package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {

    }

    // 滑动窗口
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 存储 窗口内 每个字母出现的次数
            Map<Character, Integer> window = new HashMap<>();
            int left = 0;
            int right = 0;
            int res = 0;
            while (left <= right && right < s.length()) {
                char c = s.charAt(right);
                window.put(c, window.getOrDefault(c, 0) + 1);
                right++;

                // 判断左侧窗口是否要收缩
                while (window.get(c) > 1) {
                    char d = s.charAt(left);
                    left++;
                    // 这里窗口缩小，需要同时把元素清理出去
                    window.put(d, window.get(d) - 1);
                }
                res = Math.max(res, right - left);
            }

            return res;
        }
    }
}
