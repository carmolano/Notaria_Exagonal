package com.notaria.infrastructure.adapter.persistence.mapper;


import com.notaria.domain.model.PoderNotarial;
import com.notaria.infrastructure.adapter.out.persistence.entity.PoderNotarialEntity;
import org.springframework.stereotype.Component;

@Component
public class PoderNotarialMapper {

    public PoderNotarial toDomain(PoderNotarialEntity entity) {
        return PoderNotarial.builder()
                .id(entity.getId())
                .numeroPoder(entity.getNumeroPoder())
                .tipoPoder(entity.getTipoPoder())
                .poderdante(entity.getPoderdante())
                .apoderado(entity.getApoderado())
                .facultades(entity.getFacultades())
                .fechaOtorgamiento(entity.getFechaOtorgamiento())
                .fechaVencimiento(entity.getFechaVencimiento())
                .estado(entity.getEstado())
                .notario(entity.getNotario())
                .usuarioId(entity.getUsuarioId())
                .build();
    }

    public PoderNotarialEntity toEntity(PoderNotarial domain) {
        return PoderNotarialEntity.builder()
                .id(domain.getId())
                .numeroPoder(domain.getNumeroPoder())
                .tipoPoder(domain.getTipoPoder())
                .poderdante(domain.getPoderdante())
                .apoderado(domain.getApoderado())
                .facultades(domain.getFacultades())
                .fechaOtorgamiento(domain.getFechaOtorgamiento())
                .fechaVencimiento(domain.getFechaVencimiento())
                .estado(domain.getEstado())
                .notario(domain.getNotario())
                .usuarioId(domain.getUsuarioId())
                .build();
    }
}

