package ro.agilehub.javacourse.car.hire.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UsersApi;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

@RestController
@PreAuthorize("hasAuthority('MANAGER')")
@RequiredArgsConstructor
public class UserController implements UsersApi {

	private final UserService userService;

	@Override
	public ResponseEntity<List<UserDTO>> getUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@Override
	public ResponseEntity<UserDTO> getUserById(Integer userId) {
		return ResponseEntity.ok(userService.findById(userId).get());
	}

	@Override
	public ResponseEntity<CreatedDTO> addUser(@Valid UserDTO userDTO) {
		Integer newUserId = userService.createNewUser(userDTO);
		CreatedDTO createdDTO = new CreatedDTO();
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO.id(newUserId));
	}

	@Override
	public ResponseEntity<Void> updateUser(Integer userId, @Valid UserDTO userDTO) {
		userService.patchUser(userId, userDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Void> deleteUserById(Integer id) {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
	
	
	
}
