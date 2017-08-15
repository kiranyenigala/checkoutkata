package com.itv.checkoutkata.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kirany on 14/08/2017.
 */
public class PromotionTest {

    private Promotion promotion;

    @Before
    public void setUp() throws Exception {
        promotion = new Promotion(2, 5);
    }

    @Test
    public void getQuantity() throws Exception {
        int quantity = promotion.getQuantity();
        Assert.assertEquals(2, quantity);
    }

    @Test
    public void getPrice() throws Exception {
        int price = promotion.getPrice();
        Assert.assertEquals(5, price);
    }

}