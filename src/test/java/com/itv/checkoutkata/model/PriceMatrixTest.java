package com.itv.checkoutkata.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirany on 14/08/2017.
 */
public class PriceMatrixTest {

    private PriceMatrix priceMatrix;

    @Before
    public void setUp() throws Exception {
        priceMatrix = new PriceMatrix();
    }

    @Test
    public void getProductPricesNullPrices() throws Exception {
        Assert.assertNull(priceMatrix.getProductPrices());
        List<Price> mockPrices = new ArrayList<>();
        priceMatrix.setProductPrices(mockPrices);
        Assert.assertNotNull(priceMatrix.getProductPrices());
    }

    @Test
    public void getProductPricesEmptyPrices() throws Exception {
        List<Price> mockPrices = new ArrayList<>();
        priceMatrix.setProductPrices(mockPrices);
        List<Price> returnValue = priceMatrix.getProductPrices();
        Assert.assertNotNull(returnValue);
        Assert.assertEquals(0, returnValue.size());
    }

    @Test
    public void getProductPricesReferencesExists() throws Exception {
        List<Price> mockPrices = new ArrayList<>();
        Price price = Mockito.mock(Price.class);
        mockPrices.add(price);
        price = Mockito.mock(Price.class);
        mockPrices.add(price);
        priceMatrix.setProductPrices(mockPrices);
        List<Price> returnValue = priceMatrix.getProductPrices();
        Assert.assertNotNull(returnValue);
        Assert.assertEquals(2, returnValue.size());
    }

    @Test
    public void setProductPrices() throws Exception {
        List<Price> mockPrices = new ArrayList<>();
        priceMatrix.setProductPrices(mockPrices);
        List<Price> returnValue = priceMatrix.getProductPrices();
        Assert.assertEquals(mockPrices, returnValue);
        Assert.assertEquals(0, returnValue.size());
    }

}