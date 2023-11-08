package sort_algorithm;

import java.util.Arrays;
import java.util.Random;

import static sort_algorithm.Util.swap;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 1, 8, 4, 9, 6, 3, 7};
        new Solution().sortArray(a);
        System.out.println("a = " + Arrays.toString(a));
    }

    static class Solution {
        private final static Random r = new Random(System.currentTimeMillis());

        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        //快速排序
        private void quickSort(int[] a, int low, int high) {
            if (low >= high) {
                return;
            }
            int k = partition2(a, low, high);
            quickSort(a, low, k - 1);
            quickSort(a, k + 1, high);
        }

        /**
         * 单边遍历
         * 1. 如遇到顺序数组，性能将退化为O(n^2)，因为切分的两个数组极不平衡
         * 解决方案：使用随机元素作为pivot
         * 2. 但此方法并不能解决重复元素过多的排序问题,因为使用随机挑选的pivot很可能还是重复元素
         * 从而导致递归树仍然不平衡
         */
        private int partition(int[] nums, int left, int right) {
            // 将数组第一个元素作为切分数组的元素
            // int pivot = nums[left];

            // 使用随机元素作为pivot
            // nextInt(int i)返回[0..i)的任意元素，不包含i
            int randomIndex = left + r.nextInt(right - left + 1);
            swap(nums, left, randomIndex);
            int pivot = nums[left];

            // j取多少能让[left+1..j]和(j..i)恰好都是空区间？
            int j = left;
            // 1. 将left后边的区间分成两部分，nums[left+1..j] <= pivot，nums(j..i) > pivot
            // 2. 此定义下，j处于第一个区间最后一个位置
            // 3. i是下一个即将遍历的位置，i之前的元素都是满足前面1和2两个定义的
            for (int i = left + 1; i <= right; i++) {
                // 当前遍历的元素比pivot小，则将此元素与第二个区间的第一个元素(即j+1的位置)交换位置
                if (nums[i] <= pivot) {
                    j++;
                    swap(nums, i, j);
                }
            }
            // 将pivot与j位置元素交换，使得pivot切分此数组满足nums[left+1..j] <= pivot，nums(j..i) > pivot
            swap(nums, left, j);
            return j;
        }

        /**
         * 函数定义：将区间[left..right]切分成两部分，使得前一部分 <= pivot，
         * 后一部分 >= pivot，返回切分点 i。
         * 循环不变量：遍历时保证nums[left+1..i) <= pivot，且nums(j..right] >= pivot。
         * 双路快排法：把与pivot相等的值平均地分配在数组的两侧，
         * 可以解决数组中有大量重复元素时造成递归树不平衡的问题
         */
        private int partition2(int[] nums, int left, int right) {
            // 使用随机元素作为pivot
            int randomIndex = left + r.nextInt(right - left + 1);
            swap(nums, left, randomIndex);

            int pivot = nums[left];

            // 初始时，为保持函数定义，[left+1..i)、(j..right]应为空区间
            int i = left + 1;
            int j = right;
            while (true) {
                while (i <= j && nums[i] < pivot) {
                    i++;
                }

                // nums[j] > pivot，不能加=号，需要让重复元素也交换，平均分到两边
                while (i <= j && nums[j] > pivot) {
                    j--;
                }

                // 此时i来到了第一个大于或等于pivot的位置，j到了小于或等于pivot的位置
                // 为什么有=号？因为当i=j时数组已经满足partition的要求
                if (i >= j) {
                    break;
                }

                swap(nums, i, j);
                
                // 这里需要
                i++;
                j--;
            }
            // 这里为什么不是i呢？因为退出循环后有两种情况，
            // 1. i = j，此时交换哪个都一样；
            // 2. i > j, i在j的右边，此时一定满足nums[i] >= pivot，nums[j] <= pivot
            // 此时如果把i位置元素与pivot交换，可能会把一个大于pivot的元素交换到数组的最左边，这样不符合partition要求。
            swap(nums, left, j);

            return j;
        }

        /**
         * 快速排序
         * 三路快排法：将与pivot相等的元素单独放到中间的位置
         * 把数值相等的元素挤到中间，每一次确定位置的元素变多了，以后递归处理的区间长度减少了
         */
        private void quickSort3Ways(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }

            // 使用随机元素作为pivot
            int randomIndex = left + r.nextInt(right - left + 1);
            swap(nums, left, randomIndex);

            int pivot = nums[left];

            // 循环不变量：
            // 1区间：nums[left+1..lt) < pivot,
            // 2区间：nums[lt..i) = pivot,
            // 3区间：nums(gt..right] > pivot
            // i为将要判断的元素

            // 初始时，为保持函数定义，[left+1..lt)、nums[lt..i)、nums(gt..right]应为空区间
            int lt = left + 1;
            int gt = right;

            int i = left + 1;
            // 当i = gt时，2区间和3区间并未重合
            while (i <= gt) {
                if (nums[i] < pivot) {
                    // 将当前元素与2区间的第一个位置交换
                    swap(nums, i, lt);
                    lt++;
                    i++;
                } else if (nums[i] == pivot) {
                    i++;
                } else {
                    // 将当前元素3区间的第一个位置前一个元素交换
                    swap(nums, i, gt);
                    gt--;
                    // 此时i不能++，因为从3区间过来的元素还需经过下一轮判断
                }
            }

            // left与第一个区间的最后一个位置交换
            swap(nums, left, lt - 1);
            // 维持lt循环不变量的定义
            lt--;

            // 递归排序小于pivot的区间
            quickSort3Ways(nums, left, lt - 1);
            // 递归排序大于pivot的区间
            quickSort3Ways(nums, gt + 1, right);
        }
    }
}
