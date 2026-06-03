package com.notaria.application.usecase.usuario;


import com.notaria.domain.model.PoderNotarial;
import com.notaria.domain.port.in.PoderNotarialServicePort;
import com.notaria.domain.port.out.PoderNotarialRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoderNotarialUseCase implements PoderNotarialServicePort {

    private final PoderNotarialRepositoryPort poderNotarialRepositoryPort;

    @Override
    public PoderNotarial crearPoderNotarial(PoderNotarial poderNotarial) {
        return poderNotarialRepositoryPort.guardar(poderNotarial);
    }

    @Override
    public Optional<PoderNotarial> obtenerPoderNotarialPorId(Long id) {
        return poderNotarialRepositoryPort.buscarPorId(id);
    }

    @Override
    public List<PoderNotarial> listarPoderesNotariales() {
        return poderNotarialRepositoryPort.buscarTodos();
    }

    @Override
    public PoderNotarial actualizarPoderNotarial(Long id, PoderNotarial poderNotarial) {
        PoderNotarial existente = poderNotarialRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Poder notarial no encontrado con id: " + id));
        // Actualizar propiedades según sea necesario
        return poderNotarialRepositoryPort.guardar(existente);
    }

    @Override
    public void eliminarPoderNotarial(Long id) {
        poderNotarialRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Poder notarial no encontrado con id: " + id));
        poderNotarialRepositoryPort.eliminar(id);
    }
}
