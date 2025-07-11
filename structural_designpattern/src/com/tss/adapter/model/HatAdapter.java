package com.tss.adapter.model;

public class HatAdapter implements IItem{

	Hat hat = new Hat("hi", "hello", 10.0, 500.0);
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (hat.getShortName()+hat.getLongName());
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return ((hat.getPrice())-((hat.getPrice()*hat.getDiscount())/100));

	}
	

}
