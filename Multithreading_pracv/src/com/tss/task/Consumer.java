package com.tss.task;

public class Consumer implements Runnable {
	Q q;

	public Consumer(Q q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}
			
	public void run() {
		for (int i = 0; i < 10; i++) {
			q.get();
		}
	}
}
