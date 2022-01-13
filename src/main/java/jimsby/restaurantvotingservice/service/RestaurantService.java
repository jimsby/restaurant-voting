package jimsby.restaurantvotingservice.service;

import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.repository.MealRepository;
import jimsby.restaurantvotingservice.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RestaurantService {
    private MealRepository mealRepository;
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Meal save(Meal meal, int restId) {
        meal.setRestaurant(restaurantRepository.getById(restId));
        return mealRepository.save(meal);
    }
}
