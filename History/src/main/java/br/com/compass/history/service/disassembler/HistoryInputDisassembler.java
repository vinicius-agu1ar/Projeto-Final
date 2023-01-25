package br.com.compass.history.service.disassembler;

import br.com.compass.history.entity.History;
import br.com.compass.history.service.response.HistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryInputDisassembler {

    private final ModelMapper modelMapper;

    public History toDomainObject(HistoryResponseDTO historyResponseDTO){
        return modelMapper.map(historyResponseDTO, History.class);
    }
}
