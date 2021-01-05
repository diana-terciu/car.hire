package ro.agilehub.javacourse.car.hire.user.service.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.entity.UserEntity;

@Mapper(componentModel = "spring", uses = CountryDTOMapper.class)
public interface UserDTOMapper {

	UserDTO userToUserDto(UserEntity optional);

	UserEntity userDtoToUser(UserDTO userDto);

	List<UserDTO> toUserDTOList(List<UserEntity> userList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "username", ignore = true)
	@Mapping(target = "password", ignore = true)
	void patchUser(UserDTO userDO, @MappingTarget UserEntity user);
}
