package com.notaria.infraestructure.adapter.persistence.repository;

import com.notaria.infraestructure.adapter.persistence.entity.NotariaEntity;
import com.notaria.infrastructure.adapter.persistence.entity.NotariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface SpringDataNotariaRepository extends JpaRepository<NotariaEntity, Long> {
    Optional<NotariaEntity> findByNit(String nit);
    Optional<NotariaEntity> findByEmail(String email);
}
