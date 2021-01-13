package ro.agilehub.javacourse.car.hire.fleet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
import ro.agilehub.javacourse.car.hire.api.specification.CarsApi;
import ro.agilehub.javacourse.car.hire.fleet.service.CarService;

@RestController
@PreAuthorize("hasAuthority('MANAGER')")
@RequiredArgsConstructor
public class CarController implements CarsApi {

	private final CarService carService;

	@Override
	public ResponseEntity<List<CarDTO>> getCars() {
		return ResponseEntity.ok(carService.findAll());
	}

	@Override
	public ResponseEntity<CarDTO> getCarById(Integer carId) {
		return ResponseEntity.ok(carService.getCarById(carId).get());
	}

	@Override
	public ResponseEntity<CreatedDTO> addCar(@Valid CarDTO carDTO) {
		Integer newCarId = carService.createNewCar(carDTO);
		CreatedDTO createdDTO = new CreatedDTO();
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO.id(newCarId));
	}

	@Override
	public ResponseEntity<Void> updateCar(Integer carId, @Valid CarDTO carDTO) {
		carService.patchCar(carId, carDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Void> deleteCarById(Integer id) {
		carService.deleteCar(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
}
