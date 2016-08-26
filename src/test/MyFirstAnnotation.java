package test;

/**
 * Created by yzcc on 2016/8/19.
 */
public @interface MyFirstAnnotation {
    public int i = 3;

    String noManSky() default "123";
}
