package jimsby.restaurantvotingservice.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.model.Restaurant;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    LocalDate currentDate;

    long voteCount;

    List<Meal> meals;


    public RestaurantTo(Integer id, String name, LocalDate currentDate, List<Meal> meals, long voteCount) {
        super(id, name);
        this.currentDate = currentDate;
        this.meals = meals;
        this.voteCount = voteCount;
    }

    public RestaurantTo(Restaurant restaurant, long voteCount, LocalDate date) {
        this(restaurant.getId(), restaurant.getName(), date, restaurant.getMeals(), voteCount);
    }

    public RestaurantTo(Restaurant restaurant, long voteCount) {
        this(restaurant, voteCount, LocalDate.now());
    }
}
