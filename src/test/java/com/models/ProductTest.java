package com.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void getCost_shouldReturnZeroWhenNull() {
        Product actual = new Product();
        assertEquals(BigDecimal.ZERO, actual.getCost());
    }

}