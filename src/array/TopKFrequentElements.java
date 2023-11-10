package array;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * https://leetcode.cn/problems/top-k-frequent-elements/?envType=study-plan-v2&envId=top-100-liked
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] a = new int[]{2, 2, 2, 2, 2, 2, 6, 2, 6, 6, 3, 3, 6, 1, 4, 4};
        int[] topKFrequent = new Solution().topKFrequent(a, 2);
        System.out.println("topKFrequent = " + Arrays.toString(topKFrequent));
    }

    static class Solution {
        private final static Random r = new Random(System.currentTimeMillis());

        Map<Integer, Integer> freq = new HashMap<>();

        public int[] topKFrequent(int[] nums, int k) {
            for (int e : nums) {
                freq.put(e, freq.getOrDefault(e, 0) + 1);
            }

            List<int[]> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                list.add(new int[]{entry.getKey(), entry.getValue()});
            }

            // list的大小
            // 前k个高频元素中最左侧的元素是第list.size() - k个元素，因此找到这个元素，问题就解决了。
            int target = list.size() - k;
            int left = 0;
            int right = list.size() - 1;

            int targetIndex = 0;
            while (left <= right) {
                int index = partition(list, left, right);
                if (index == target) {
                    targetIndex = index;
                    break;
                } else if (index < target) {
                    left = index + 1;
                } else {
                    right = index - 1;
                }
            }

            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = list.get(targetIndex)[0];
                targetIndex++;
            }

            return res;
        }

        /**
         * 函数定义：将区间[left..right]切分成两部分，使得前一部分 <= pivot，
         * 后一部分 >= pivot，返回切分点 i。
         * 循环不变量：遍历时保证nums[left+1..i) <= pivot，且nums(j..right] >= pivot。
         * 双路快排法：把与pivot相等的值平均地分配在数组的两侧，
         * 可以解决数组中有大量重复元素时造成递归树不平衡的问题
         */
        private int partition(List<int[]> list, int left, int right) {
            // 使用随机元素作为pivot
            int randomIndex = left + r.nextInt(right - left + 1);
            Collections.swap(list, left, randomIndex);

            int pivot = list.get(left)[1];

            // 初始时，为保持函数定义，[left+1..i)、(j..right]应为空区间
            int i = left + 1;
            int j = right;
            while (true) {
                while (i <= j && list.get(i)[1] < pivot) {
                    i++;
                }

                // nums[j] > pivot，不能加=号，需要让重复元素也交换，平均分到两边
                while (i <= j && list.get(j)[1] > pivot) {
                    j--;
                }

                // 此时i来到了第一个大于或等于pivot的位置，j到了小于或等于pivot的位置
                // 为什么有=号？因为当i=j时数组已经满足partition的要求
                if (i >= j) {
                    break;
                }

                Collections.swap(list, i, j);

                // 这里需要
                i++;
                j--;
            }
            // 这里为什么不是i呢？因为退出循环后有两种情况，
            // 1. i = j，此时交换哪个都一样；
            // 2. i > j, i在j的右边，此时一定满足nums[i] >= pivot，nums[j] <= pivot
            // 此时如果把i位置元素与pivot交换，可能会把一个大于pivot的元素交换到数组的最左边，这样不符合partition要求。
            Collections.swap(list, left, j);

            return j;
        }
    }
}
