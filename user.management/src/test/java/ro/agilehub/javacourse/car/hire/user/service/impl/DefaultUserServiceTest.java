package ro.agilehub.javacourse.car.hire.user.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.entity.UserEntity;
import ro.agilehub.javacourse.car.hire.user.repository.UserEntityRepository;
import ro.agilehub.javacourse.car.hire.user.service.DefaultUserService;
import ro.agilehub.javacourse.car.hire.user.service.mapper.UserDTOMapper;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

	@Mock
	private UserEntityRepository userRepository;

	@Mock
	private UserDTOMapper userDOMapper;

	@InjectMocks
	private DefaultUserService defaultUserService;

	@Test
	public void findAllUsers_whenEmpty_thenException() {
		List<UserEntity> repoUsers = Collections.emptyList();
		List<UserDTO> doUsers = Collections.emptyList();

		when(userRepository.findAll()).thenReturn(repoUsers);
		when(userDOMapper.toUserDTOList(repoUsers)).thenReturn(doUsers);

		assertThrows(NoSuchElementException.class, () -> defaultUserService.findAll());
	}

	@Test
	public void findAllUsers_whenUsers_thenResult() {
		UserDTO userDO = mock(UserDTO.class);
		List<UserDTO> doUsers = List.of(userDO);

		when(userDOMapper.toUserDTOList(any())).thenReturn(doUsers);

		assertEquals(1, defaultUserService.findAll().size());
	}
}
