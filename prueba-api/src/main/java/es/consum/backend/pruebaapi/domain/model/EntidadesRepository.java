package es.consum.backend.pruebaapi.domain.model;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import es.consum.backend.domain.domainutils.repository.AggregateRootRepository;


/**
 * Class for all operations related to Entidades entity.
 * 
 */
public interface EntidadesRepository extends AggregateRootRepository<Entidades> {

    /**
    * create a Entidades object
    * 
    * @param dto
    *
    * @return EntidadesDto
    */
    Entidades create(Entidades entity);
    
    /**
    * get a Entidades object
    * 
    * @param id
    *
    * @return EntidadesDto
    */
    Optional<Entidades> findById(Long id);
    
    /**
    * update a Entidades object
    * 
    * @param dto
    *
    * @return EntidadesDto
    */
    Entidades update(Entidades entity);
    
        /**
    * get a list of EntidadesDto objects
    * 
    * @param spec
    * @param pageable
    *
    * @return {@link Page<Entidades>}
    */
    Page<Entidades> findEntidades(Specification<Entidades> spec, Pageable pageable);
    
    /**
    * delete a Entidades object
    * 
    * @param id
    *
    * @return 
    */
    void delete(Long id);

}