package me.suiyueyu.algs4.sec1.exercise;

import edu.princeton.cs.algs4.StdIn;
import me.suiyueyu.algs4.sec1.algs.Stack;
import me.suiyueyu.algs4.sec1.model.FixedCapacityStackOfStrings;

/**
 * Created by yzcc on 2016/8/2.
 */
public class Ex_1_3 {

    // 见FixedCapacityStackOfStrings 类
    // return a.length == N;
    public static void ex_1_3_1() {
        FixedCapacityStackOfStrings stackOfStrings = new FixedCapacityStackOfStrings(5);
    }

    public static void ex_1_3_4() {
        System.out.println(Parentheses());
    }

    /**
     * 1.3.4 便携一个Stack的用例Parentheses，从标准输入中读取一个文本流并使用
     * 栈判定其中的括号是否配对完整。例如
     * [()]{}{[()()]()}
     * 打印true
     * [(])
     * 打印false
     *
     * @return 匹配的结果
     */
    private static boolean Parentheses() {
        Stack<Character> stack = new Stack<Character>();

        String input = StdIn.readLine();
        for (int i = 0; i < input.length(); i++) {
            char next = input.charAt(i);
//            System.out.println(next);
            char stackTop;
            if (next == '(' || next == '[' || next == '{') {
                stack.push(next);
            } else if (next == ')') {
                stackTop = stack.pop();
                if (stackTop != '(') {
                    return false;
                }
            } else if (next == ']') {
                stackTop = stack.pop();
                if (stackTop != '[') {
                    return false;
                }
            } else if (next == '}') {
                stackTop = stack.pop();
                if (stackTop != '{') {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 为Stack添加一个方法peek(), 返回栈中最近添加的元素(而不弹出它)
     * 见Stack
     */
    public static void ex_1_3_7() {
        Stack<String> stack = new Stack<>();
        stack.push("aaa");
        String top = stack.peek();
        System.out.println(stack.isEmpty());
    }

    public static void main(String[] args) {
        ex_1_3_4();
    }
}
