package br.com.collecion.pokemontcg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    BAD_REQUEST("Bad Request"),
    FORBIDDEN("Forbidden"),
    NOT_FOUND("Not Found"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    SERVICE_UNAVAILABLE("Service Unavailable");

    private final String text;


}