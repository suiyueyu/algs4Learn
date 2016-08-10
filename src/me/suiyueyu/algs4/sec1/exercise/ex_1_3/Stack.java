package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by yzcc on 2016/8/8.
 * 1.3.42 复制栈。编写一个新的构造函数，使用以下代码：
 * <pre>
 *     Stack<Item> t = new Stack<Item>(s)
 * </pre>
 * 得到的t向栈s的一个新的独立副本。
 *
 * 1.3.47 可连接的队列，栈或steque。 为队列，栈或者steque(请见练习1.3.22 )添加一个能够(破坏性的)
 * 连接两个同类对象的额外操作 catenation
 *
 * 1.3.50 快速出错的迭代器。修改Stack的迭代器代码，确保一旦用例在迭代器中(通过push()或pop())操作修改集合数据就抛出一个
 * java.util.ConcurrentModificationException 的异常。
 * 解答：用一个结束期记录push()和pop()操作的次数。在创建迭代器时，将该值记录到Iterator的一个实例变量中。
 * 在每次调用hasNext() 和 next()之前，检查该值是否发生了变化，如果变化则抛出异常
 *
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    // 1.3.50 pushAndpopCounter
    private int pushAndpopCounter;

    public class Node {
        Item item;
        Node next;
    }

    public Stack() {
        // 默认的，啥也不干
    }

    public Stack(Stack<Item> q) {
        Stack<Item> stack = new Stack<Item>();
        for (Item item : q) {
            stack.push(item);
        }
        for (Item item : stack) {
            this.push(item);

            pushAndpopCounter++;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // 从栈顶添加元素
        Node oldItem = first;
        first = new Node();
        first.item = item;
        first.next = oldItem;
        N++;

        pushAndpopCounter++;
    }

    public Item pop() {
        // 从栈顶删除元素
        Item item = first.item;
        first = first.next;
        N--;

        pushAndpopCounter++;

        return item;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o.getClass() == this.getClass()) {
            Stack<Item> that = (Stack<Item>) o;
            if (isEmpty() && that.isEmpty()) {
                // 两个都是空的
                return true;
            } else if (this.size() == that.size()) {
                // 两个都不是空的，对比每个元素
                Node curr1 = this.first;
                Node curr2 = that.first;

                for (; curr1 != null && curr2 != null; curr1 = curr1.next,
                        curr2 = curr2.next) {
                    if (!curr1.item.equals(curr2.item)) {
                        return false;
                    }
                }
                return true;
            } else {
                // 大小不等
                return false;
            }
        } else {
            // 两个类型不同
            return false;
        }
    }

    /**
     * 1.3.47 可连接的队列，栈或steque。 为队列，栈或者steque(请见练习1.3.22 )添加一个能够(破坏性的)
     * 连接两个同类对象的额外操作 catenation
     *
     * @param s
     */
    public void catenation(Stack<Item> s) {
        Stack<Item> tmp = new Stack<Item>();
        for (Item item : s) {
            tmp.push(item);
        }
        for (Item item : tmp) {
            this.push(item);

            pushAndpopCounter++;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;
        private int expectedPushAndPopCounter = pushAndpopCounter;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!checkPushAndPopCounter()) {
                throw new ConcurrentModificationException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        /**
         * Fail-Fast(快速失败)机制
         *
         * @return
         * @see <a href="http://blog.csdn.net/lcore/article/details/12083951">Fail-Fast</a>
         */
        private boolean checkPushAndPopCounter() {
            return expectedPushAndPopCounter == pushAndpopCounter;
        }
    }

    // ex 1.3.7
    public Item peek() {
        if (isEmpty()) return null;
        return first.item;
    }

    public static void main(String[] args) {
        Stack<String> stack1 = new Stack<>();
        stack1.push("1");
        stack1.push("2");
        stack1.push("3");

        Stack<String> stack2 = new Stack<>(stack1);

        System.out.println(stack1.equals(stack2));

        stack2.push("4");

        System.out.println(stack1.equals(stack2));

        stack1.catenation(stack2);

        for (String str : stack1) {
            System.out.print(str + " ");
        }
        System.out.println();

        // check for new Iterator
        Iterator<String> iterator = stack1.iterator();
        while (iterator.hasNext()) {
            stack1.push("1");
            System.out.println(iterator.next());
        }



    }
}
