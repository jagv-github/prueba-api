package es.consum.backend.pruebaapi.infrastructure.repository;

import java.util.Optional;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import es.consum.backend.pruebaapi.domain.model.Entidades;
import es.consum.backend.pruebaapi.domain.model.EntidadesRepository;
import es.consum.backend.pruebaapi.infrastructure.repository.jpa.EntidadesJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class EntidadesRepositoryImpl implements EntidadesRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EntidadesJpaRepository jpaRepository;

    protected EntidadesRepositoryImpl() {
    // Default constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Entidades> findById(Long id) {
        return jpaRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Entidades> findEntidades(Specification<Entidades> spec, Pageable pageable) {
        return jpaRepository.findAll(spec, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entidades create(Entidades entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entidades update(Entidades entity) {
        return jpaRepository.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
