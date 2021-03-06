package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/5.
 * 1.3.31 实现一个嵌套类DoubleNode 来构造双向链表，其中每个节点都含有一个指向前驱元素的引用，
 * 和一项指向后续元素的引用（如果不存在则为null）。
 * 为以下任务实现若干静态方法：
 * 在表头插入节点，
 * 在表尾插入节点
 * 从表头删除节点，
 * 从表尾删除节点
 * 在指定节点之前插入新节点，
 * 在指定节点之后插入新节点,
 * 删除指定节点
 */
public class DoubleNodeList<Item> {
    private class DoubleNode<Item> {
        Item item;
        DoubleNode prev;
        DoubleNode next;
    }

    public DoubleNode first;
    public int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    // 在表头插入节点，
    public void insertHead(Item item) {

        if (isEmpty()) {
            first = new DoubleNode();
            first.item = item;
            first.prev = null;
            first.next = null;

        } else {
            //  oldfirst <-> next
            DoubleNode oldFirst = first;

            // newfirst -> oldfirst <-> next
            first = new DoubleNode();
            first.prev = null;
            first.item = item;
            first.next = oldFirst;

            // <- newfirst <--> oldfirst <--> next
            oldFirst.prev = first;
        }
        N++;
    }

    /**
     * 在表尾插入节点
     *
     * @param item
     */
    public void insertTail(Item item) {
        if (isEmpty()) {
            first = new DoubleNode();
            first.item = item;
            first.prev = null;
            first.next = null;
        } else {
            DoubleNode curr = first;

            for (int i = 0; i < size() - 1; i++) {
                curr = curr.next;
            }
            // 在这个位置后面加
            curr.next = new DoubleNode();
            DoubleNode newNode = curr.next;
            newNode.prev = curr;
            newNode.item = item;
            newNode.next = null;
        }
        N++;
    }

    // * 从表头删除节点，
    // * 从表尾删除节点

    /**
     * 删除头节点
     */
    public void deleteHead() {
        // 不是空链表
        if (!isEmpty()) {
            first = first.next;
            // 不是单节点链表
            if (size() != 1) {
                first.prev = null;
            }
            N--;
        }
    }

    public void deleteTail() {
        // 不是空链表
        if (!isEmpty()) {
            DoubleNode curr = first;

            if (size() == 1) {
                first = null;
            } else {
                // 不止一个节点

                // 倒数第二个节点
                // curr -> last -> null
                for (int i = 0; i < size() - 2; i++) {
                    curr = curr.next;
                }
                curr.next = null;
            }
            N--;
        }
    }

    /*
     * 在指定节点之前插入新节点，
     * 在指定节点之后插入新节点,
     * 删除指定节点
     */

    /**
     * 跳到链表的第pos+1个节点
     * 即 List[pos]
     * 从0开始
     *
     * @param pos 下标
     * @return 对应下标的节点，如果跳不到，则返回null
     */
    private DoubleNode jumpTo(int pos) {
        if (pos > size()) {
            return null;
        } else {
            DoubleNode curr = first;
            for (int i = 0; i < pos; i++) {
                curr = curr.next;
            }
            return curr;
        }
    }

    /**
     * 在指定节点之前插入新节点，
     * 空链表加一个：insertBefore(sth, 1);
     * @param position 第n个节点
     */
    public void insertBefore(Item item, int position) {
        if (position <= 0 || position > size()) {
            // do nothing
            throw new UnsupportedOperationException("the position is overflow the list");
        } else {
            if (position == 1) {
                insertHead(item);
            } else {
                // prev不是null
                DoubleNode node = jumpTo(position - 1);// 跳到这个节点，现在有prev注意
                DoubleNode prev_node = node.prev;
                DoubleNode newNode = new DoubleNode();
                newNode.item = item;
                prev_node.next = newNode;
                newNode.prev = prev_node;
                newNode.next = node;
                node.prev = newNode;

                N++;
            }

        }
    }

    /**
     * 在position后插入一个元素
     *
     * @param item
     * @param position 第position个元素，0 < position <=size()
     */
    public void insertAfter(Item item, int position) {
        if (position > size() || position <= 0) {
            // do-nothing
            throw new UnsupportedOperationException("the position is illegal ");
        } else {
            if (isEmpty()) {
                // 这个时候只能position = 1
                insertHead(item);
            } else {
                DoubleNode curr = jumpTo(position - 1);
                // insert After this node
                DoubleNode curr_next = curr.next;
                curr.next = new DoubleNode();
                curr.next.item = item;
                curr.next.prev = curr;
                curr.next.next = curr_next;
                if (position != size()) {
                    curr_next.prev = curr.next;

                } else {
                    //尾巴插入，null 没有prev
                }
                N++;
            }
        }
    }

    /**
     * 删除指定位置的节点，第position位置的节点
     *
     * @param position
     */
    public void remove(int position) {
        if (position <= 0 || position > size()) {
            // do nothing
            // 如果是空链表，那么一定进这里
            throw new UnsupportedOperationException("the position is overflow");
        } else {
            if (position == 1) {
                deleteHead();
            } else if (position == size()) {
                deleteTail();
            } else {
                // 被删除点
                DoubleNode node = jumpTo(position - 1);
                DoubleNode node_parent = node.prev;
                DoubleNode node_son = node.next;
                node_parent.next = node_son;
                node_son.prev = node_parent;
                N--;
            }
        }

    }

    public void print() {
        DoubleNode curr = first;
        if (isEmpty()) {
            System.out.print("[ empty ]");
        }
        for (int i = 0; i < size(); i++, curr = curr.next) {
            System.out.print(" [ " + curr.item + " ] ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        DoubleNodeList<String> doubleNodeList = new DoubleNodeList<>();
        doubleNodeList.insertTail("a");
        doubleNodeList.insertTail("b");
        doubleNodeList.insertTail("c");
        doubleNodeList.print();
        doubleNodeList.remove(3);
        doubleNodeList.print();
        doubleNodeList.remove(2);
        doubleNodeList.print();
        doubleNodeList.remove(1);
        doubleNodeList.print();
        doubleNodeList.remove(1);

    }
}