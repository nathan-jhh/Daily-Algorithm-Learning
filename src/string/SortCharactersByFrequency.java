package string;

import java.util.Random;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
 * https://leetcode.cn/problems/sort-characters-by-frequency/description/
 */
public class SortCharactersByFrequency {
    public static void main(String[] args) {
        String string = new Solution().frequencySort("treeer");
        System.out.println("string = " + string);
    }

    // 注意到题目中的说明： s 由大小写英文字母和数字组成。所以 s 的种类是有限的，
    // 因此 s 中有大量重复的值，这一点提示我们可以使用「三路快排」
    static class Solution {
        private final static Random random = new Random(System.currentTimeMillis());

        private int[] freq;

        public String frequencySort(String s) {
            // 先转换为字符数组，以避免 charAt() 方法每次都检查下标有效性
            char[] charArray = s.toCharArray();
            // 题目中的字符只有 a-z A-Z 和数字
            freq = new int[128];
            for (char c : charArray) {
                freq[c]++;
            }

            int len = charArray.length;
            quickSort3Ways(charArray, 0, len - 1);
            return new String(charArray);
        }

        /**
         * 三路快排法：将与pivot相等的元素单独放到中间的位置
         * 把数值相等的元素挤到中间，每一次确定位置的元素变多了，以后递归处理的区间长度减少了
         */
        private void quickSort3Ways(char[] charArray, int left, int right) {
            if (left >= right) {
                return;
            }

            // 使用随机元素作为pivot
            int randomIndex = left + random.nextInt(right - left + 1);
            swap(charArray, left, randomIndex);

            int pivot = charArray[left];

            // 循环不变量：
            // 1区间：nums[left+1..lt) 的频数 > pivot的频数,
            // 2区间：nums[lt..i) 的频数 = pivot的频数,
            // 3区间：nums(gt..right] 的频数 < pivot的频数
            // i为将要判断的元素

            // 初始时，为保持函数定义，[left+1..lt)、nums[lt..i)、nums(gt..right]应为空区间
            int lt = left + 1;
            int gt = right;

            int i = left + 1;
            // 当i = gt时，2区间和3区间并未重合
            while (i <= gt) {
                // 只需要在这句话外面套一层 freq [] ，其它逻辑和快速排序一样
                if (freq[charArray[i]] > freq[pivot]) {
                    // 将当前元素与2区间的第一个位置交换
                    swap(charArray, i, lt);
                    lt++;
                    i++;
                } else if (charArray[i] == pivot) {
                    i++;
                } else {
                    // 将当前元素3区间的第一个位置前一个元素交换
                    swap(charArray, i, gt);
                    gt--;
                    // 此时i不能++，因为从3区间过来的元素还需经过下一轮判断
                }
            }

            // left与第一个区间的最后一个位置交换
            swap(charArray, left, lt - 1);
            // 维持lt循环不变量的定义
            lt--;

            // 递归排序小于pivot的区间
            quickSort3Ways(charArray, left, lt - 1);
            // 递归排序大于pivot的区间
            quickSort3Ways(charArray, gt + 1, right);
        }

        private void swap(char[] charArray, int index1, int index2) {
            char temp = charArray[index1];
            charArray[index1] = charArray[index2];
            charArray[index2] = temp;
        }
    }
}
