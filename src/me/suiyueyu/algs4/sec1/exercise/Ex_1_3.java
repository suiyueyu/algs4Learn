package me.suiyueyu.algs4.sec1.exercise;

import edu.princeton.cs.algs4.Particle;
import edu.princeton.cs.algs4.StdIn;
import me.suiyueyu.algs4.sec1.algs.Queue;
import me.suiyueyu.algs4.sec1.algs.Stack;
import me.suiyueyu.algs4.sec1.model.FixedCapacityStackOfStrings;

import java.util.Iterator;

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

    /**
     * 编写一段程序，从标准输入得到一个缺少左括号的表达式并打印出
     * 补全括号以后的中序表达式。
     * 例如给定输入
     * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
     * 你的程序应该输出
     * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
     */
    public static void ex_1_3_9() {
        Stack<String> expressionStack = new Stack<>();
        Stack<Character> symbolStack = new Stack<>();

        char next;
        while (!StdIn.isEmpty()) {
            next = StdIn.readChar();
            if (next != ' ') {
                if (next == '+' || next == '-' || next == '*' || next == '/') {
                    symbolStack.push(next);
                } else if (Character.isDigit(next)) {
                    expressionStack.push(next + "");
                } else if (next == ')') {
                    String expr1 = expressionStack.pop();
                    String expr2 = expressionStack.pop();
                    char symbol = symbolStack.pop();
                    expressionStack.push("( " + expr2 + " " + symbol + " " + expr1 + " )");
                }
            }
        }
        String expr = expressionStack.pop();
        System.out.println(expr);

    }

    /**
     * 1.3.10编写一个过滤器 InfixToPostfix，将算术表达式由中序表达式转为后序表达式
     * 中序表达式：2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 )
     * 前序表达式：+ / * 2 3 - 2 1 * 3 - 4 1
     * 后序表达式：2 3 * 2 1 - / 3 4 1 - * +
     */
    public static void ex_1_3_10() {
        System.out.println("input the expresssion");
        System.out.println(InfixToPostfix());
    }

    /**
     * 中序表达式转成后序表达式算法：
     *
     * @return
     * @see <a href = "https://en.wikipedia.org/wiki/Shunting-yard_algorithm" >Shunting-yard algorithm</a>
     * 1）当输入的是数字时，直接输出到后序表达式的序列中
     * 2）当输入的是开括号时，把它压栈
     * 3）当输入的是闭括号时，先判断栈是否为空，若为空，则发生错误并进行相关处理。
     * 若非空，把栈中元素依次弹出并输出到序列中，直到遇到第一个开括号为止，若没有遇到开括号，也即发生错误，进行相关处理
     * 4）当输入的是运算符op（+、- 、×、/）时，有两种情况需要处理：
     * a)执行循环，判断当（栈非空and栈顶不是开括号and栈顶运算符的优先级不低于输入的运算符的优先级）时，
     * 反复将栈顶元素弹出并添加到序列中
     * b)否则把输入的运算符op压栈
     * 5）当中序表达式的符号序列全部读入后，若栈内仍有元素，把它们依次弹出并放到后序表达式序列尾部。
     * 若弹出的元素遇到空括号，则说明不匹配，发生错误，并进行相关处理
     */
    private static String InfixToPostfix() {
        String result = "";
        Stack<Character> symbolStack = new Stack<>();
        char token; // for the next token read in
        char stackTop;
        while (!StdIn.isEmpty()) {
            token = StdIn.readChar();
            if (token == ' ') {

            } else if (Character.isDigit(token)) {
                // 这个数是数字，直接输出
//                System.out.print(" " + token);
                result = result + " " + token;
            } else if (token == '(') {
                // 左括号，压栈
                symbolStack.push(token);
            } else if (token == ')') {
                // 右括号
                // 3）当输入的是闭括号时，先判断栈是否为空，若为空，则发生错误并进行相关处理。
                if (symbolStack.isEmpty()) {
                    // 括号不匹配
                    throw new RuntimeException("parenthesis not fit");
                } else {
                    // 若非空，把栈中元素依次弹出并输出到序列中，直到遇到第一个开括号为止，若没有遇到开括号，
                    // 也即发生错误，进行相关处理
                    stackTop = symbolStack.pop();
                    while (!symbolStack.isEmpty() && stackTop != '(') {
//                        System.out.print(" " + stackTop);
                        result = result + " " + stackTop;
                        stackTop = symbolStack.pop();
                    }
                    // 没有碰到开括号
                    if (symbolStack.isEmpty()) {
                        throw new RuntimeException("parenthesis not fit");
                    }
                    // 是'('也弹栈，但是不加入输出
                }
            } else if (isOperator(token)) {
                // 4）当输入的是运算符op（+、- 、×、/）时，有两种情况需要处理：

                //    a)执行循环，判断当（栈非空and栈顶不是开括号and栈顶运算符的优先级不低于输入的运算符的优先级）时，
                //    反复将栈顶元素弹出并添加到序列中

                while (!symbolStack.isEmpty()) {
                    stackTop = symbolStack.pop();
                    if (stackTop != '('
                            && getPrecedence(stackTop) >= getPrecedence(token)) {
//                        System.out.print(" " + stackTop);
                        result = result + " " + stackTop;
                    } else {
                        // 这里用stack.peek()可能更好
                        symbolStack.push(stackTop);
                        break;
                    }
                }
                //    b)否则把输入的运算符op压栈
                symbolStack.push(token);
            }
        }
        // 5）当中序表达式的符号序列全部读入后，若栈内仍有元素，把它们依次弹出并放到后序表达式序列尾部。
        // * 若弹出的元素遇到空括号，则说明不匹配，发生错误，并进行相关处理
        while (!symbolStack.isEmpty()) {
            stackTop = symbolStack.pop();
            if (stackTop == '(') {
                throw new RuntimeException("parenthesis not fit");
            }
//            System.out.print(" " + stackTop);
            result = result + " " + stackTop;
        }
        return result;
    }

    /**
     * 如果是 + - * / 返回true
     *
     * @param token
     * @return
     */
    private static boolean isOperator(char token) {
        if (token == '+' || token == '-' ||
                token == '*' || token == '/') {
            return true;
        }
        return false;
    }

    /**
     * 获得运算符优先级
     * + - ：优先级1
     * * / : 优先级2
     * 不考虑其他运算符
     *
     * @param op 运算符
     * @return
     */
    private static int getPrecedence(char op) {
        if (op == '+' || op == '-') {
            // + and -
            return 1;
        } else {
            // * and /
            return 2;
        }
    }

    public static void ex_1_3_11() {
        System.out.println(EvaluatePostfix());
    }

    /**
     * 从标准输入中得到一个后序表达式，求值并打印结果
     * （将上一题的程序中得到的输出用管道传递给这一段程序可以得到和
     * Evaluate相同的行为
     * 先限定到整数的计算
     *
     * @return 计算的结果
     */
    private static double EvaluatePostfix() {
        int result;
        Stack<Double> numberStack = new Stack<>();
//        Stack<Character> symbolStack = new Stack<>();

        String expr;
        double val1, val2;
        char symbol;
        System.out.println("input the Postfix");
        while (!StdIn.isEmpty()) {
            expr = StdIn.readString();
            // 只有两种情况
            // 数字或者符号 +, -, *, /
            // 这里就不判断别的了
            if (Character.isDigit(expr.charAt(0))) {
                // 是数字
                val1 = Double.valueOf(expr);
                numberStack.push(val1);
            } else {
                val2 = numberStack.pop();
                val1 = numberStack.pop();
                if (expr.equals("+")) {
                    // 这里也不做关于栈是否为空的判断了
                    // 是符号
                    numberStack.push(val1 + val2);
                } else if (expr.equals("-")) {
                    numberStack.push(val1 - val2);
                } else if (expr.equals("*")) {
                    numberStack.push(val1 * val2);
                } else if (expr.equals("/")) {
                    numberStack.push(val1 / val2);
                } else {
                    // 无关字符
                }
            }

        }
        return numberStack.pop();
    }

    /**
     * 编写一个可迭代的Stack用例，它含有一个静态的copy()方法，接受一个字符串的栈作为参数并返回该栈的一个副本
     * 注意：这种能力是迭代器价值的一个重要体现，因为有了它我们无须改变基本API就能实现这种功能
     */
    public static void ex_1_3_12() {

    }

    /**
     * 编写一个类ResizingArrayQueueOfStrings 使用定长数组实现队列的抽象，
     * 然后拓展实现，使用调整数组的方法突破大小的限制。
     */
    public static void ex_1_3_13() {
        ResizingArrayQueueOfStrings queueOfStrings = new ResizingArrayQueueOfStrings(2);
        queueOfStrings.enqueue("a");
        queueOfStrings.dequeue();
        queueOfStrings.enqueue("b");
        queueOfStrings.enqueue("c");
        queueOfStrings.enqueue("d");
        queueOfStrings.enqueue("e");
        queueOfStrings.enqueue("f");
        queueOfStrings.dequeue();
        queueOfStrings.dequeue();
        queueOfStrings.enqueue("g");
        queueOfStrings.dequeue();
        queueOfStrings.dequeue();
        queueOfStrings.dequeue();
    }

    /**
     * 编写一个Queue的用例，接受一个命令行参数k并打印出标准输入中的倒数第k个字符串
     * 假设标准输入中至少有k的字符串
     * 命令行参数就直接传入了
     *
     * @param k 要打印的位置
     */
    public static void ex_1_3_15(int k) {
        Queue<String> queue = new Queue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        System.out.println(queue.size());
        for (int i = 0; i <= queue.size() - k + 2; i++) {
            queue.dequeue();
        }
        System.out.println(queue.dequeue());
    }

    public static void main(String[] args) {
        ex_1_3_15(2);
    }
}

class Stack_ex_1_3_12<Item> implements Iterable<Item> {
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not support in myStack");
        }
    }

    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Node top = first;
        first = first.next;
        N--;
        return top.item;
    }

    public static Stack<String> copy(Stack<String> sour) {
        Stack<String> des = new Stack<>();
        for (String str : sour) {
            des.push(str);
        }
        return des;
    }
}

/**
 * 使用定长数组实现队列的的抽象
 * 然后使用调整数组的方法来突破大小的限制
 */
class ResizingArrayQueueOfStrings implements Iterable<String> {
    private int N;
    private String[] a;
    private int first;

    public ResizingArrayQueueOfStrings(int cap) {
        a = new String[cap];

    }

    // 添加一个元素
    public void enqueue(String str) {
        if (N == a.length) {
            resize(2 * N);
        }
        a[(first + N) % a.length] = str;
        N++;

        // DEBUG-USE
        print();
    }

    // 删除最早添加的元素
    public String dequeue() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("cannot dequeue from an empty queue");
        }
        String str = a[first];
        N--;
        first = (first + 1) % a.length;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }

        // DEBUG-USE
        print();

        return str;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        System.out.println("resize to " + max);
        String[] temp = new String[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[(first + i) % a.length];
        }
        a = temp;
        first = 0;// 对齐到0
    }

    @Override
    public Iterator<String> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<String> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public String next() {
            return a[i++];
        }
    }

    public void print() {

        if (isEmpty()) {
            System.out.print("[ empty ]");
        } else {
            int i;
            for (i = 0; i < N; i++) {
                System.out.print("[ " +
                        a[(first + i) % a.length] + " - " + (first + i) % a.length
                        + " ] ");
            }
            for (; i < a.length; i++) {
                System.out.print("[ empty"
                        + " - " + (first + i) % a.length
                        + " ] ");
            }
        }

        System.out.println();
    }
}