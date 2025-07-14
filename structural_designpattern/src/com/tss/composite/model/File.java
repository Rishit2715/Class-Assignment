package com.tss.composite.model;

public class File implements IFileComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("File: " + name);
    }
}