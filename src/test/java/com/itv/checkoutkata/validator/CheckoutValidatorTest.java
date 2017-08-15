package com.itv.checkoutkata.validator;

import com.itv.checkoutkata.model.IPriceMatrix;
import com.itv.checkoutkata.model.Price;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by kirany on 14/08/2017.
 */
public class CheckoutValidatorTest {

    private CheckoutValidator validator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private IPriceMatrix priceMatrix;

    @Before
    public void setUp() throws Exception {
        validator = new CheckoutValidator();
        priceMatrix = Mockito.mock(IPriceMatrix.class);
    }

    @Test
    public void validCheckoutRequestNullSkus() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("There are no products to checkout");
        validator.validCheckoutRequest(null, priceMatrix);

        List<String> productSkus = new ArrayList<>();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("There are no products to checkout");
        validator.validCheckoutRequest(productSkus, priceMatrix);
    }

    @Test
    public void validCheckoutRequestPriceMatrixNull() throws Exception {
        List<String> productSkus = new ArrayList<>();
        productSkus.add("A");
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("There are no price matrix for the products");
        validator.validCheckoutRequest(productSkus, null);
    }

    @Test
    public void validCheckoutRequest() throws Exception {
        List<String> productSkus = new ArrayList<>();
        productSkus.add("A");
        productSkus.add("C");
        List<Price> productPrices = new ArrayList<>();
        Price price = Mockito.mock(Price.class);
        Mockito.when(price.getSku()).thenReturn("A");
        productPrices.add(price);
        Mockito.when(priceMatrix.getProductPrices()).thenReturn(productPrices);

        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("No Price Matrix found for Product with SKU [C]");
        validator.validCheckoutRequest(productSkus, priceMatrix);
    }

}