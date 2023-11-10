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
        int length = new Solution2().lengthOfLongestSubstring("abcabcbbwes");
        System.out.println("length = " + length);
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

    static class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int len = s.length();
            if (len < 2) {
                return len;
            }

            // 当 window 中某个字符的频数为 2 时，表示滑动窗口内有重复字符
            // 频数数组，128 由测试数据的范围决定
            // 字符类型 char在Java 中是和Unicode编码形成一一对应的映射来存储的，
            // 因此char类型可以直接作为下标，取值是对应Unicode的值
            int[] freq = new int[128];
            // 转换为字符数组，避免每一次 s.charAt() 方法检查下标越界
            char[] array = s.toCharArray();
            // 循环不变量：区间[left..right) 内没有重复元素
            int left = 0;
            // 初始时，right取0，区间[left..right)为空区间
            int right = 0;
            int res = 0;

            // right = len - 1时，正好是最后一个待检测元素
            while (right <= len - 1) {
                // 检测right
                freq[array[right]]++;

                // 否则缩小左边界，while 循环体内不断缩小边界
                while (freq[array[right]] == 2) {
                    freq[array[left]]--;
                    left++;
                }
                // 维持循环不变量定义
                right++;

                // 此时[left..right)内没有重复元素,[left..right)区间大小就是right - left
                res = Math.max(res, right - left);
            }
            return res;
        }
    }
}
