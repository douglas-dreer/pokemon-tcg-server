package br.com.collecion.pokemontcg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupEnum {
    ADMIN("Administrators"),
    USER("Users"),
    SYSTEM("System");

    private final String text;
}
