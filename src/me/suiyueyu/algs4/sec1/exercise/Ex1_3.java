package me.suiyueyu.algs4.sec1.exercise;

import me.suiyueyu.algs4.sec1.ResizingArraysStack;
import java.util.Scanner;

/**
 * Created by yzcc on 2015/6/11.
 * 将中缀表达式转换为后缀表达式：
 与转换为前缀表达式相似，遵循以下步骤：
 (1) 初始化两个栈：运算符栈S1和储存中间结果的栈S2；
 (2) 从左至右扫描中缀表达式；
 (3) 遇到操作数时，将其压入S2；
 (4) 遇到运算符时，比较其与S1栈顶运算符的优先级：
     (4-1) 如果S1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     (4-2) 否则，若优先级比栈顶运算符的高，也将运算符压入S1（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
     (4-3) 否则，将S1栈顶的运算符弹出并压入到S2中，再次转到(4-1)与S1中新的栈顶运算符相比较；
 (5) 遇到括号时：
     (5-1) 如果是左括号“(”，则直接压入S1；
     (5-2) 如果是右括号“)”，则依次弹出S1栈顶的运算符，并压入S2，直到遇到左括号为止，此时将这一对括号丢弃；
 (6) 重复步骤(2)至(5)，直到表达式的最右边；
 (7) 将S1中剩余的运算符依次弹出并压入S2；
 (8) 依次弹出S2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式（转换为前缀表达式时不用逆序）。

 */
public class Ex1_3 {
    /* isAlpha 判断String内容是不是全是字母
     * @param name 输入String
     * @return boolean 判断结果
     * 只能判断是不是字母，运算符 + -什么的都认为不是\
     * 数字的返回也是false
     */

    public static boolean myIsAlpha(String name){
        char[] chars = name.toCharArray();
        for (char c : chars){
            if (!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean myIsNumber(String name){
        char[] chars = name.toCharArray();
        for (char c : chars){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean myIsSymbol(String name){
        char[] chars = name.toCharArray();

        if ((chars.length > 1) || (chars.length == 0)){
            return false;
        }
        else if(chars[0] == '+' ||
                chars[0] == '-' ||
                chars[0] == '*' ||
                chars[0] == '/'){
            return true;

        }
        return false;
    }

    /**
     * getSymbolPriority 获取运算符a的优先级
     * 注意，这里并不完整，只包含了加减法里面用的几个
     * @param a 运算符
     * @return int priority 优先级 从1~9 节选，并不完整。其余情况返回0
     */
    public static int getSymbolPriority(char a){
        if ( a == '(' ||
             a == ')'
                ){
            return 1;
        }
        else if (a == '*' ||
                 a == '/' ||
                 a == '%'
                ){
            return 3;
        }
        else if (a == '+'||
                 a == '-' )
        {
            return 4;
        }
        return 0;
    }

    /**
     * comparePriority 比较a和b的优先级
     * @param a 运算符a
     * @param b 运算符b
     * @return int -1代表a<b,0代表a==b,1代表a>b
     */
    public static int comparePriorityLevel(char a,char b){
        int pro_A = getSymbolPriority(a);
        int pro_B = getSymbolPriority(b);

        if (pro_A < pro_B){
            return -1;
        }else if (pro_A == pro_B){
            return 0;
        }else{
            return 1;
        }
    }

    public static void inFixToPostFix() {
        ResizingArraysStack<String> S1 = (ResizingArraysStack<String>) new ResizingArraysStack<String>();
        ResizingArraysStack<String> S2 = (ResizingArraysStack<String>) new ResizingArraysStack<String>();

        Scanner stdin = new Scanner(System.in);

        while (stdin.hasNext()) {
            System.out.println("S1 :  " + S1.toString());
            System.out.println("S2 :  " + S2.toString());

            String s = stdin.next();

            if (myIsNumber(s)) {
                S2.push(s);
            } else if (myIsSymbol(s)) {
                while (true) {
                    if (S1.isEmpty()){
                        S1.push(s);
                        break;
                    }else{
                        String S1TopString = S1.pop();
                        S1.push(S1TopString);
                        char S1Top = S1TopString.toCharArray()[0];
                        char symbolC = s.toCharArray()[0];

                        if (S1Top == '(') {
                            S1.push(s);
                            break;
                        } else if (comparePriorityLevel(symbolC, S1Top) == 1) {
                            S1.push(s);
                            break;
                        } else {
                            S1TopString = S1.pop();
                            S2.push(S1TopString);
                        }
                    }
                }
            } else if (s.equals("(")) {
                S1.push(s);
            } else if (s.equals(")")) {
                String S1topC = S1.pop();

                while (!S1topC.equals("(")) {
                    S2.push(S1topC);

                    S1topC = S1.pop();
                }
            } else {
                System.out.println("error");
            }
        }
        while(!S1.isEmpty()){
            S2.push(S1.pop());
        }
        System.out.println("------------------");
        while(!S2.isEmpty()){
            System.out.println(S2.pop());
        }
    }

    /*
     后缀表达式的计算机求值：
     与前缀表达式类似，只是顺序是从左至右：
     从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
     用运算符对它们做相应的计算（次顶元素 op 栈顶元素），并将结果入栈；
     重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
     */
    public static void evaluatePostfix(){
        ResizingArraysStack<String> stack = (ResizingArraysStack<String>)new ResizingArraysStack<String>();
        Scanner stdin = new Scanner(System.in);

        while (stdin.hasNext()){
            String s = stdin.next();
            System.out.println(s+'\t');

            if(myIsNumber(s)){
                stack.push(s);
            }else if(myIsSymbol(s)){
                String top1 = stack.pop();
                String top2 = stack.pop();

                double doubleTop1 = Double.parseDouble(top1);
                double doubleTop2 = Double.parseDouble(top2);
                double result = 0.0;

                if (s.equals("+")){
                    result = doubleTop2 + doubleTop1;
                }else if (s.equals("-")){
                    result = doubleTop2 - doubleTop1;
                }else if (s.equals("*")){
                    result = doubleTop2 * doubleTop1;
                }else if (s.equals("/")){
                    result = doubleTop2 / doubleTop1;
                }else{
                    System.out.println("error! symbol is : " + s);
                }
                stack.push(result+"");
            }else {
                System.out.println("error! symbol is : " + s);
            }

        }
        double result = Double.parseDouble(stack.pop());
        System.out.println("result is : " + result);
    }

    public static void main(String[] args){
        evaluatePostfix();
    }
}
