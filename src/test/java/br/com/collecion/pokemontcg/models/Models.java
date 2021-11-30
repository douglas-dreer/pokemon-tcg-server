package br.com.collecion.pokemontcg.models;

import br.com.collecion.pokemontcg.enums.MessagesEnum;

public interface Models {
    void mustReturnSuccess_WhenCreateByConstructorWithParams();
    void mustReturnSuccess_WhenCreateBySetters();
    void mustReturnSuccess_WhenCreateByBuilder();
    default String createMsg(String field) {
        return String.format(MessagesEnum.TEST_ERROR.getText(), field);
    }
}