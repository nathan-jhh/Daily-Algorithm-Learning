package array


/**
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/
 */
class RemoveDuplicatesFromSortedArrayII {
    fun removeDuplicates(nums: IntArray): Int {
        // 循环不变量: nums[0..j]中出现次数超过两次的元素只出现两次
        var j = 1
        for (i in 2 until nums.size) {
            if (nums[i] != nums[j - 1]) {
                j++
                nums[j] = nums[i]
            }
        }

        return j + 1;
    }
}

fun main() {
    val array = intArrayOf(1, 2, 2, 2, 3, 3)
    RemoveDuplicatesFromSortedArrayII().removeDuplicates(array)
    println("array:${array.contentToString()}")
}