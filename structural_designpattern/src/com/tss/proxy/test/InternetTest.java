package com.tss.proxy.test;

import com.tss.proxy.model.IInternet;
import com.tss.proxy.model.ProxyInternet;

public class InternetTest {
	public static void main(String[] args) {
		IInternet proxy = new ProxyInternet();
		try {
			proxy.connectTo("google.com");
			proxy.connectTo("youtube.com"); 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}