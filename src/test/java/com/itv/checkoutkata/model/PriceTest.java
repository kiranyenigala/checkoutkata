package com.itv.checkoutkata.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by kirany on 15/08/2017.
 */
public class PriceTest {

    private Price price;

    @Before
    public void setUp() throws Exception {
        price = new Price(5);
    }

    @Test
    public void getPromotionNotExistis() throws Exception {
        Promotion promotion = price.getPromotion();
        assertNull(promotion);
    }

    @Test
    public void getPromotionExistis() throws Exception {
        Promotion promotion = Mockito.mock(Promotion.class);
        price.setPromotion(promotion);
        Promotion returnValue = price.getPromotion();
        assertNotNull(returnValue);
        assertEquals(returnValue, promotion);
    }

    @Test
    public void getSku() throws Exception {
        String sku = price.getSku();
        assertNull(sku);

        String value = "A";
        price.setSku(value);
        sku = price.getSku();
        assertNotNull(sku);
        assertEquals("A", sku);

        price.setSku("B");
        sku = price.getSku();
        assertNotNull(sku);
        assertEquals("B", sku);

    }

    @Test
    public void getPricePerUnit() throws Exception {
        int unitPrice = price.getPricePerUnit();
        assertEquals(5, unitPrice);

        price.setPricePerUnit(2);
        unitPrice = price.getPricePerUnit();
        assertEquals(2, unitPrice);
    }
}