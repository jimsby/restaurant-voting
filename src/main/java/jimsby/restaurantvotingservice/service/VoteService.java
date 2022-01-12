package jimsby.restaurantvotingservice.service;

import jimsby.restaurantvotingservice.repository.RestaurantRepository;
import jimsby.restaurantvotingservice.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteService {
    private RestaurantRepository restaurantRepository;
    private VoteRepository voteRepository;



}
