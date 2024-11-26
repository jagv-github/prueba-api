package es.consum.backend.pruebaapi.application.service;

import es.consum.backend.infrastructure.ws.utils.dto.PagedDataResponseDto;
import es.consum.backend.pruebaapi.application.dto.EntidadesDto;
import es.consum.backend.pruebaapi.application.dto.DataEntidadesDto;


import org.springframework.data.domain.Sort;

/**
 * Class for all operations related to Entidades.
 * 
 */
public interface EntidadesService {

    /**
    * create a Entidades object
    * 
    * @param dto
    *
    * @return DataEntidadesDto
    */
    DataEntidadesDto create(EntidadesDto dto);
    
    /**
    * get a Entidades object
    * 
    * @param id
    *
    * @return DataEntidadesDto
    */
    DataEntidadesDto getEntidades(Long id);

    /**
    * update a Entidades object
    * 
    * @param id 
    * @param dto
    *
    * @return DataEntidadesDto
    */
    DataEntidadesDto update(Long id, EntidadesDto dto);

    /**
    * get a list of EntidadesDto objects
    * 
    * @param dto
    * @param page
    * @param size
    * @param sort
    *
    * @return {@link PagedDataResponseDto<EntidadesDto>}
    */
    PagedDataResponseDto<EntidadesDto> getEntidadesPagedDataAndOrderedAndFiltered(EntidadesDto dto, Integer page, Integer size, Sort sort);

    /**
    * delete a Entidades object
    * 
    * @param id
    *
    * @return 
    */
    void deleteEntidades(Long id);

}
