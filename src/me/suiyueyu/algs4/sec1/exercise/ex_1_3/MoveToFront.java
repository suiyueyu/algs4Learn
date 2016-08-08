package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/8.
 * 前移编码。从标准输入中读取一串字符，使用链表保存这些字符并清除重复字符。
 * 当你读取了一个从未见过的字符时，将它插入表头。
 * 当你读取了一个重复的字符时，将它从链表中删除并再次插入表头。
 * 将你的程序命名为ModeToFront；
 * 它实现了著名的前移编码策略，这种侧路假设最近访问过的元素很可能会再次访问，因此可以用于缓存
 * 数据压缩等许多场景
 */
public class MoveToFront {
    private class Node {
        char item;
        Node next;
        Node prev;

        /**
         * 这里加构造也是防止忘记new Node()后设置属性
         *
         * @param item
         * @param prev
         * @param next
         */
        public Node(char item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node first;
    private int N;

    /**
     * 这些api都是套路啊...
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 插入头
     *
     * @param item
     */
    public void insertHead(char item) {
        if (isEmpty()) {
            first = new Node(item, null, null);

            N++;
        } else {
            Node oldFirst = first;
            first = new Node(item, null, oldFirst);

            oldFirst.prev = first;

            N++;
        }
    }

    /**
     * 查找item的位置，并返回这个节点
     * 如果没有，则返回null
     *
     * @param item
     * @return
     */
    public Node find(char item) {
        if (isEmpty()) {
            return null;
        } else {
            Node curr = first;
            for (; curr != null; curr = curr.next) {
                // 这里first不能为空
                if (curr.item == item) {
                    return curr;
                }
            }
            // 没找到，curr=null
            return curr;
        }

    }

    /**
     * 从链表中删除item并返回item
     *
     * @param item
     * @return item
     */
    public char delete(char item) {
        Node delNode = find(item);
        if (delNode == null) {
            // 没找到不删
            // 空链表这里也找不到，不删
        } else {
            Node delNodePrev = delNode.prev;
            Node delNodeNext = delNode.next;

            // 首节点
            if (delNodePrev == null) {
                first = first.next;
                if (delNodeNext == null) {
                    // 单节点
                    // first = null
                } else {
                    delNodeNext.prev = first;
                }
            } else {
                // 中间节点 + 尾巴
                delNodePrev.next = delNodeNext;

                // 尾巴
                if (delNodeNext == null) {

                } else {
                    delNodeNext.prev = delNodePrev;
                }
            }
            N--;
        }
        return item;
    }

    public void add(char item) {
        if (find(item) == null) {
            // 第一次插入
//            insertHead(item);
        } else {
            delete(item);
        }
        insertHead(item);
    }

    public String toString() {
        String str = "";
        if (isEmpty()) {
            str = "[ empty ]";
            return str;
        } else {
            Node curr = first;
            for (int i = 0; i < N; i++) {
//            for (; curr != null; ){
                str = str + " [ " + curr.item + " ] ";
                curr = curr.next;
            }
//            str += '\n';
        }
        return str;
    }

    public static void main(String[] args) {
        MoveToFront moveToFront = new MoveToFront();
        moveToFront.add('a');
//        moveToFront.add('b');
//        moveToFront.add('c');
//        moveToFront.add('a');
//        moveToFront.add('b');
        moveToFront.add('a');
        moveToFront.add('b');
        moveToFront.add('a');

        System.out.println(moveToFront);
    }


}
