package com.notaria.domain.model;

import com.notaria.domain.enums.NotariaCategoria;
import com.notaria.domain.valueobject.NotariaId;
import com.notaria.domain.valueobject.NotariaName;
import com.notaria.domain.valueobject.NotariaNit;
import com.notaria.domain.valueobject.NotariaDireccion;
import com.notaria.domain.valueobject.NotariaEmail;
import lombok.Value;

@Value
public class UserModel {
    NotariaId id;
    NotariaName name;
    NotariaNit nit;
    NotariaDireccion direccion;
    NotariaEmail email;
    NotariaCategoria categoria;

    public static NotariaModel create(
            final NotariaId id,
            final NotariaName name,
            final NotariaNit nit,
            final NotariaDireccion direccion,
            final NotariaEmail email,
            final NotariaCategoria categoria) {
        return new NotariaModel(id, name, nit, direccion, email, categoria);
    }
}
