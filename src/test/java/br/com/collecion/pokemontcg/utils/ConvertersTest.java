package br.com.collecion.pokemontcg.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;



public class ConvertersTest {
    @Test
    public void privateConstructorTest() {
        assertThrows(IllegalStateException.class, () -> Converters.applicationError());
    }
}