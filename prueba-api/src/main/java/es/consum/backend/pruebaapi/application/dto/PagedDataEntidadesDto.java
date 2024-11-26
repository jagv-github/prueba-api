package es.consum.backend.pruebaapi.application.dto;

import java.util.ArrayList;

import org.springframework.validation.annotation.Validated;

import es.consum.backend.infrastructure.ws.utils.dto.PagedDataAndSortDto;
import es.consum.backend.infrastructure.ws.utils.dto.PagedDataResponseDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Validated
@Data
@EqualsAndHashCode(callSuper = true)
public class PagedDataEntidadesDto extends PagedDataResponseDto<EntidadesDto> {

    private static final long serialVersionUID = 1L;

    public PagedDataEntidadesDto(PagedDataResponseDto<EntidadesDto> response) {
        super(response != null ? response.getData() : new ArrayList<>(),
        response != null ? response.getPaging() : new PagedDataAndSortDto());
    }
}
