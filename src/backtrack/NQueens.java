package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.12. 八皇后
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
 * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * https://leetcode.cn/problems/eight-queens-lcci/description/
 */
public class NQueens {
    public static void main(String[] args) {
        new Solution().solveNQueens(8);
    }

    static class Solution {
        int[] queenPosition;//全局或成员变量,下标表示行,值表示queen存储在哪一列
        List<List<String>> result = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            queenPosition = new int[n];
            solve8QueensInner(0, n);
            return result;
        }

        private void solve8QueensInner(int row, int n) {
            if (row == n) {
                // 问题已经解决,在这里添加进结果list
                printQueens(queenPosition, n);
                return;
            }

            for (int column = 0; column < n; column++) {
                if (isOk(row, column, n)) {
                    queenPosition[row] = column;
                    solve8QueensInner(row + 1, n);
                }
            }
        }

        private boolean isOk(int row, int column, int n) {
            int leftUp = column - 1;
            int rightUp = column + 1;
            for (int i = row - 1; i >= 0; i--) {// 逐行往上考察每一行
                if (queenPosition[i] == column) {// 第i行的column列有棋子吗？
                    return false;
                }
                if (leftUp >= 0 && queenPosition[i] == leftUp) {// 考察左上对角线：第i行leftup列有棋子吗？
                    return false;
                }
                if (rightUp <= n - 1 && queenPosition[i] == rightUp) {// 考察右上对角线：第i行rightup列有棋子吗？
                    return false;
                }
                --leftUp;
                ++rightUp;
            }
            return true;
        }

        private void printQueens(int[] oneResult, int n) { // 打印出一个二维矩阵
            List<String> arr = new ArrayList<String>();
            for (int row = 0; row < n; ++row) {
                StringBuffer s = new StringBuffer();
                for (int column = 0; column < n; ++column) {
                    if (oneResult[row] == column) {
                        System.out.print("Q ");
                        s.append("Q");
                    } else {
                        System.out.print("* ");
                        s.append(".");
                    }
                }
                System.out.println();
                arr.add(s.toString());
            }
            result.add(arr);
            System.out.println();
        }
    }
}
