package com.notaria.infraestructure.adapter.email;
import com.notaria.infrastructure.adapter.email.SmtpConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmailConfiguration {

    @Bean
    public SmtpConfig smtpConfig(
            @Value("${mail.smtp.host}") String host,
            @Value("${mail.smtp.port}") int port,
            @Value("${mail.smtp.username}") String username,
            @Value("${mail.smtp.password}") String password,
            @Value("${mail.smtp.from-address}") String fromAddress,
            @Value("${mail.smtp.from-name}") String fromName) {
        return new SmtpConfig(host, port, username, password, fromAddress, fromName);
    }
}
