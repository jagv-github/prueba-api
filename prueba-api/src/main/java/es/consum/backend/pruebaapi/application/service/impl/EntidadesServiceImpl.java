package es.consum.backend.pruebaapi.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import es.consum.backend.common.commonutils.annotations.ApplicationService;
import es.consum.backend.common.commonutils.exception.TechnicalException;
import es.consum.backend.common.commonutils.internationalization.GenericErrorMessageKey;
import es.consum.backend.domain.domainutils.utils.DefaultPaginationValues;
import es.consum.backend.infrastructure.ws.utils.dto.PagedDataResponseDto;
import es.consum.backend.infrastructure.ws.utils.dto.PagedDataResponseDtoMapper;
import es.consum.backend.infrastructure.ws.utils.dto.SortValidator;
import es.consum.backend.pruebaapi.application.dto.DataEntidadesDto;
import es.consum.backend.pruebaapi.application.dto.EntidadesDto;
import es.consum.backend.pruebaapi.application.dto.EntidadesDtoMapper;
import es.consum.backend.pruebaapi.application.dto.PagedDataEntidadesDto;
import es.consum.backend.pruebaapi.application.service.EntidadesService;
import es.consum.backend.pruebaapi.domain.model.Entidades;
import es.consum.backend.pruebaapi.domain.model.EntidadesRepository;

@ApplicationService
@Transactional
public class EntidadesServiceImpl implements EntidadesService {

    @Autowired
    private EntidadesRepository repository;

    @Autowired
    private EntidadesDtoMapper mapper;

    @Autowired
    private DefaultPaginationValues defaultPaginationValues;
    
    @Autowired
    private PagedDataResponseDtoMapper<EntidadesDto> pagedDataResponseDtoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public DataEntidadesDto create(EntidadesDto dto){
        /* TODO: Revisar la creacion de un Entidades mediante el builder y después pasárselo al repositorio */
        /* TODO tal y cual y pascual */
          Entidades entity = Entidades.builder()
                  .id(dto.getId()).nroent(dto.getNroent())
            .noment(dto.getNoment())
            .codagrup(dto.getCodagrup())
            .tpopro(dto.getTpopro())
            .disabled(dto.getDisabled())
            .build();
        EntidadesDto newDto = mapper.fromEntity(repository.create(entity));
       return new DataEntidadesDto(newDto);      
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataEntidadesDto getEntidades(Long id) {
        Optional<Entidades> entity = repository.findById(id);
        if (entity.isPresent()) {
        	EntidadesDto dto = mapper.fromEntity(entity.get());
            return new DataEntidadesDto(dto);
        } else {
            throw new TechnicalException(GenericErrorMessageKey.ENTITY_ID_NOT_FOUND_KEY.getValue(),
                Entidades.class.getSimpleName(), id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataEntidadesDto update(Long id, EntidadesDto dto) {
   Optional<Entidades> entityExists = repository.findById(id);
        if (entityExists.isPresent()) {
        /* TODO: Revisar la modificación de un Entidades partiendo del dto y después pasárselo al repositorio */
            Entidades entity = entityExists.get();
                entity.setNroent(dto.getNroent());
                entity.setNoment(dto.getNoment());
                entity.setCodagrup(dto.getCodagrup());
                entity.setTpopro(dto.getTpopro());
                entity.setDisabled(dto.getDisabled());
            EntidadesDto updatedDto = mapper.fromEntity(repository.update(entity));

            return new DataEntidadesDto(updatedDto);            
        } else {
            throw new TechnicalException(GenericErrorMessageKey.ENTITY_ID_NOT_FOUND_KEY.getValue(),
                   Entidades.class.getSimpleName(), dto.getId());

        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public PagedDataResponseDto<EntidadesDto> getEntidadesPagedDataAndOrderedAndFiltered(EntidadesDto searchDto, Integer page, Integer size, Sort sort){

        Sort validateSort = SortValidator.validate(sort, EntidadesDto.class);
        if (page == null) {
            page = defaultPaginationValues.getDefaultPaginationPage();
        }
        if (size == null) {
            size = defaultPaginationValues.getDefaultPaginationSize();
        } else if (size.equals(-1)) {
            size =  defaultPaginationValues.getDefaultMaxPaginationSize();
        }

        /* TODO: Se deben de establecer los 'specs' que se consideren para la búsqueda partiendo del searchDto */
        Specification<Entidades> specs = null;
        Pageable pageable = PageRequest.of(page, size, validateSort);
        Page<Entidades> pageEntidades = repository.findEntidades(specs, pageable);
        List<EntidadesDto> dtos = mapper.fromEntityList(pageEntidades.getContent());
		return new PagedDataEntidadesDto(
                pagedDataResponseDtoMapper.from(pageEntidades, page, size, sort, dtos));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEntidades(Long id) {
        Optional<Entidades> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.delete(id);
        } else {
            throw new TechnicalException(GenericErrorMessageKey.ENTITY_ID_NOT_FOUND_KEY.getValue(),
                Entidades.class.getSimpleName(), id);
        }
    }
}