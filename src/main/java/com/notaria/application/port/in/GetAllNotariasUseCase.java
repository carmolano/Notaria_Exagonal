package com.notaria.application.port.in;

import com.notaria.domain.NotariaModel;
import java.util.List;

public interface GetAllNotariasUseCase {
    List<NotariaModel> execute();
}
