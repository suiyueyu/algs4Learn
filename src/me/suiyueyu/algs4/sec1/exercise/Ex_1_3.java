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

    /**
     * 1.3.19 给出一段代码，删除链表的尾节点，其中链表的首节点为first
     * 1.3.20 编写一个方法delete()，接受一个int参数k，删除链表的第k个元素(如果它存在的话)
     * 1.3.21 编写一个方法find()， 接受一个链表和一个字符串作为参数。
     * 如果链表中某个节点的item的值是key，返回true，否则返回false
     */
    public static void ex_1_3_20() {
        ExList<String> list = new ExList<>();
        list.addToHead("a");
        list.addToHead("b");
        list.addToHead("c");

        list.print();
//        list.deleteLast();
        System.out.println(list.delete(3));
        System.out.println(list.delete(1));
        System.out.println(list.delete(2));

        list.print();

    }

    /**
     * 1.3.26 删除链表里面所有item是key的节点
     */
    public static void ex_1_3_26() {
        ExList<String> list = new ExList<>();
        list.addToHead("s");
        list.addToHead("c");
        list.addToHead("s");
        list.addToHead("b");
        list.addToHead("a");
        list.addToHead("s");
        list.addToHead("s");
        list.addToHead("s");
        list.remove("s");

        list.print();
    }

    /**
     * 1.3.27
     * 接收一条链表的首节点作为参数，返回链表中最大的节点的值。
     * 假设所有键均为正整数，如果链表为空则返回0.
     * 1.3.28
     */
    public static void ex_1_3_27() {

    }

    /**
     * 接收一条链表的首节点作为参数，返回链表中最大的节点的值。
     * 假设所有键均为正整数，如果链表为空则返回0.
     *
     * @return
     * @warning 所有键都得是正整数
     */
    public int max(IntegerNode first) {
        if (first == null) {
            return 0;
        } else {
            int maxVal = first.val;
            IntegerNode curr = first.next;
            for (; curr != null; curr = curr.next) {
                if (curr.val > maxVal) {
                    maxVal = curr.val;
                }
            }
            return maxVal;
        }
    }

    /**
     * 1.3.28 用递归的方法解答上一道练习
     *
     * @param curr 当前节点
     * @return 当前节点为头的链表的最大值
     */
    public int max_RecursionVersion(IntegerNode curr) {
        if (curr == null) {
            return 0;
        }
        return Math.max(curr.val, max_RecursionVersion(curr.next));
    }

    private class IntegerNode {
        int val;
        IntegerNode next;
    }
    public static void main(String[] args) {
        ex_1_3_26();
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

class ExList<Item> implements Iterable<Item> {


    private class Node {
        Item item;
        Node next;

        public String toString() {
            return item + "";
        }
    }

    private Node first;

    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addToHead(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        N++;
    }

    public void deleteHead() {
        first = first.next;
        N--;
    }

    public void print() {
        System.out.println("the N is " + N);
        for (Node node = first; node != null; node = node.next) {
            System.out.print(node.item + " -> ");
        }
        if (isEmpty()) {
            System.out.print("[ empty ]");
        }
        System.out.println();
    }

    /**
     * 1.3.19 删除链表的尾节点
     */
    public void deleteLast() {
        if (first != null) {
            if (first.next == null) {
                // 单节点
                first = null;
            } else {
                Node curr = first.next;
                Node curr_parent = first;

                for (; curr.next != null;
                     curr = curr.next, curr_parent = curr_parent.next) {
                }
                // curr.next = null
                // curr是最后一个节点
                // curr_parent是它之前的那个节点
                curr_parent.next = null;
                curr = null;// 置空
            }
            N--;
        }

    }

    /**
     * 1.3.20 编写一个方法，delete()，接受一个int参数k
     * 删除链表的第k个节点
     * 如果它存在的话
     *
     * @param k 删除的位置
     * @return 删除是否成功
     */
    public boolean delete(int k) {
        if (k < 0) {
            return false;
        }
        if (k == 1) {
            deleteHead();
            return true;
        }
        Node curr = jumpTo(k - 1 - 1);
        if (curr == null || curr.next == null) {
            return false;//删除失败
        }
        Node tmp = curr.next;
        curr.next = curr.next.next;
        tmp = null;
        N--;
        return true;
    }

    /**
     * 去链表的第k+1个位置
     * list[k]
     *
     * @param k
     * @return 跳的到就返回节点，跳不到就返回null
     */
    public Node jumpTo(int k) {
        Node curr = first;
        if (k >= N) {
            return null;
        }
        for (int i = 0; i < k && curr != null; i++) {
            curr = curr.next;
        }
//            if (curr == null) return null;
        return curr;
    }

    /**
     * 1.3.21 编写一个方法find(), 接收一条链表和一个字符串key作为参数
     * 如果链表中的某个节点的item域的值为key，则方法返回true，否则返回false
     *
     * @param key
     * @return
     */
    public boolean find(String key) {
        if (!isEmpty()) {
            Node node = first;
            for (; node != null; node = node.next) {
                if (node.item.equals(key)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * 1.3.24 编写一个方法removeAfter 接受一个链表节点作为参数
     * 并删除该节点的后续结点
     * 如果参数节点或参数节点的后续结点为空则什么也不做
     *
     * @param node 节点
     */
    public void removeAfter(Node node) {
        if (node == null || node.next == null) {
            // 什么都不做
        } else {
            Node next = node.next;
            node.next = next.next;
            next = null;
            N--;
        }
    }

    /**
     * 1.2.25接受两个链表节点作为参数，将第二个节点插入链表，
     * 并使之成为第一个节点的后续结点（如果两个参数为空则什么都不做）。
     *
     * @param node
     * @param insertNode
     */
    public void insertAfter(Node node, Node insertNode) {
        if (node == null || insertNode == null) {
            // do nothing
        } else {
            insertNode.next = node.next;
            node.next = insertNode;
            N++;
        }

    }

    /**
     * 1.3.26 编写一个方法remove(), 接收一条链表和一个字符串key作为参数
     * 将第二个节点插入链表并使之成为第一个节点的后续结点。
     * (如果两个参数为空则什么也不做)
     * <p>
     * 这里为了方便没做成static的函数，下一组练习把class全部拿出来
     *
     * @param key
     */
    public void remove(String key) {
        if (key == null) {

        } else {
            if (!isEmpty()) {

                while (first.item.equals(key)) {
                    first = first.next;
                    N--;
                    // the first needs to be del
                }
                Node curr = first.next;
                Node curr_parent = first;

                for (; curr != null; ) {
                    if (curr.item.equals(key)) {
                        // del curr
                        curr_parent.next = curr.next;
                        curr = curr_parent.next;
                        N--;

                    } else {
                        curr = curr.next;
                        curr_parent = curr_parent.next;
                    }
//                    curr = curr.next, curr_parent = curr_parent.next
                }
            } else {
                // the list is empty
            }
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node curr = first;

        @Override
        public boolean hasNext() {
            return curr == null;
        }

        @Override
        public Item next() {
            Item item = curr.item;
            curr = curr.next;
            return item;
        }
    }
}

