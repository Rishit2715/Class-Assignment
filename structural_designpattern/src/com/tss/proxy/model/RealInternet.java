package com.tss.proxy.model;

class RealInternet implements IInternet {
    public void connectTo(String site) {
        System.out.println("Connecting to " + site);
    }
}