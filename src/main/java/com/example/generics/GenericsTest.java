package com.example.generics;

public class GenericsTest {

    public static void main(String[] args) {

        BoxA<String> b = new BoxA<String>();
        b.setItem((String) new Object());
        b.setItem("ABC");
        String item = b.getItem();

    }

}

class BoxA<T> {
    T item;

    void setItem(T item) {this.item = item;}
    T getItem() { return item;}

    T[] itemArr;

//    T[] toArray() {
//        //T[] tmpArr = new T[itemArr.length];  // 오류 발생
//
//        return tmpArr;
//    }


}
