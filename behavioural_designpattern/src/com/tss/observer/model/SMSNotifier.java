package com.tss.observer.model;

public class SMSNotifier implements INotifier {
	@Override
	public void sendNotification(Account account) {
		System.out.println(
				"SMS: Notification sent for Account " + account.getAccNo() + " | Balance: " + account.getBalance());
	}
}
