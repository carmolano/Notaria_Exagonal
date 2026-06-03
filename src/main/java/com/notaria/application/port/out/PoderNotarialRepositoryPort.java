package com.notaria.application.port.out;

import com.notaria.domain.model.PoderNotarial;

import java.util.List;
import java.util.Optional;

public interface PoderNotarialRepositoryPort {
    PoderNotarial guardar(PoderNotarial poder);
    Optional<PoderNotarial> buscarPorId(Long id);
    List<PoderNotarial> buscarTodos();
    void eliminar(Long id);
    boolean existePorNumeroPoder(String numeroPoder);
}