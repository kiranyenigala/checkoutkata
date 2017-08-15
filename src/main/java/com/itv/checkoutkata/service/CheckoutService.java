package com.itv.checkoutkata.service;

import com.itv.checkoutkata.model.IPriceMatrix;
import com.itv.checkoutkata.model.Price;
import com.itv.checkoutkata.validator.CheckoutValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kirany on 14/08/2017.
 *
 */
public class CheckoutService {

	private PriceCalculatorService priceCalculatorService = new PriceCalculatorService();
	
	private CheckoutValidator checkoutValidator = new CheckoutValidator();

	/**
	 *
	 * @param productSkus
	 * @param priceMatrix
	 * @return
	 */
	public int checkout(final List<String> productSkus, final IPriceMatrix priceMatrix) {

		checkoutValidator.validCheckoutRequest(productSkus, priceMatrix);

		final Map<String, Integer> productsAndCost = getProductsAndCost(productSkus, priceMatrix);

		return priceCalculatorService.calculateCheckoutTotal(productsAndCost);
	}

	/**
	 *
	 * @param productSkus
	 * @return
	 */
	private Map<String, Long> getProductsAndQuantity(final List<String> productSkus){
		return productSkus.stream()
				.collect(Collectors.groupingBy(sku -> sku, Collectors.counting()));
	}

	/**
	 *
	 * @param productSkus
	 * @param priceMatrix
	 * @return
	 */
	private Map<String, Integer> getProductsAndCost(final List<String> productSkus, final IPriceMatrix priceMatrix){
		
		final Map<String, Long> productsAndQuantity = getProductsAndQuantity(productSkus);
		final Map<String, Integer> productsAndCost = new HashMap<>();
		
		productsAndQuantity.forEach((sku, qty) -> {
			final Price price = priceCalculatorService.lookupPrice(sku, priceMatrix);
			productsAndCost.put(sku, priceCalculatorService.calculateCostPerUnit(qty.intValue(), price));
		});
		
		return productsAndCost;
	}

}
