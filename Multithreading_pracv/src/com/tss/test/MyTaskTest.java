package com.tss.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tss.task.NewTask;

public class MyTaskTest {

    public static void main(String[] args) {


    	
//    	ExecutorService service = Executors.newCachedThreadPool();
//    	ExecutorService service = Executors.newFixedThreadPool(3);
//    	ExecutorService service = Executors.newSingleThreadExecutor();
//
//
//    	service.submit(new MyTask());
//    	service.submit(new MyTask2());
//    	service.submit(new MyTask());
//    	service.submit(new MyTask());
//    	
//    	service.shutdown();
    	
    	ExecutorService service = null;
    	try {
    	    List<NewTask> tasks = Arrays.asList(
    	        new NewTask(), new NewTask(), new NewTask(), new NewTask()
    	    );

    	    service = Executors.newSingleThreadExecutor();
    	    // Future<Integer> future = service.submit(new NewTask());

    	    int value = service.invokeAny(tasks);

    	    List<Future<Integer>> futures = service.invokeAll(tasks);

    	    for (Future<Integer> future : futures) {
    	        System.out.println(future.get());
    	    }
    	} catch (InterruptedException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	} catch (ExecutionException e) {
    	    // TODO Auto-generated catch block
    	    e.printStackTrace();
    	}

        
    }

}