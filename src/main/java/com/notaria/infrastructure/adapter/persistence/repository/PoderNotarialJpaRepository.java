package com.notaria.infrastructure.adapter.persistence.repository;

import com.notaria.infrastructure.adapter.out.persistence.entity.PoderNotarialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PoderNotarialJpaRepository extends JpaRepository<PoderNotarialEntity, Long> {
    boolean existsByNumeroPoder(String numeroPoder);
}
