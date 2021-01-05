package ro.agilehub.javacourse.car.hire.user.service;

import java.util.List;
import java.util.Optional;

import ro.agilehub.javacourse.car.hire.api.model.UserDTO;

public interface UserService {

	List<UserDTO> findAll();

	 Optional<UserDTO> findById(Integer id);

    void deleteUser(Integer id);

    UserDTO getByUsername(String username);

	Integer createNewUser(UserDTO example);

	void patchUser(Integer id, UserDTO updateExample);

}
