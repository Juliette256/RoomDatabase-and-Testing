package com.example.juliet2;

import static org.junit.Assert.assertTrue;


public class BookTest {
    public static void assertEquals( Book expected, Book actual ) {
        assertTrue(expected.getBookName( ).equals( actual.getBookName( ) )
                && expected.getBookType( ).equals( actual.getBookType( ) ));
    }

}