package com.tss.observer.model;

public class EmailNotifier implements INotifier {
	@Override
	public void sendNotification(Account account) {
		System.out.println(
				"Email: Notification sent for Account " + account.getAccNo() + " | Balance: " + account.getBalance());
	}
}
