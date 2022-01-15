package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.model.Restaurant;
import jimsby.restaurantvotingservice.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT COUNT (v) from Vote v WHERE v.date = :date and v.restaurant.id = :restaurant_id")
    long getVotesCountByRestaurantCurrentDate(int restaurant_id, LocalDate date);

    @Query("SELECT COUNT (v) from Vote v WHERE v.date = current_date and v.restaurant.id = :restaurant_id")
    long getVotesCountByRestaurantToday(int restaurant_id);


    @Query("SELECT v FROM Vote v join fetch v.user u WHERE u.id = :id and v.date = current_date")
    Vote get(int id);

    @Query("SELECT v FROM Vote v join fetch v.restaurant r join fetch v.user WHERE r.id = :id and v.date = current_date")
    List<Vote> getByRest(int id);
}
