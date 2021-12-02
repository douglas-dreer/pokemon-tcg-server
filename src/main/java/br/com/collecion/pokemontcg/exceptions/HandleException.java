package br.com.collecion.pokemontcg.exceptions;

import br.com.collecion.pokemontcg.enums.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleException {
    public static Logger logger = LoggerFactory.getLogger(HandleException.class);

    public static void errorHandling(String code) {

        switch (code) {
            case "400":
                logger.error(ExceptionEnum.BAD_REQUEST.getText());
                break;
            case "403":
                logger.error(ExceptionEnum.FORBIDDEN.getText());
                break;
            case "404 NOT_FOUND":
                logger.error(ExceptionEnum.NOT_FOUND.getText());
                break;
            case "500":
                logger.error(ExceptionEnum.INTERNAL_SERVER_ERROR.getText());
                break;
            case "503":
                logger.error(ExceptionEnum.SERVICE_UNAVAILABLE.getText());
                break;

            default:
                logger.error(code);
        }
    }
}