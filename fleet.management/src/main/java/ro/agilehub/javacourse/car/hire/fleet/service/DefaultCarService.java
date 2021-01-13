package ro.agilehub.javacourse.car.hire.fleet.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.entity.CarEntity;
import ro.agilehub.javacourse.car.hire.fleet.entity.CarStatus;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarEntityRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarDTOMapper;

@Service
@RequiredArgsConstructor
public class DefaultCarService implements CarService {
	private final CarDTOMapper carDTOMapper;

	private final CarEntityRepository carRepository;

	@Override
	public List<CarDTO> findAll() {
		List<CarDTO> result = carDTOMapper.toCarDTOList(carRepository.findAll());
		if (result.isEmpty()) {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public Optional<CarDTO> getCarById(Integer id) {
		return Optional.ofNullable(carDTOMapper.carToCarDto(carRepository.findById(id).orElseThrow()));
	}

	@Override
	public Integer createNewCar(CarDTO example) {
		CarEntity newCar = carDTOMapper.carDtoToCar(example);
		newCar = carRepository.save(newCar);
		return newCar.getId();
	}

	@Override
	public void patchCar(Integer id, CarDTO updateExample) {
		CarEntity car = findById(id);
		carDTOMapper.patchCar(updateExample, car);
		carRepository.save(car);
	}

	@Override
	public void deleteCar(Integer id) {
		CarEntity car = findById(id);
		car.setStatus(CarStatus.DELETED);
		carRepository.save(car);
	}

	private CarEntity findById(Integer id) {
		return carRepository.findById(id).orElseThrow();
	}

	@Override
	public CarDTO getByCarMaker(String carMaker) {
		return carDTOMapper.carToCarDto(carRepository.findByMakeIgnoreCase(carMaker).orElseThrow());
	}
}
