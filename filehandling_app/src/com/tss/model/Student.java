package com.tss.model;

import java.io.Serializable;

public class Student implements Serializable {
	 public int id;
	 public String name;

	 public Student(int id, String name) {
	     this.id = id;
	     this.name = name;
	 }
	}

