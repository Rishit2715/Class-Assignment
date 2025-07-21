package com.tss.test;

import java.util.List;

import com.tss.model.Product;

public class ProductTest {
	public static void main(String []args) {
	List<Product> products = List.of(
		    new Product("Pen", 10, 2),
		    new Product("Notebook", 50, 1),
		    new Product("Pencil", 5, 5)
		);
	
	double totalBill = products.stream()
		    .map(product -> product.getPrice() * product.getQuantity()) 
		    .reduce(0.0, Double::sum);

	System.out.println(totalBill);
}
}