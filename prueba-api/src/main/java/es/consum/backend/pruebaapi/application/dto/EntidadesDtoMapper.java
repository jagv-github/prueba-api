package es.consum.backend.pruebaapi.application.dto;

import java.util.List;

import org.mapstruct.Mapper;

import es.consum.backend.pruebaapi.domain.model.Entidades;

@Mapper(componentModel = "spring")
public interface EntidadesDtoMapper {

    EntidadesDto fromEntity(Entidades entity);

    List<EntidadesDto> fromEntityList(List<Entidades> entities);

}