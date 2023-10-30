package string;

import java.util.*;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * https://leetcode.cn/problems/valid-parentheses/?envType=study-plan-v2&envId=top-100-liked
 */
public class ValidParentheses {
    public static void main(String[] args) {
        boolean flag = new Solution().isValid("([])");
        boolean flag1 = new Solution().isValid("(])");
        boolean flag2 = new Solution().isValid("()[]");

        System.out.println("flag = " + flag);
        System.out.println("flag1 = " + flag1);
        System.out.println("flag2 = " + flag2);
    }

    static class Solution {
        public boolean isValid(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            //Deque<String> stack = new LinkedList<>();

            Map<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (map.containsValue(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || !map.get(c).equals(stack.peek())) {
                        return false;
                    }

                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }
}
