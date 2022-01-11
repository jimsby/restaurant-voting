package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MealRepository extends JpaRepository<Meal, Integer> {
}
