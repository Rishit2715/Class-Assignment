package com.tss.observer.model;

public class WhatsappNotifier implements INotifier {
	@Override
	public void sendNotification(Account account) {
		System.out.println("WhatsApp: Notification sent for Account " + account.getAccNo() + " | Balance: "
				+ account.getBalance());
	}
}
