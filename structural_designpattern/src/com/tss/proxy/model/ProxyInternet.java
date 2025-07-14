package com.tss.proxy.model;

import java.util.List;

public class ProxyInternet implements IInternet {
	private IInternet internet = new RealInternet();
	private static final List<String> blockedSites = List.of("facebook.com", "youtube.com");

	public void connectTo(String site) throws Exception {
		if (blockedSites.contains(site.toLowerCase())) {
			throw new Exception("Access Denied to " + site);
		}
		internet.connectTo(site);
	}
}