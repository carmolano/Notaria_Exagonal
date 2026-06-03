package com.notaria.infrastructure.adapter.persistence;


import com.notaria.domain.model.PoderNotarial;
import com.notaria.domain.port.out.PoderNotarialRepositoryPort;
import com.notaria.infrastructure.adapter.out.persistence.mapper.PoderNotarialMapper;
import com.notaria.infrastructure.adapter.out.persistence.repository.PoderNotarialJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class PoderNotarialRepositoryAdapter  implements PoderNotarialRepositoryPort {

    private final PoderNotarialJpaRepository jpaRepository;
    private final PoderNotarialMapper mapper;

    @Override
    public PoderNotarial guardar(PoderNotarial poder) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(poder)));
    }

    @Override
    public Optional<PoderNotarial> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<PoderNotarial> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorNumeroPoder(String numeroPoder) {
        return jpaRepository.existsByNumeroPoder(numeroPoder);
    }
}