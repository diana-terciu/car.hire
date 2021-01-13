package ro.agilehub.javacourse.car.hire.fleet.service;

import java.util.List;
import java.util.Optional;

import ro.agilehub.javacourse.car.hire.api.model.CarDTO;

public interface CarService {

	List<CarDTO> findAll();

	 Optional<CarDTO> getCarById(Integer id);

    void deleteCar(Integer id);

    CarDTO getByCarMaker(String carMaker);

	Integer createNewCar(CarDTO example);

	void patchCar(Integer id, CarDTO updateExample);

}
