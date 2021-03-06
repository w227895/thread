package com.kebo.synchronize;

/**
 * @description:
 * @author: kb
 * @create: 2021-01-03 14:26
 **/
public class SychronizedDemo02 implements Runnable {
    static int i = 100;

    //加上了synchronized可以保证线程同步,结果是3100,否则结果可能小于3100
    public static synchronized void increase() {
        i++;
    }

    //不同线程访问这个方法不会互斥
    public synchronized void increaseNoStatic() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        SychronizedDemo02 sychronizedDemo01 = new SychronizedDemo02();
        Thread thread1 = new Thread(sychronizedDemo01);
        Thread thread2 = new Thread(sychronizedDemo01);
        Thread thread3 = new Thread(sychronizedDemo01);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(SychronizedDemo02.i);
    }
}

