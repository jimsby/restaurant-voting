package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    //@Query("SELECT v from Vote v WHERE v.date = :date")
    List<Vote> getVotesByDateEquals(LocalDate date);

    @Query("SELECT COUNT (v) from Vote v WHERE v.date = :date and v.restaurant.id = :restaurant_id")
    Integer getVotesByRestaurantCurrentDate(LocalDate date, int restaurant_id);
}
