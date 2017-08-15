package com.itv.checkoutkata.model;

/**
 * Created by kirany on 14/08/2017.
 */
public class Promotion {

	private final int quantity;
	private final int price;

	public Promotion(int quantity, int price) {
		this.quantity = quantity;
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

}
