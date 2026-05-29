package com.notaria.application.port.out;
import com.notaria.domain.model.UserModel;
import java.util.List;

public interface GetAllNotariasPort {
    List<UserModel> getALL();
}
