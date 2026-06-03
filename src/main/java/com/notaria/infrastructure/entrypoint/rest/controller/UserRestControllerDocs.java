package com.notaria.infraestructure.entrypoint.rest.controller;

import com.notaria.infrastructure.entrypoint.web.dto.CreateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.UpdateUserRequest;
import com.notaria.infrastructure.entrypoint.web.dto.response.ApiErrorResponse;
import com.notaria.infrastructure.entrypoint.web.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Users", description = "Gestión de usuarios: crear, consultar, actualizar y elimin

public interface UserRestControllerDocs {

    @Operation(
            summary = "Crear usuario",
            description =
                    "Registra un nuevo usuario en el sistema. "
                            + "El ID debe ser único y el correo no puede estar en uso. "
                            + "Se enviará un correo de bienvenida con las credenciales al registrarlo.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos (campos requeridos vacíos o formato incorrecto).",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Ya existe un usuario registrado con ese correo electrónico.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    UserResponse create(@Valid @RequestBody CreateUserRequest request);



    @Operation(
            summary = "Listar todos los usuarios",
            description = "Retorna la lista completa de usuarios ordenada alfabéticamente por nombre.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de usuarios obtenida exitosamente. Puede ser vacía.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    List<UserResponse> getAll();

    /

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Retorna los datos de un usuario específico identificado por su ID único.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe ningún usuario con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    UserResponse getById(
            @Parameter(description = "ID único del usuario.", example = "usr-001", required = true)
            @PathVariable
            String id);



    @Operation(
            summary = "Actualizar usuario",
            description =
                    "Actualiza los datos de un usuario existente. "
                            + "Si se cambia el correo, este no debe estar en uso por otro usuario. "
                            + "Se enviará un correo de notificación de actualización.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe ningún usuario con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "El nuevo correo ya está en uso por otro usuario.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    UserResponse update(
            @Parameter(description = "ID único del usuario a actualizar.", example = "usr-001", required = true)
            @PathVariable
            String id,
            @Valid @RequestBody UpdateUserRequest request);



    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina permanentemente un usuario del sistema por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente."),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe ningún usuario con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    void delete(
            @Parameter(description = "ID único del usuario a eliminar.", example = "usr-001", required = true)
            @PathVariable
            String id);
}
