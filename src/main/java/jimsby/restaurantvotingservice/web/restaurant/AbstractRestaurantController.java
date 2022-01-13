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

    public ResponseEntity<Restaurant> getToday(int id){
        log.info("get restaurant {} today", id);
        return ResponseEntity.of(repository.getRestaurantById(id));
    }

    public List<Restaurant> getAllToday() {
        log.info("get restaurant All today");
        return repository.findAllWithMealsToday();
    }
}
