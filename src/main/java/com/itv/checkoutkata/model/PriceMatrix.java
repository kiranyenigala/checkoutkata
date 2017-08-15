package com.itv.checkoutkata.model;

import java.util.List;

/**
 * Created by kirany on 14/08/2017.
 *
 */
public class PriceMatrix implements IPriceMatrix {

	private List<Price> productPrices;

	public List<Price> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<Price> productPrices) {
		this.productPrices = productPrices;
	}
}
