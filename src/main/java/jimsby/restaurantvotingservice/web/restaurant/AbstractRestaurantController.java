package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.Restaurant;
import jimsby.restaurantvotingservice.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public List<Restaurant> getAllToday() {
        log.info("get restaurant All today");
        return repository.findAllWithMealsToday();
    }
}
