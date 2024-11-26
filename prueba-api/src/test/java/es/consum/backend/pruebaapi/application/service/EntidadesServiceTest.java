package es.consum.backend.pruebaapi.application.service;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import java.time.*;

import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import es.consum.backend.pruebaapi.application.service.impl.EntidadesServiceImpl;
import es.consum.backend.pruebaapi.domain.model.Entidades;
import es.consum.backend.pruebaapi.application.dto.EntidadesDto;
import es.consum.backend.pruebaapi.application.dto.EntidadesDtoMapper;
import es.consum.backend.pruebaapi.domain.model.EntidadesRepository;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EntidadesServiceTest {

    private static final Long FOUND_ENTIDADES_ID = 1L;


    // TODO: Establece para cada variable un valor distinto acorde a su tipo
    private static final Long TO_CREATE_ENTIDADES_ID = null;
    @InjectMocks
    private EntidadesServiceImpl service;

    @Mock
    private EntidadesRepository repository;

    @Mock
    private EntidadesDtoMapper mapper;

    EntidadesDto dto;
    Entidades entity;

    @Before
    public void configure(){
        MockitoAnnotations.openMocks(this);
        // Se deberían de asignar atributos tanto al DTO como a la ENTIDAD
        dto = new EntidadesDto();
         dto.setId(FOUND_ENTIDADES_ID);
        dto.setNroent(1);
        dto.setNoment("abc");
        dto.setCodagrup("abc");
        dto.setTpopro(1);
        dto.setDisabled(true);
        entity = Entidades.builder()
            .id(FOUND_ENTIDADES_ID)
            .nroent(1)
            .noment("abc")
            .codagrup("abc")
            .tpopro(1)
            .disabled(true)
            .build();
    }

    @Test
    public void shouldCreateEntidades(){
        when(repository.create(any(Entidades.class))).thenReturn(entity);
        when(mapper.fromEntity(any())).thenReturn(dto);
        assertThat(dto).isEqualTo(service.create(dto).getData());
    }

    @Test
    public void shouldGetEntidades(){
        when(repository.findById(FOUND_ENTIDADES_ID)).thenReturn(Optional.of(entity));
        when(mapper.fromEntity(any())).thenReturn(dto);
        assertThat(dto).isEqualTo(service.getEntidades(FOUND_ENTIDADES_ID).getData());
    }

    @Test
    public void shoulDeleteEntidades(){

        EntidadesDto dtoCreated = new EntidadesDto();
        dtoCreated.setId(TO_CREATE_ENTIDADES_ID);
        when(repository.findById(TO_CREATE_ENTIDADES_ID)).thenReturn(Optional.of(entity));
        service.deleteEntidades(dtoCreated.getId());
        verify(repository,times(1)).delete(TO_CREATE_ENTIDADES_ID);
        verify(repository,times(1)).findById(TO_CREATE_ENTIDADES_ID);
    }

    @Test
    public void shouldUpdateEntidades(){

        when(repository.findById(dto.getId())).thenReturn(Optional.of(entity));
        when(repository.update(any(Entidades.class))).thenReturn(entity);
        when(mapper.fromEntity(entity)).thenReturn(dto);
        assertThat(dto).isEqualTo(service.update(dto.getId(), dto).getData());


    }

}