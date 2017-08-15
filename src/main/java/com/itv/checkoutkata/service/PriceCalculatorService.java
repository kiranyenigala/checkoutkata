package com.itv.checkoutkata.service;

import com.itv.checkoutkata.model.IPriceMatrix;
import com.itv.checkoutkata.model.Price;
import com.itv.checkoutkata.model.Promotion;

import java.util.Map;

/**
 * Created by kirany on 14/08/2017.
 */
public class PriceCalculatorService {

	/**
	 *
	 * @param sku
	 * @param priceMatrix
	 * @return
	 */
	public Price lookupPrice(final String sku, final IPriceMatrix priceMatrix) {
		return priceMatrix.getProductPrices()
				.stream()
				.filter(price -> price.getSku().equalsIgnoreCase(sku))
				.findFirst()
				.orElse(null);
	}

	/**
	 *
	 * @param skuAndTotalCosts
	 * @return
	 */
	public Integer calculateCheckoutTotal(final Map<String, Integer> skuAndTotalCosts) {
		return skuAndTotalCosts.values()
				.stream()
				.mapToInt(Integer::intValue)
				.sum();
	}

	/**
	 *
	 * @param qty
	 * @param unitPrice
	 * @return
	 */
	public int calculateCostPerUnit(final int qty, final Price unitPrice) {
		final int pricePerUnit = unitPrice.getPricePerUnit();
		final Promotion promotion = unitPrice.getPromotion();

		if (isValidPromotion(promotion)){
			return getPromotionCost(promotion, qty, pricePerUnit);
		}

		return qty * pricePerUnit;
	}

	/**
	 *
	 * @param promotion
	 * @return
	 */
	private boolean isValidPromotion(final Promotion promotion){
		return promotion != null && promotion.getPrice() != 0 && promotion.getQuantity() != 0;
	}

	/**
	 *
	 * @param promotion
	 * @param unitQty
	 * @param pricePerUnit
	 * @return
	 */
	private int getPromotionCost(final Promotion promotion, final int unitQty, final int pricePerUnit){
		final int promotionQty = promotion.getQuantity();
		final int promotionApparences = unitQty / promotionQty;
		
		if (promotionApparences != 0) {
			int cost = promotionApparences * promotion.getPrice();
			final int exceedingUnits = unitQty % promotionQty;
			if (exceedingUnits != 0) {
				cost += exceedingUnits * pricePerUnit;
			}
			return cost;
		}
		
		return unitQty * pricePerUnit;
	}
}
