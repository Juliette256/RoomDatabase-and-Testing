package com.example.juliet2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DAOTest {
    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
}}