package ro.agilehub.javacourse.car.hire.user.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.entity.UserEntity;
import ro.agilehub.javacourse.car.hire.user.entity.UserStatus;
import ro.agilehub.javacourse.car.hire.user.repository.UserEntityRepository;
import ro.agilehub.javacourse.car.hire.user.service.mapper.UserDTOMapper;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
	private final UserDTOMapper userDOMapper;

	private final UserEntityRepository userRepository;

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> result = userDOMapper.toUserDTOList(userRepository.findAll());
		if (result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public Optional<UserDTO> findById(Integer id) {
		return Optional.ofNullable(userDOMapper.userToUserDto(getUserById(id)));
	}

	private UserEntity getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public Integer createNewUser(UserDTO example) {
		UserEntity newUser = userDOMapper.userDtoToUser(example);
		newUser = userRepository.save(newUser);
		return newUser.getId();
	}

	@Override
	public void patchUser(Integer id, UserDTO updateExample) {
		UserEntity user = getUserById(id);
		userDOMapper.patchUser(updateExample, user);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		UserEntity user = getUserById(id);
		user.setStatus(UserStatus.DELETED);
		userRepository.save(user);
	}

	@Override
	public UserDTO getByUsername(String username) {
		return userDOMapper.userToUserDto(userRepository.findByUsernameIgnoreCase(username).orElseThrow());
	}

}
