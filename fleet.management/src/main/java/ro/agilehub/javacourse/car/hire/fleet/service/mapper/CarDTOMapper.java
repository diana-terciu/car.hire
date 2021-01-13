package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.CarEntity;

@Mapper(componentModel = "spring")
public interface CarDTOMapper {

	CarDTO carToCarDto(CarEntity optional);

	CarEntity carDtoToCar(CarDTO carDto);

	List<CarDTO> toCarDTOList(List<CarEntity> carList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	void patchCar(CarDTO carDTO, @MappingTarget CarEntity car);
}
