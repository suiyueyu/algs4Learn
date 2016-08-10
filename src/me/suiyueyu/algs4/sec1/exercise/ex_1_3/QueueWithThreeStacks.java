package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

/**
 * Created by yzcc on 2016/8/9.
 * 1.3.49 栈和队列。用有限个栈实现一个队列，保证每个队列操作(在最坏情况下)都只需要常数次的
 * 栈操作。 警告：非常难
 * <p>
 * 1.3.49 Queue with three stacks. Implement a queue with three stacks so that each
 * queue operation takes a constant (worst-case) number of stack operations. Warning :
 * high degree of difficulty.
 * <p>
 * 这个中文和英文的意思不太一样吧...
 * 找了下，看样子中文的书是对的，英文上已经换成 a constant number of
 * <p>
 * <a href = "http://stackoverflow.com/questions/5538192/how-to-implement-a-queue-with-three-stacks">
 * 参考链接 from stackoverflow
 * </a>
 * SUMMARY
 * <ol>
 * <li>O(1) algorithm is known for 6 stacks</li>
 * <li>
 * O(1) algorithm is known for 3 stacks, but using lazy evaluation which
 * in practice corresponds to having extra internal data structures,
 * so it does not constitute a solution
 * </li>
 * <li>
 * People near Sedgewick have confirmed they are not aware of a 3-stack
 * solution within all the constraints of the original question
 * </li>
 * <ol/>
 * <p>
 * <p>a solution by flolo</p>
 * <pre>
 *     queue.new() : Stack1 = Stack.new(<Stack>);
 *                   Stack2 = Stack1;
 *
 *     enqueue(element): Stack3 = Stack.new(<TypeOf(element)>);
 *                       Stack3.push(element);
 *                       Stack2.push(Stack3);
 *                       Stack3 = Stack.new(<Stack>);
 *                       Stack2.push(Stack3);
 *                       Stack2 = Stack3;
 *
 *     dequeue():    Stack3 = Stack1.pop();
 *                   Stack1 = Stack1.pop();
 *                   dequeue() = Stack1.pop() // 这里是说result = Stack1.pop(), 最后再return result;
 *                   Stack1 = Stack3;
 *
 *     isEmtpy():    Stack1.isEmpty();
 * </pre>
 * <p>
 * 上述代码能够构成如下结构(by flolo)
 * <pre>
 *      | | | |3| | | |
 *      | | | |_| | | |
 *      | | |_____| | |
 *      | |         | |
 *      | |   |2|   | |
 *      | |   |_|   | |
 *      | |_________| |
 *      |             |
 *      |     |1|     |
 *      |     |_|     |
 *      |_____________|
 * </pre>
 * 解释一下，就是说，构成了一个嵌套的栈，类似于链表
 * class NestStack<Item>{
 * Stack<Item> value; // 这个在栈底
 * NestStack< Stack<Item> > next; // 这个在栈顶
 * }
 * 例如上图最外层就是
 * <pre>
 *     [ 第一层, <- stack1 指向这里，最外层的NestStack
 *         [1], [ 第二层
 *                 [2], [ 第三层
 *                          [3],
 *                           [] <-  stack2 指向最后这个空的NestStack
 *                      ]
 *             ]
 *     ]
 *     stack3 是一个temp的stack
 * </pre>
 * <p>
 * 每次加入都会在stack2构建一个
 * <pre>
 *     [
 *        [3],
 *        [] <-  stack2 指向最后这个空的NestStack
 *     ]
 * </pre>
 * 然后stack2指向内部那个空的stack< Stack<Item> >
 * <p>
 * dequeue():    Stack3 = Stack1.pop();
 * Stack1 = Stack1.pop();
 * dequeue() = Stack1.pop() // 这里是说result = Stack1.pop(), 最后再return result;
 * Stack1 = Stack3;
 * 而dequeue的时候，
 * stack3 = [2, ...] // Stack3 = Stack1.pop();
 * stack1 = [1]      // Stack1 = Stack1.pop();
 * result = 1        // dequeue() = Stack1.pop()
 * stack1 = [2, ...] // Stack1 = Stack3;
 * <p>
 * <p>
 * <a href = "http://wizmann.tk/implement-queue-with-stacks.html">一个中国人的解释</a>
 * 他解释的图如下
 * <pre>
 *     [1,
 *         [2,
 *             [3]
 *         ]
 *     ],
 * </pre>
 * 我的图的思路来自于这张图，不过注意到，他这里 1 (Item) 和 [2, ...](Stack<Item > )并列
 * 但其实对于Stack中放的都是一种类型的话，我把[]加上以示区别，要不然就泛型数组了
 */
public class QueueWithThreeStacks<Item> {
    // 下面这个声明
    private class NestStack {
        private Stack<Item> stackBottom;
        private Stack<NestStack> stackTop;

        // 这样是嵌套的声明了，不过push的参数怎么传呢?
        // 是Stack<Item> 还是 Stack<NestStack>?
        // 最后全用Object么？
        public void push() {

        }

        public void pop() {

        }
    }


    private Stack<Stack<Item>> stack1;
    private Stack<Stack<Item>> stack2;
//    private Stack<Stack<Item> > stack3;

    public QueueWithThreeStacks() {
        stack1 = new Stack<Stack<Item>>();
        stack2 = stack1;
    }

    public void enqueue(Item item) {

        // 这里他的stack是弱类型的，先生成了一个Stack<Item>

        // 然后又把Stack指向了一个 Stack<Stack<Item > >
//             enqueue(element): Stack3 = Stack.new(<TypeOf(element)>);
//                               Stack3.push(element);
        // 这里stack3 是一个 Stack<Item >
//                               Stack2.push(Stack3);
        // Stack2是一个 Stack<Stack<Item > >
//                               Stack3 = Stack.new(<Stack>);
        // 这里Stack3 是一个 Stack<Stack<Item > > 放了Stack<Item> 的Stack
//                               Stack2.push(Stack3);
        // 这里Stack2 又是一个 Stack < Stack<Stack<Item > >> 又多了一层
//                               Stack2 = Stack3;
        // 我不知道怎么声明一个列表(栈也一样)
        // 表示为 [
        //          [1], 包含了一个Item的列表
        //          [2, []] 除了Item以外, 还有一个列表 的列表
        //    ]
        // 用py之类的实现就很简单
        // todo 用java怎么声明?

    }
}
