package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MealRepository extends JpaRepository<Meal, Integer> {

    Optional<Meal> getByDateEquals(LocalDate date);
}
