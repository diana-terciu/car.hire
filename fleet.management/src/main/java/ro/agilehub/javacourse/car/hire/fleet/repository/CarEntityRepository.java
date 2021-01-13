package ro.agilehub.javacourse.car.hire.fleet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.agilehub.javacourse.car.hire.fleet.entity.CarEntity;

@Repository
public interface CarEntityRepository extends JpaRepository<CarEntity, Integer> {
	
	CarEntity findById(Long id);

	void deleteById(Long id);
	
	Optional<CarEntity> findByMakeIgnoreCase(String carMaker);

}
