package br.com.compass.history.service.assembler;

import br.com.compass.history.entity.History;
import br.com.compass.history.service.response.HistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HistoryDTOAssembler {

    private final ModelMapper modelMapper;

    public HistoryResponseDTO toModel(History history){
        return modelMapper.map(history, HistoryResponseDTO.class);
    }

    public List<HistoryResponseDTO> toCollectionModel(List<History> histories){
        return histories.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
