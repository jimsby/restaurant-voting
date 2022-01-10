package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Integer> {
}
