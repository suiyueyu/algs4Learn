package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/8.
 * 1.3.44 文件编辑器的缓冲区。为文本编辑器的缓冲区设计一种数据结构并实现如下的API
 * <p>
 * public class Buffer
 * <p>
 * Buffer 创建一块空缓冲区
 * void insert(char c)  在光标位置插入字符c
 * char delete() 删除并返回光标位置的字符
 * void left(int k) 将光标向左移动k个位置
 * void right(int k) 将光标向右移动k个位置
 * int size() 缓冲区中的字符数量
 * <p>
 * 提示：使用两个栈
 */
public class Buffer {
    private Stack<Character> leftStack = new Stack<>();
    private Stack<Character> rightStack = new Stack<>();
    private int position;

    public void insert(char c) {
        leftStack.push(c);
        position++;
    }

    public char delete() {
        char c = leftStack.pop();
        position--;
        return c;
    }

    //    * void left(int k) 将光标向左移动k个位置
//    * void right(int k) 将光标向右移动k个位置
//    * int size() 缓冲区中的字符数量
    public void left(int k) {
        for (int i = 0; i < k; i++) {
            rightStack.push(leftStack.pop());
        }
        position -= k;
    }

    public void right(int k) {
        for (int i = 0; i < k; i++) {
            leftStack.push(rightStack.pop());
        }
        position += k;
    }

    public int size() {
        return leftStack.size() + rightStack.size();
    }

    public String toString() {
        Deque<Character> all = new Deque<>();
        all.pushLeft('$');
        for (char c : leftStack) {
            all.pushLeft(c);
        }
        for (char c : rightStack) {
            all.pushRight(c);
        }
        String str = "";
        for (char c : all) {
            str += c;
        }
        return str;

    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        buffer.insert('a');
        buffer.insert('b');
        buffer.insert('d');
        buffer.insert('e');
        buffer.insert('f');
        buffer.left(2);
        buffer.insert('g');

        System.out.println(buffer);


    }
}
