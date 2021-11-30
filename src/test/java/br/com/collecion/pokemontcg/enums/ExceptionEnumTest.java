package br.com.collecion.pokemontcg.enums;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExceptionEnumTest {
    private static final String badRequest = "Bad Request";
    private static final String forbidden = "Forbidden";
    private static final String notFound = "Not Found";
    private static final String internalServerError = "Internal Server Error";
    private static final String serviceUnavailable = "Service Unavailable";

    @Test
    public void mustReturnSuccess_WhenGetText() {
        assertEquals(badRequest, ExceptionEnum.BAD_REQUEST.getText());
        assertEquals(forbidden, ExceptionEnum.FORBIDDEN.getText());
        assertEquals(notFound, ExceptionEnum.NOT_FOUND.getText());
        assertEquals(internalServerError, ExceptionEnum.INTERNAL_SERVER_ERROR.getText());
        assertEquals(serviceUnavailable, ExceptionEnum.SERVICE_UNAVAILABLE.getText());
    }
}
