package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    //@Query("SELECT v from Vote v WHERE v.date = :date")
    List<Vote> getVotesByDateEquals(LocalDate date);
}
