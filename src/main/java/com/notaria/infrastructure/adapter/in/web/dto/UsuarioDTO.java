package com.notaria.infrastructure.adapter.in.web.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UsuarioDTO {
    @Data
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email no tiene formato válido")
        private String email;

        @NotBlank(message = "La contraseña es obligatoria")
        private String password;

        private String rol;
    }

    @Data
    public static class Response {
        private Long id;
        private String nombre;
        private String email;
        private String rol;
    }

    @Data
    public static class LoginRequest {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;
    }

    @Data
    public static class LoginResponse {
        private String token;
        private String email;
        private String nombre;
        private String rol;

        public LoginResponse(String token, String email, String nombre, String rol) {
            this.token = token;
            this.email = email;
            this.nombre = nombre;
            this.rol = rol;
        }
    }
}

