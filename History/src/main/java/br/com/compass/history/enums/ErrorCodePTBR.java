package br.com.compass.history.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodePTBR {
    REQUISICAO_INVALIDA("Requisição Invalida"),
    PARAMETRO_INVALIDO("Parametro requisitado invalido"),
    ERRO_INTERNO_SERVIDOR("Um erro interno ocorreu"),
    HISTORICO_NAO_ENCONTRADO("Histórico não encontrado, verifique antes de fazer esta solicitação novamente");

    private final String mensagem;
}
