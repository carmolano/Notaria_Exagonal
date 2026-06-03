package com.notaria.application.port.in;


import com.notaria.domain.model.PoderNotarial;

import java.util.List;
import java.util.Optional;



public interface PoderNotarialServicePort {
    PoderNotarial crearPoder(PoderNotarial poder);
    Optional<PoderNotarial> obtenerPoderPorId(Long id);
    List<PoderNotarial> listarPoderes();
    PoderNotarial actualizarPoder(Long id, PoderNotarial poder);
    void eliminarPoder(Long id);
}
