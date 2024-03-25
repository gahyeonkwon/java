package com.example.generics;

import java.util.ArrayList;


class Fruit { public String toString() { return "Fruit";}}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends  Fruit { public String toString() { return "Grape";}}
class Toy { public String toString() { return "Toy";}}

class Juice {
    String name;
    Juice(String name) { this.name = name + "Juice";}
    public String toString() { return name;}
}

class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";

        for(Fruit f : box.getList()) {
            tmp += f + "";
        }
             return new Juice(tmp);
    }
}
public class GenericsTest2 {

    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        Box<Toy> toyBox = new Box<Toy>();
        //Box<Grape> grapeBox = new Box<Apple>(); // 에러 타입 불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple()); // ok. void add(Fruit item)

        fruitBox.add(new Apple());
        fruitBox.add(new Apple());
        //appleBox.add(new Toy()); // error. box<Apple> 에는 Apple 만 담을 수 있음

        toyBox.add(new Toy());
        //toyBox.add(new Apple()); // error . box<Toy> 에는 Toy 만 담을 수 있음

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));
    }
}


class FruitBox<T extends  Fruit> extends Box<T> {}
class Box<T> {
    ArrayList<T> list = new ArrayList<>();
    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i);}
    int size() { return list.size(); }
    public String toString() { return list.toString();}

    ArrayList<T> getList() { return list;}
}
