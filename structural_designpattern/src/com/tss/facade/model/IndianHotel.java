package com.tss.facade.model;

public class IndianHotel implements IHotel {

	@Override
	public IMenu getMenu() {
		// TODO Auto-generated method stub

		return new IndianMenu();
	}

}
