package com.itv.checkoutkata.service;

import com.itv.checkoutkata.model.IPriceMatrix;
import com.itv.checkoutkata.model.Price;
import com.itv.checkoutkata.model.PriceMatrix;
import com.itv.checkoutkata.model.Promotion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirany on 14/08/2017.
 */
public class CheckoutServiceTest {

    private CheckoutService service;

    @Before
    public void setUp() throws Exception {
        service = new CheckoutService();
    }

    @Test
    public void checkout() throws Exception {
        IPriceMatrix priceMatrix = Mockito.mock(IPriceMatrix.class);
        List<Price> prodcutPrices = new ArrayList<>();
        prodcutPrices.add(getPrice("A", 5, 12, 3));
        prodcutPrices.add(getPrice("B", 6));
        prodcutPrices.add(getPrice("C", 4, 6, 2));
        Mockito.when(priceMatrix.getProductPrices()).thenReturn(prodcutPrices);
        List<String> products = new ArrayList<>();
        products.add("A");products.add("A");products.add("A");products.add("A");products.add("A");
        products.add("B");products.add("B");
        products.add("C");products.add("C");products.add("C");products.add("C");

        int totalCost = service.checkout(products, priceMatrix);
        Assert.assertEquals(46, totalCost);
    }

    private Price getPrice(String sku, int pricePerUnit) {
        Price price = Mockito.mock(Price.class);
        Mockito.when(price.getSku()).thenReturn(sku);
        Mockito.when(price.getPricePerUnit()).thenReturn(pricePerUnit);
        return price;
    }

    private Price getPrice(String sku, int pricePerUnit, int promotionPrice, int promotionQty) {
        Price price = Mockito.mock(Price.class);
        Mockito.when(price.getSku()).thenReturn(sku);
        Mockito.when(price.getPricePerUnit()).thenReturn(pricePerUnit);
        Promotion promotion = Mockito.mock(Promotion.class);
        Mockito.when(promotion.getQuantity()).thenReturn(promotionQty);
        Mockito.when(promotion.getPrice()).thenReturn(promotionPrice);
        Mockito.when(price.getPromotion()).thenReturn(promotion);
        return price;
    }

}