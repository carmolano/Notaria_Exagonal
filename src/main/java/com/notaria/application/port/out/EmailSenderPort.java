package com.notaria.application.port.out;

import com.notaria.domain.model.EmailDestinationModel;

public interface EmailSenderPort {
    void send(EmailDestinationModel destination);
}
