package array;

import java.util.Random;

/**
 * 面试题 17.14. 最小K个数
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * https://leetcode.cn/problems/smallest-k-lcci/description/
 */
class SmallestK {
    private static final Random random = new Random(System.currentTimeMillis());

    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        int len = arr.length;
        int left = 0;
        int right = len - 1;
        // 找下标是 k - 1 的那个数，由于在循环过程中 left <= right 一定成立，因此写 while (true)  就可以
        // 最小的k个数中最右侧的元素正好是第k-1个数，如果partition找到了这个数，也就找到了最小的k个数。
        while (true) {
            int index = partition(arr, left, right);
            if (index == k - 1) {
                break;
            } else if (index < k - 1) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    // 快速排序双路快排思想
    private int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, randomIndex);


        // all in nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        int pivot = nums[left];
        int le = left + 1;
        int ge = right;

        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }

            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }

            if (le >= ge) {
                break;
            }
            swap(nums, le, ge);
            le++;
            ge--;
        }

        swap(nums, left, ge);
        return ge;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
