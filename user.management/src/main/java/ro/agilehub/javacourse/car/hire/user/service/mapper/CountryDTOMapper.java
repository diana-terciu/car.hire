package ro.agilehub.javacourse.car.hire.user.service.mapper;

import org.mapstruct.Mapper;

import ro.agilehub.javacourse.car.hire.api.model.CountryDTO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;

@Mapper(componentModel = "spring")
public interface CountryDTOMapper {

	CountryDTO toCountryDO(Country country);

	Country toCountry(CountryDTO countryDO);
}
