package br.com.collecion.pokemontcg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessagesEnum {
    SUCCESS("Operação realizada com sucesso"),
    NOT_FOUND("Não foi possível encontrar nenhum registro a partir dos parametros enviados"),
    ERROR("Não foi possível realizar a operação com os parametros { '%s': '%s', '%s': '%s' }"),
    INTERNAL_ERROR("Não foi possível realizar a operação. Caso persista o erro, por favor entre em contato com o administrador."),
    BAD_REQUEST_ERROR("Nós não conseguimos processar esta ação"),
    FORBIDDEN_ERROR("O acesso não foi permitido"),
    FAIL_AUTHENTICATE("Falha ao autenticar usuário"),
    SERVICE_UNAVAILABLE_ERROR("Serviço temporáriamente indisponível. Por favor tente mais tarde."),
    TOO_MANY_REQUESTS_ERROR("Foram realizadas muitas requisições em pouco tempo. Por favor aguarde um pouco e tente novamente."),
    UUID_INVALID("UUID %s inválido"),
    ACCESS_DENIED("Usuário, senha ou token inválido"),
    TEST_ERROR("O campo '%s' está vazio, null ou é diferente da esperada.");


    private final String text;
}