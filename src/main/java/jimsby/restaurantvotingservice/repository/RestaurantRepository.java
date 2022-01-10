package jimsby.restaurantvotingservice.repository;

import jimsby.restaurantvotingservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
