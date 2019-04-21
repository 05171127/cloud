package com.sunlong.self;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author shipp
 * @descript
 * @create 2019-04-02 15:17
 */
public class LambdaTest {

    @Test
    public void test(){
        //传统写法
        System.out.println(arithmetic(10, 20, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x+y;
            }
        }));
        //lambda写法
        System.out.println(arithmetic(10, 20, (x, y) -> x + y));
        System.out.println(arithmetic(10, 20, (x, y) -> x - y));
        System.out.println(arithmetic(10, 20, (x, y) -> x * y));
        System.out.println(arithmetic(10, 20, (x, y) -> x / y));
        System.out.println(arithmetic(10, 20, (x, y) -> (x + y) * (x -y)));
    }
    //四则运算
    public <T> T arithmetic(T t1, T t2, BinaryOperator<T> operator){
        return operator.apply(t1, t2);
    }



    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 10, 3, 5, 9, 12, 18, 30);
        //1、找出流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取流中的前两个元素，最后求出流中元素的总和

        int ret = list.stream()
                .filter(x -> x > 2)//找出流中大于2的元素
                .mapToInt(x -> x * 2)//将每个元素乘以2
                .skip(2)//忽略调流中前两个元素
                .limit(2)//取流中前两个元素
                .sum();//求和
        System.out.println(ret);
    }


    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(1, 10, 3, 5, 9, 12, 18, 30);
        //1、找出流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取流中的前两个元素，最后求出流中元素的总和

        //获取统计信息
        IntSummaryStatistics statistics = list.stream()
                .filter(x -> x > 2)
                .mapToInt(x -> x * 2)
                .skip(2)
                .limit(2)
                .summaryStatistics();

        /** 计算出最大，最小，平均等等，是不是很好用，赶紧get起来 **/
        System.out.println("the max:" + statistics.getMax());
        System.out.println("the min:" + statistics.getMin());
        System.out.println("the average:" + statistics.getAverage());
        System.out.println("the sum:" + statistics.getSum());
        System.out.println("the count:" + statistics.getCount());
    }


    //四大函数式接口
    /*
    接口	    参数	返回类型	描述
    Predicate	T	    boolean	    断言型接口
    Consumer	T	    void	    消费型接口
    Function	T	    R	        函数式接口
    Supplier	None	T	        供给型接口
    */
    /*
    默认方法
    Collection接口增加stream方法
    Iterable接口增加forEach
        default void forEach(Consumer<? super T> action) {
	        for (T t : this) {
		        action.accept(t);
	        }
        }
     */
    @Test
    public void test5(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.stream().iterator().forEachRemaining(x-> System.out.println(x));
    }


    //方法引用
    /*
        类名::静态方法名：(x, y) -> 类名.静态方法名(x, y)
        对象名::实例方法名：(x, y) -> 对象名.实例方法名(x, y)，如：System.out::println
        类名::实例方法名：(x) -> x.实例方法名()
        构造方法引用：类名::new，(x, y) -> new 构造方法(x, y)，如：Supplier<List<Object>> ret = ArrayList::new;
     */

    @Test
    public void test6(){
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream().map(x -> x.toUpperCase()).forEach(x -> System.out.println(x));
        list.stream().map(String::toUpperCase/*类名::实例方法名*/).forEach(System.out::println);
    }
}
