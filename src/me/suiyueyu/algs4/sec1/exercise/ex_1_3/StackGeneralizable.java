package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import edu.princeton.cs.algs4.StdIn;

import java.util.regex.Pattern;

/**
 * Created by yzcc on 2016/8/8.
 * 1.3.45 栈的可生成性 假设我们的栈测试用例将会进行一系列的入栈和出栈操作，序列中的整数
 * 0, 1, ..., N-1(按此先后顺序排列)表示入栈操作,N个减号代表出栈操作。
 * 设计一个算法，判断给定的混合序列是否会使数组向下溢出（你所使用的空间量与N无关，即不能用
 * 某种数据结构存储所有整数）。
 * 设计一个限行时间的算法判定我们的测试用例能否产生某个给定的排列
 * (这取决于出栈操作指令的出现位置）。
 * <p>
 * 解答：除非对于某个整数k，前k次出栈操作会在前k次的入栈操作前完成，否则栈不会向下溢出。
 * 如果某个排列可以产生，那么它产生的方式一定是唯一的：如果输出序列中的下一个整数在栈顶，即将它弹出，
 * 否则将它压入栈之中
 */
public class StackGeneralizable {

    /**
     * 判断是否为整数
     * - + 号开头(0或者1次)
     * 随后跟着若干位数字(多个)
     *
     * @param str
     * @return
     * @see <a href = "http://javapub.iteye.com/blog/666544">参考链接</a>
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 因为考虑只有-号作为操作符，其他的一律认为不是
     *
     * @param str
     * @return
     */
    public static boolean isOperations(String str) {
        return str.equals("-");
    }

    public static boolean isStackGeneralizable(String[] operators) {

        int currNumber = 0;
        int currPopNum = 0;

        for (int i = 0; i < operators.length; i++) {
            if (isOperations(operators[i])) {
                currPopNum++;
            } else {
                currNumber++;
            }

            if (currPopNum > currNumber) {
                return false;
            }
        }
        return true;
    }

    /**
     * 这里限制了输入是有序的数列
     * 要不然input中可能有重复的元素，这样就不好判断了
     * @param output
     * @return +号代表压栈，-号代表出栈
     */
    public static boolean isOutputGeneralizable(String[] input, String[] output) {
        Stack<String> stack = new Stack<>();

        int j = 0;

        for (int i = 0; i < input.length; i++) {

            // 如果栈顶是我们想要的，就弹出
            while (output[j].equals(stack.peek())) {
                j++;
                stack.pop();
                System.out.print("- ");
                if (j == output.length) {
                    return true;
                }
            }

            stack.push(input[i]);
            System.out.print("+ ");
        }

        while (output[j].equals(stack.peek())) {
            j++;
            stack.pop();
            System.out.print("- ");
            if (j == output.length) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

//        String serial = StdIn.readLine();
        String serial = "1 2 3 - - -";
        String[] operators = serial.split("\\s+");

        System.out.println(isStackGeneralizable(operators));

        String input = "0 1 2 3 4 5 6 7 8 9";
        String[] inputs = input.split("\\s+");

        String output = "4 3 2 1 0 9 8 7 6 5";
        String[] outputs = output.split("\\s+");

//        String output2 = "2 1 4 3 6 5 8 7 9 0";
        String output2 = "0 1 2 3 4 5 6 7 8 9";
        String[] outputs2 = output2.split("\\s+");

//        System.out.println(isOutputGeneralizable(inputs, outputs));

        System.out.println(isOutputGeneralizable(inputs, outputs2));
    }
}
