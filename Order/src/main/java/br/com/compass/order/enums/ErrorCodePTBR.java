package br.com.compass.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodePTBR {

    REQUISICAO_INVALIDA("Request invalid"),
    PARAMETRO_INVALIDO("Invalid request parameter"),
    ERRO_INTERNO_SERVIDOR("Internal error has occurred."),
    PEDIDO_NAO_ENCONTRADO("Pedido não encontrado, verifique antes de fazer esta solicitação novamente"),
    ENDERECO_NAO_ENCONTRADO("Endereço não encontrado, verifique antes de fazer esta solicitação novamente"),
    ENTIDADE_EM_USO("A entidade está em uso, verifique antes de executar esta ação novamente");

    private final String mensagem;
}
