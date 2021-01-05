package ro.agilehub.javacourse.car.hire.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ro.agilehub.javacourse.car.hire.user.entity.UserEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private static final String USERNAME = "Username";

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Before
	public void setUp() {
		var user = new UserEntity();
		user.setUsername(USERNAME);

		testEntityManager.persist(user);
	}

	@Test
	public void findByName_whenNoMatch_returnEmpty() {
		var foundUser = userEntityRepository.findByUsernameIgnoreCase("another");

		assertTrue(foundUser.isEmpty());
	}

	@Test
	public void findByName_whenMatch_return() {
		var foundUser = userEntityRepository.findByUsernameIgnoreCase(USERNAME.toLowerCase());

		assertTrue(foundUser.isPresent());
		assertEquals(USERNAME, foundUser.get().getUsername());
	}
}
