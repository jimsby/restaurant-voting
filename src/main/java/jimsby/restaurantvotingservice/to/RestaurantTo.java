package jimsby.restaurantvotingservice.to;

import jimsby.restaurantvotingservice.model.Meal;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo{

    Integer currentVote;

    LocalDate currentDate;

    List<Meal> meals;

    public RestaurantTo(Integer id, String name, Integer currentVote, LocalDate currentDate, List<Meal> meals) {
        super(id, name);
        this.currentVote = currentVote;
        this.currentDate = currentDate;
        this.meals = meals;
    }
}
