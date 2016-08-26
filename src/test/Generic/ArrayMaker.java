package test.Generic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/19.
 */
public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    @Override
    public String toString() {
        return "the kind is " + kind;
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<String>(String.class);
        String[] stringArray = stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));

        System.out.println(stringMaker);
    }
}
