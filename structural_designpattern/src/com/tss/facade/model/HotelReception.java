package com.tss.facade.model;

public class HotelReception {

	public void getIndianMenu() {
		IHotel indianHotel = new IndianHotel();
		IMenu indianMenu = indianHotel.getMenu();
		indianMenu.displayMenu();
	}
	
	public void getItalianMenu() {

	IHotel italianHotal = new IndianHotel();
	IMenu italianmenu = italianHotal.getMenu();
	italianmenu.displayMenu();
	}
}
