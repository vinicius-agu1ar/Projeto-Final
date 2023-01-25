package br.com.compass.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodePTBR {

    REQUISICAO_INVALIDA("Requisição Invalida"),
    PARAMETRO_INVALIDO("Parametro requisitado invalido"),
    ERRO_INTERNO_SERVIDOR("Um erro interno ocorreu"),
    PEDIDO_NAO_ENCONTRADO("Pedido não encontrado, verifique antes de fazer esta solicitação novamente"),
    ENDERECO_NAO_ENCONTRADO("Endereço não encontrado, verifique antes de fazer esta solicitação novamente"),
    ITEM_NAO_ENCONTRADO("Item não encontrado, verifique antes de fazer esta solicitação novamente"),
    CEP_INVALIDO("CEP invalido, por favor verifique antes de fazer esta solicitação novamente"),
    CPF_INVALIDO("CPF invalido, por favor verifique antes de fazer esta solicitação novamente"),
    SINTAXE_INVALIDA("Sintaxe invalido, por favor verifique antes de fazer esta solicitação novamente"),
    USO_DATA_INVALIDO("Data invalida, por favor verifique antes de fazer esta solicitação novamente"),
    ENTIDADE_EM_USO("A entidade está em uso, verifique antes de executar esta ação novamente");

    private final String mensagem;
}
