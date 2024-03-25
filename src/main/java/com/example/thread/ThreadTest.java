package com.example.thread;

import javax.swing.*;

public class ThreadTest {

    public static void main(String[] args) {

         Thread_T1 t1 = new Thread_T1();

         Runnable r = new Thread_T2();
         Thread t2 = new Thread(r);

         t1.start();
         t2.start();
    }
}


class Thread_T1 extends Thread {

    @Override
    public void run() {
        for(int i=0; i < 5; i ++) {
            System.out.println(getName()); // 조상 Thread 의 getName() 을 호출
        }
    }
}


class Thread_T2 implements Runnable {
    @Override
    public void run() {
        for(int i=0; i <5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}