package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Restaurant> getRestaurantById(int id);

    //@Query("select r from Restaurant r join fetch r.votes v join fetch r.meals m where r.id = ?1 and v.date = current_date and m.date = current_date")
    @Query("select r from Restaurant r join fetch r.votes v where r.id = :id and v.date = current_date")
    Optional<Restaurant> getWithVotes(int id);

    @Query("select r from Restaurant r join fetch r.meals m where r.id = :id and m.date = current_date")
    Optional<Restaurant> getWithMealsToday(int id);

    @Query("select r from Restaurant r join fetch r.meals m where r.id = :id and m.date = :date")
    Optional<Restaurant> getWithMealsFromDate(int id, LocalDate date);


    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select r from Restaurant r")
    List<Restaurant> getAllWithMeals();

    @Query("select distinct r from Restaurant r " +
            "join fetch r.meals m " +
            "where m.date = current_date ORDER BY r.name ASC")
    List<Restaurant> findAllWithMealsToday();
}
