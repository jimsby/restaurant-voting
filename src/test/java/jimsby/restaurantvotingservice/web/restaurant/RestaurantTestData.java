package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.model.Restaurant;
import jimsby.restaurantvotingservice.model.Vote;
import jimsby.restaurantvotingservice.to.RestaurantTo;
import jimsby.restaurantvotingservice.web.MatcherFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.LocalDate.now;
import static jimsby.restaurantvotingservice.web.user.UserTestData.user;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "user");
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int MEAL1_ID = 1;
    public static final int VOTE1_ID = 1;
    public static final LocalDate OLD_DATE = of(2022, Month.JANUARY, 10);

    public static final Meal meal1 = new Meal(MEAL1_ID + 1, "Первое блюдо - суп", 20, now());
    public static final Meal meal2 = new Meal(MEAL1_ID + 2, "Второе блюдо - каша", 20, now());
    public static final Meal meal3 = new Meal(MEAL1_ID + 3, "Первое блюдо - солянка", 20, now());
    public static final Meal meal4 = new Meal(MEAL1_ID + 4, "Второе блюдо - плов", 20, now());
    public static final Meal meal5 = new Meal(MEAL1_ID + 5, "Первое блюдо - старый суп", 20, OLD_DATE);
    public static final Meal meal6 = new Meal(MEAL1_ID + 6, "Второе блюдо - старая каша", 20, OLD_DATE);



    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Restaurant 3*", List.of(meal1, meal2));
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "MICHELIN-starred Restaurant", List.of(meal3, meal4, meal5, meal6));

    public static final Vote vote1 = new Vote(VOTE1_ID + 1, OLD_DATE,restaurant2, user);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, now(),restaurant1, user);

    static {
        restaurant1.setVotes(List.of(vote2));
        restaurant2.setVotes(List.of(vote1));
    }
}
