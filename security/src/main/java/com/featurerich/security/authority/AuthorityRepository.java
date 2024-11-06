package com.featurerich.security.authority;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository
        extends CrudRepository<Authority, String>, PagingAndSortingRepository<Authority, String> {

    @Query(
            "SELECT a.id, a.version, a.name, a.updated_at, a.updated_by FROM authorities a INNER"
                    + " JOIN role_authorities ra ON a.id = ra.authority_id INNER JOIN roles r ON"
                    + " ra.role_id = r.id INNER JOIN user_roles ur ON ur.role_id = r.id WHERE"
                    + " ur.user_id = :userId")
    Collection<Authority> findByUserId(final String userId);

    Page<Authority> findById(String id, Pageable pageable);

    Page<Authority> findByName(String name, Pageable pageable);
}
