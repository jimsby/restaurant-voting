package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Query("select distinct m from Meal m " +
            "where m.date = current_date and m.restaurant.id = :id")
    List<Meal> findMealsByRestaurantToday(int id);

    @Query("select distinct m from Meal m " +
            "where m.date = :date and m.restaurant.id = :id")
    List<Meal> findMealsByRestaurantAndDate(int id, LocalDate date);

    @Query("SELECT m FROM Meal m WHERE m.id = :id and m.restaurant.id = :restId")
    Optional<Meal> get(int id, int restId);

    default Meal checkBelong(int id, int restId) {
        return get(id, restId).orElseThrow(
                () -> new RuntimeException("Meal id=" + id + " doesn't belong to User id=" + restId));
    }
}