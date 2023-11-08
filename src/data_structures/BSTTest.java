package data_structures;

import java.util.ArrayList;
import java.util.Random;

public class BSTTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println("getSize(): " + bst.getSize());
        System.out.println("isEmpty(): " + bst.isEmpty());

        System.out.print("preOrder: ");
        bst.preOrder();

        System.out.print("\ninOrder: ");
        bst.inOrder();

        System.out.print("\npostOrder: ");
        bst.postOrder();

        System.out.print("\npreOrderNR: ");
        bst.preOrderNR();

        System.out.print("\nlevelOrder: ");
        bst.levelOrder();

        System.out.print("\nmin: " + bst.min());
        System.out.print("\nminNR: " + bst.minNR());

        //deleteMin  Test
        Random random = new Random();
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        for (int i = 0; i < 10000; i++) {
            bst2.add(random.nextInt(10000)); //向二分搜索树中随机添加小于10000的数
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!bst2.isEmpty()) {
            arrayList.add(bst2.deleteMin()); //不出意外的话，arrayList里边的数据应该是从小到大排列的
        }
        System.out.println("\narrayList: " + arrayList);
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1) > arrayList.get(i)) {
                throw new IllegalArgumentException("二分搜索树实现有问题！");
            }
        }
        System.out.println("deleteMin success");

        //delete Test
        bst.delete(5);
        System.out.print("\n删除后再中序遍历: ");
        bst.preOrder();
    }
}
