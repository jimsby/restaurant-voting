package jimsby.restaurantvotingservice.service;

import jimsby.restaurantvotingservice.model.Vote;
import jimsby.restaurantvotingservice.repository.RestaurantRepository;
import jimsby.restaurantvotingservice.repository.UserRepository;
import jimsby.restaurantvotingservice.repository.VoteRepository;
import jimsby.restaurantvotingservice.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class VoteService {
    private RestaurantRepository restaurantRepository;
    private VoteRepository voteRepository;
    private UserRepository userRepository;

    public RestaurantTo get(int rest_id){
        return new RestaurantTo(
        restaurantRepository.getWithMeals(rest_id).get(),
                voteRepository.getVotesCountByRestaurantToday(rest_id));
    }


    public RestaurantTo getForDate(int rest_id, LocalDate date) {
        return new RestaurantTo(
                restaurantRepository.getWithMealsFromDate(rest_id, date).get(),
                voteRepository.getVotesCountByRestaurantCurrentDate(rest_id, date),
                date);
    }

    @Transactional
    public Vote vote(int rest_id, int id) {
        Vote created = new Vote(restaurantRepository.getById(rest_id), userRepository.getById(id));
        if(LocalTime.now().isBefore(LocalTime.of(11, 0))){
            return voteRepository.save(created);
        }else return voteRepository.get(id);
    }
}
