package com.notaria.application.port.out;



import com.notaria.domain.model.NotariaModel;
import com.notaria.domain.valueobject.NotariaNit;
import java.util.Optional;

public interface GetNotariaByNitPort {
    Optional<NotariaModel> getByNit
📁 Notaria_API (Collection)
  📁 Users Management
    ├─ POST - Create User
    ├─ GET - Get User by ID
    ├─ PUT - Update User
    ├─ DELETE - Delete User
    ├─ GET - List All Users
    └─ POST - Login User (si existe el endpoint)

  📁 Notaria Management (para tu TablaXyz)
    ├─ POST - Create Notaria
    ├─ GET - Get Notaria by ID
    ├─ PUT - Update Notaria
    ├─ DELETE - Delete Notaria
    └─ GET - List All NotariasMethod: POST
URL: {{base_url}}/api/users
Body (JSON):
{
  "id": "usr-001",
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "password": "SecurePass123",
  "role": "ADMIN"
}Method: GET
URL: {{base_url}}/api/usersMethod: PUT
URL: {{base_url}}/api/users/usr-001
Body (JSON):
{
  "name": "Juan Pérez Actualizado",
  "email": "juan.nuevo@example.com",
  "password": "NewSecurePass456",
  "role": "USER",
  "status": "ACTIVE"
}Method: DELETE
URL: {{base_url}}/api/users/usr-001}
