package com.itv.checkoutkata.service;

import com.itv.checkoutkata.model.IPriceMatrix;
import com.itv.checkoutkata.model.Price;
import com.itv.checkoutkata.model.Promotion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirany on 14/08/2017.
 */
public class PriceCalculatorServiceTest {

    private PriceCalculatorService priceCalculatorService;

    private IPriceMatrix priceMatrix;

    @Before
    public void setUp() throws Exception {
        priceCalculatorService = new PriceCalculatorService();
        priceMatrix = Mockito.mock(IPriceMatrix.class);
    }

    @Test
    public void lookupNullPrice() throws Exception {
        Price price = priceCalculatorService.lookupPrice("A", priceMatrix);
        Assert.assertNull(price);
    }

    @Test
    public void lookupPrice() throws Exception {
        List<Price> productPrices = getProductPrices("A", 5);
        Mockito.when(priceMatrix.getProductPrices()).thenReturn(productPrices);
        Price price = priceCalculatorService.lookupPrice("A", priceMatrix);
        Assert.assertNotNull(price);
        Assert.assertEquals(5, price.getPricePerUnit());
        Assert.assertEquals("A", price.getSku());
    }

    @Test
    public void calculateCostPerUnit() throws Exception {
        Price price = Mockito.mock(Price.class);
        Mockito.when(price.getSku()).thenReturn("A");
        Mockito.when(price.getPricePerUnit()).thenReturn(5);
        int costPerUnit = priceCalculatorService.calculateCostPerUnit(5, price);
        Assert.assertEquals(25, costPerUnit);
    }

    @Test
    public void calculateCostPerUnitWithPramotion() throws Exception {
        Price price = Mockito.mock(Price.class);
        Promotion promotion = Mockito.mock(Promotion.class);
        Mockito.when(promotion.getPrice()).thenReturn(12);
        Mockito.when(promotion.getQuantity()).thenReturn(3);
        Mockito.when(price.getPromotion()).thenReturn(promotion);
        Mockito.when(price.getSku()).thenReturn("A");
        Mockito.when(price.getPricePerUnit()).thenReturn(5);
        int costPerUnit = priceCalculatorService.calculateCostPerUnit(5, price);
        Assert.assertEquals(22, costPerUnit);
    }


    private List<Price> getProductPrices(String sku, int pricePerunit) {
        List<Price> list = new ArrayList<>();
        Price price = Mockito.mock(Price.class);
        Mockito.when(price.getSku()).thenReturn(sku);
        Mockito.when(price.getPricePerUnit()).thenReturn(pricePerunit);
        list.add(price);
        return list;
    }

}