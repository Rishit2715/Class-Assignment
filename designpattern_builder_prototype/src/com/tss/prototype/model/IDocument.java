package com.tss.prototype.model;

public interface IDocument extends Cloneable {
	IDocument clone();

	void print();
}
