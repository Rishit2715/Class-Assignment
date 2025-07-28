package com.tss.test;

public class MainThread {

    public static void main(String[] args) {
        
        System.out.println(Thread.currentThread());
        
        Thread.currentThread().setName("Rishit");
        
        System.out.println(Thread.currentThread());
        
        Thread.currentThread().setPriority(9);
        System.out.println(Thread.currentThread());
        
        MyThread thread1 = new MyThread("thread1");
        thread1.start();
        thread1.setPriority(1);
        
        MyThread thread2 = new MyThread("thread2");
        thread2.start();
        thread2.setPriority(2);

        MyThread thread3 = new MyThread("thread3");
        thread3.start();
        thread3.setPriority(3);

        
        
        try {
            Thread.sleep(10000);
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Exiting");
    }

}