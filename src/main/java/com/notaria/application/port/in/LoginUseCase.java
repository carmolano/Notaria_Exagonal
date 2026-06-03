package com.notaria.application.port.in;

import com.notaria.application.service.dto.command.LoginCommand;
import com.notaria.domain.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public interface LoginUseCase {
    UserModel execute(@NotNull @Valid LoginCommand command);
}
