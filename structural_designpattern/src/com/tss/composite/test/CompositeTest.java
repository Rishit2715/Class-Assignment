package com.tss.composite.test;

import com.tss.composite.model.File;
import com.tss.composite.model.Folder;
import com.tss.composite.model.IFileComponent;

public class CompositeTest {
	public static void main(String[] args) {
		IFileComponent file1 = new File("Resume.doc");
		IFileComponent file2 = new File("Photo.jpg");

		Folder folder1 = new Folder("My Documents");
		folder1.add(file1);
		folder1.add(file2);

		IFileComponent file3 = new File("Project.zip");
		Folder folder2 = new Folder("Downloads");
		folder2.add(file3);
		folder2.add(folder1); 

		folder2.show();
	}
}