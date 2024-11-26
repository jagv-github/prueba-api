package es.consum.backend.pruebaapi.infrastructure.repository.jpa;

import es.consum.backend.domain.domainutils.repository.AggregateRootJpaPagedRepository;
import es.consum.backend.pruebaapi.domain.model.Entidades;




public interface EntidadesJpaRepository
extends AggregateRootJpaPagedRepository<Entidades, Long> {
}
