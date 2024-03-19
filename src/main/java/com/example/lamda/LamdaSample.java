package com.example.lamda;

import java.util.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class LamdaSample {
        public static void main(String[] args) {

            Supplier<Integer> s = () -> (int)(Math.random()*100) + 1; // 파라미터가 없고 Return 이 있음 -> get
            Consumer<Integer> c = i -> System.out.println(i +","); // return 이 없음  -> accept
            Predicate<Integer> p = i -> i % 2 == 0; // 보통 조건문 -> apply
            Function<Integer, Integer> f = i -> i / 10 * 10;  //  일반적으로 가장 많이씀 -> test

            List<Integer> list = new ArrayList<>();
            makeRandomList(s, list);
            System.out.println(list);


            printEventNum(p, c, list);
            List<Integer> newList = doSomething(f, list);
            System.out.println(newList);

        }

    private static List<Integer> doSomething(Function<Integer, Integer> f, List<Integer> list) {
            List<Integer> newList =  new ArrayList<>(list.size());

            for(Integer i : newList) {
                newList.add(f.apply(i));
            }

            return newList;
    }

    private static void printEventNum(Predicate<Integer> p, Consumer<Integer> c, List<Integer> list) {
        System.out.println("[");
        for(Integer i : list) {
            if(p.test(i)) {
                c.accept(i);
            }
        }

        System.out.println("]");

    }

    private static void makeRandomList(Supplier<Integer> s, List<Integer> list) {
        for(int i=0;i<10;i++) {
            list.add(s.get());
        }
    }


}
