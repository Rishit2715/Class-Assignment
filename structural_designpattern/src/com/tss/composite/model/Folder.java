package com.tss.composite.model;

import java.util.ArrayList;
import java.util.List;

public class Folder implements IFileComponent {
	private String name;
	private List<IFileComponent> children = new ArrayList<>();

	public Folder(String name) {
		this.name = name;
	}

	public void add(IFileComponent component) {
		children.add(component);
	}

	public void show() {
		System.out.println("Folder: " + name);
		for (IFileComponent child : children) {
			child.show();
		}
	}
}