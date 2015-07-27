package me.suiyueyu.algs4.sec1.exercise;

import me.suiyueyu.algs4.sec1.ResizingArraysStack;
import stdlib.StdIn;

import java.util.Scanner;

/**
 * Created by yzcc on 2015/5/22.
 */
public class Ex1_3_4{
    public static void main(String[] args){
        ResizingArraysStack<String> symbol = (ResizingArraysStack<String>)new ResizingArraysStack<String>();
        Scanner stdin = new Scanner(System.in);

        while(stdin.hasNext()){
            String s = stdin.next();
            System.out.println(s+'\t');
            if ( ( s.equals("(") ) ||
                    ( s.equals("[") ) ||
                    ( s.equals("{") )
                    ){
                symbol.push(s);
            }
            else if ( ( s.equals(")") ) ||
                    ( s.equals("]") ) ||
                    ( s.equals("}") )
                    ){
                String ops = symbol.pop();
                if ( (s.equals(')') && ops.equals('('))){

                }else if( (s.equals(']') && ops.equals('[')) ){

                }
                else if(s.equals('}') && ops.equals('{')){
                    System.out.println("符号不匹配");
                }
            }
        }
        if (symbol.isEmpty()){
            System.out.println("匹配成功");
        }
        else{
            System.out.println("匹配失败");
        }


    }

}
