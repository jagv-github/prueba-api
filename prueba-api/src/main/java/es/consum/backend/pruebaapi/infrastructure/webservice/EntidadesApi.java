package es.consum.backend.pruebaapi.infrastructure.webservice;

import static es.consum.backend.infrastructure.data.service.impl.FilterUtils.FILTER_NOTE;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;

import es.consum.backend.common.commonutils.annotations.WebService;
import es.consum.backend.pruebaapi.application.dto.DataEntidadesDto;
import es.consum.backend.pruebaapi.application.dto.EntidadesDto;
import es.consum.backend.pruebaapi.application.dto.PagedDataEntidadesDto;
import es.consum.backend.pruebaapi.application.service.EntidadesService;

import jakarta.validation.Valid;


/**
 * API for all operations related to Entidades.
 *
 */
@Api(value = "Entidades", tags = { "EntidadesApi v1" })
@WebService
@RequestMapping(value = "/entidades/v1", produces = {"application/json"})
@Validated
public class EntidadesApi {

    private final EntidadesService entidadesService;

    EntidadesApi(EntidadesService entidadesService){
        this.entidadesService = entidadesService;
    }

	/**
    * Entry Point to create a Entidades object
    * 
    * @param EntidadesDto
    *
    * @return EntidadesDto
    */
    @ApiOperation(value = "Creación de un nuevo Entidades", notes="", nickname = "createEntidades")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "List of Entidades"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping
    public ResponseEntity<DataEntidadesDto> createEntidades(@Valid @RequestBody EntidadesDto dto){

    DataEntidadesDto entidadesDto = entidadesService.create(dto);
    return new ResponseEntity<>(entidadesDto, HttpStatus.OK);
    }



    /**
    * Entry Point to get a Entidades object
    * 
    * @param id
    *
    * @return EntidadesDto
    */
    @ApiOperation(value = "Recupera un Entidades", notes="", nickname = "getEntidades")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "DataEntidadesDto"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping( value = "/{id}")
    public ResponseEntity<DataEntidadesDto> getEntidades(@PathVariable Long id){
        DataEntidadesDto entidadesDto = entidadesService.getEntidades(id);
        return new ResponseEntity<>(entidadesDto, HttpStatus.OK);
    }

    /**
    * Entry Point to delete a Entidades object
    * 
    * @param id
    *
    * @return
    */
    @ApiOperation(value = "Borrar Entidades", notes="", nickname = "deleteEntidades")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Entidades deleted"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> deleteEntidades(@PathVariable Long id){
        entidadesService.deleteEntidades(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





    /**
    * Entry Point to update a Entidades object
    * 
    * @param dto
    *
    * @return EntidadesDto
    */
    @ApiOperation(value = "Actualizar Entidades", notes="", nickname = "updateEntidades")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Entidades updated"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<DataEntidadesDto> updateEntidades(@PathVariable Long id, @Valid @RequestBody EntidadesDto dto){
    	DataEntidadesDto entidadesDto = entidadesService.update(id, dto);
        return new ResponseEntity<>(entidadesDto, HttpStatus.OK);
    }


    /**
    * Entry point to get a list of Entidades objects
    * 
    * @param page
    * @param size
    * @param sort
    *
    * @return {@link PagedDataResponseDto<EntidadesDto>}
    */
    @ApiOperation(value = "Búsqueda de Entidades", notes = FILTER_NOTE, nickname = "getEntidadesPagedAndOrderedAndFiltered")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Lista de Entidades paginada"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "Results page you want to retrieve (0..N)", defaultValue = "0", dataTypeClass = Integer.class, paramType = "query"),
    @ApiImplicitParam(name = "size", value = "Number of records per page.", defaultValue = "10", dataTypeClass = Integer.class, paramType = "query"),
    @ApiImplicitParam(name = "sort", value = "Sorting criteria in the format: property(,asc|desc). "
    + "Default sort order is ascending. Multiple sort criteria are supported.", allowMultiple = true, dataType = "string", paramType = "query")})
    @GetMapping
    public ResponseEntity<PagedDataEntidadesDto> getEntidadesPagedAndOrderedAndFiltered(Integer page, Integer size, Sort sort) {
        // TODO: Agrega al endpoint los queryParams que se usarán en la búsqueda y los estableces en el searchDto
        EntidadesDto searchDto = new EntidadesDto();
        PagedDataEntidadesDto pageDto = new PagedDataEntidadesDto(entidadesService.getEntidadesPagedDataAndOrderedAndFiltered(searchDto, page, size, sort));
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}
