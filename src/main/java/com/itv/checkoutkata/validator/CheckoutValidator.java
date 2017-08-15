package com.itv.checkoutkata.validator;

import com.itv.checkoutkata.model.IPriceMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by kirany on 14/08/2017.
 */
public class CheckoutValidator {
	
	public void validCheckoutRequest(final List<String> productSkus, final IPriceMatrix priceMatrix) {
		validateProductSkus(productSkus);
		validatePriceMatrix(priceMatrix);
		validateProductSkusAgainstPriceMatrix(productSkus, priceMatrix);
	}
	
	private void validateProductSkus(final List<String> productSkus){
		if (productSkus == null || productSkus.isEmpty()) {
			throw new IllegalArgumentException("There are no products to checkout");
		}
	}
	
	private void validatePriceMatrix(final IPriceMatrix priceMatrix){
		if (priceMatrix == null) {
			throw new IllegalArgumentException("There are no price matrix for the products");
		}
	}

	private void validateProductSkusAgainstPriceMatrix(final List<String> productSkus, final IPriceMatrix priceMatrix){
		
		final List<String> priceMatrixSkus = new ArrayList<>();
		
		priceMatrix.getProductPrices().forEach(price -> {
			priceMatrixSkus.add(price.getSku());
		});
		
		final List<String> missingPriceMatrix =  productSkus.stream()
															.filter(sku -> !priceMatrixSkus.contains(sku))
															.collect(Collectors.toList());
		
		if(!missingPriceMatrix.isEmpty()){
			throw new NoSuchElementException("No Price Matrix found for Product with SKU " + missingPriceMatrix);
		}
	}
}
