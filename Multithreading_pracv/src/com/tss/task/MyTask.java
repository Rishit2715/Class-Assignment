package com.tss.task;

public class MyTask implements Runnable{

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName()+" i am running");
        
    }
    
}