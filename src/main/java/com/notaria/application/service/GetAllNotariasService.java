package com.notaria.application.service;

import com.notaria.application.in.GetAllNotariasUseCase;
import com.notaria.application.out.GetAllNotariasPort;
import com.notaria.domain.model.NotariaModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllNotariasService implements GetAllNotariasUseCase {

    private final GetAllNotariasPort getAllNotariasPort;

    @Override
    public List<NotariaModel> execute() {
        return getAllNotariasPort.getAll();
    }
}