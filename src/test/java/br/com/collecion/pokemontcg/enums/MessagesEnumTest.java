package br.com.collecion.pokemontcg.enums;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessagesEnumTest {
    private static final String success = "Operação realizada com sucesso";
    private static final String notFound = "Não foi possível encontrar nenhum registro a partir dos parametros enviados";
    private static final String internalError = "Não foi possível realizar a operação. Caso persista o erro, por favor entre em contato com o administrador.";
    private static final String error = "Não foi possível realizar a operação com os parametros { '%s': '%s', '%s': '%s' }";
    private static final String badRequestError = "Nós não conseguimos processar esta ação";
    private static final String forbiddenError = "O acesso não foi permitido";
    private static final String serviceUnavailableError = "Serviço temporáriamente indisponível. Por favor tente mais tarde.";
    private static final String tooManyRequestsError = "Foram realizadas muitas requisições em pouco tempo. Por favor aguarde um pouco e tente novamente.";
    private static final String uuidInvalid = "UUID %s inválido";
    private static final String accessDenied = "Usuário, senha ou token inválido";
    private static final String testError = "O campo '%s' está vazio, null ou é diferente da esperada.";

    @Test
    public void mustReturnSuccess_WhenGetText() {
        assertEquals(success, MessagesEnum.SUCCESS.getText());
        assertEquals(notFound, MessagesEnum.NOT_FOUND.getText());
        assertEquals(internalError, MessagesEnum.INTERNAL_ERROR.getText());
        assertEquals(error, MessagesEnum.ERROR.getText());
        assertEquals(badRequestError, MessagesEnum.BAD_REQUEST_ERROR.getText());
        assertEquals(forbiddenError, MessagesEnum.FORBIDDEN_ERROR.getText());
        assertEquals(serviceUnavailableError, MessagesEnum.SERVICE_UNAVAILABLE_ERROR.getText());
        assertEquals(tooManyRequestsError, MessagesEnum.TOO_MANY_REQUESTS_ERROR.getText());
        assertEquals(uuidInvalid, MessagesEnum.UUID_INVALID.getText());
        assertEquals(accessDenied, MessagesEnum.ACCESS_DENIED.getText());
        assertEquals(testError, MessagesEnum.TEST_ERROR.getText());
    }
}
