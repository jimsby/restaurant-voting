package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class RestaurantController extends AbstractRestaurantController {
    static final String REST_URL = "/api/restaurant";

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get restaurant {} with meals today", id);
        return ResponseEntity.of(repository.getRestaurantById(id));
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAllToday();
    }

    @GetMapping("/{id}/with-votes-today")
    public ResponseEntity<Restaurant> getWithVotes(@PathVariable int id) {
        log.info("get restaurant with votes {}", id);
        return ResponseEntity.of(repository.getWithVotes(id));
    }

}
