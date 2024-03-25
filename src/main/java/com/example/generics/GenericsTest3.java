package com.example.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Fruit2 {
    String name;
    int weight;

    public Fruit2(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        return name + " (" + weight + ")" ;
    }
}


class Apple2 extends Fruit2 {
    Apple2(String name, int weight) {
        super(name, weight);
    }
}

class Grape2 extends Fruit2 {
    Grape2(String name, int weight) {
        super(name, weight);
    }
}

class AppleComp implements Comparator<Apple2> {
    public int compare(Apple2 t1, Apple2 t2) {
        return t2.weight - t1.weight;
    }
}

class GrapeComp implements  Comparator<Grape2> {
    public int compare(Grape2 t1, Grape2 t2) {
        return t2.weight - t1.weight;
    }
}

class FruitComp implements Comparator<Fruit2> {
    public int compare(Fruit2 t1, Fruit2 t2) {
        return t1.weight - t2.weight;
    }
}


public class GenericsTest3 {
    public static void main(String[] args) {


        FruitBox2<Apple2> apple2FruitBox = new FruitBox2<Apple2>();
        FruitBox2<Grape2> grape2FruitBox = new FruitBox2<Grape2>();

        apple2FruitBox.add(new Apple2("GreenApple", 300));
        apple2FruitBox.add(new Apple2("GreenApple", 100));
        apple2FruitBox.add(new Apple2("GreenApple", 200));

        grape2FruitBox.add(new Grape2("GreenGrape", 400));
        grape2FruitBox.add(new Grape2("GreenGrape", 300));
        grape2FruitBox.add(new Grape2("GreenGrape", 200));

        Collections.sort(apple2FruitBox.getList(), new AppleComp());
        Collections.sort(grape2FruitBox.getList(), new GrapeComp());
        System.out.println(apple2FruitBox);
        System.out.println(grape2FruitBox);
        System.out.println("=============");
        Collections.sort(apple2FruitBox.getList(), new FruitComp());
        Collections.sort(grape2FruitBox.getList(), new FruitComp());

        System.out.println(apple2FruitBox);
        System.out.println(grape2FruitBox);

    }
}
    class FruitBox2<T extends  Fruit2> extends Box2<T> {}
    class Box2<T> {
        ArrayList<T> list = new ArrayList<>();
        void add(T item) { list.add(item); }
        T get(int i) { return list.get(i);}
        int size() { return list.size(); }
        public String toString() { return list.toString();}

        ArrayList<T> getList() { return list;}
    }

