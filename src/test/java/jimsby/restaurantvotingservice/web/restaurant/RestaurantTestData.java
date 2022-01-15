package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.*;
import jimsby.restaurantvotingservice.to.RestaurantTo;
import jimsby.restaurantvotingservice.web.MatcherFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.LocalDate.now;
import static jimsby.restaurantvotingservice.web.user.UserTestData.user;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "votes", "meals");
    public static final MatcherFactory.Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "restaurant");
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int MEAL1_ID = 1;
    public static final int VOTE1_ID = 1;
    public static final LocalDate OLD_DATE = of(2022, Month.JANUARY, 10);
    public static final LocalDate NOW_DATE = LocalDate.now();

    public static final Meal meal1 = new Meal(MEAL1_ID, "Суп Том-Ям", 20, now());
    public static final Meal meal2 = new Meal(MEAL1_ID + 1, "Рол с лососем", 30, now());
    public static final Meal meal3 = new Meal(MEAL1_ID + 2, "Яблочный смузи", 15, now());
    public static final Meal meal4 = new Meal(MEAL1_ID + 3, "Гороховый суп", 5, now());
    public static final Meal meal5 = new Meal(MEAL1_ID + 4, "Пельмени", 10, now());
    public static final Meal meal6 = new Meal(MEAL1_ID + 5, "Компот", 5, now());
    public static final Meal meal7 = new Meal(MEAL1_ID + 6, "Старая_еда", 5, OLD_DATE);

    public static final List<Meal> rest1_meal = List.of(meal1, meal2, meal3);
    public static final List<Meal> rest2_meal = List.of(meal4, meal5, meal6, meal7);


    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Grand Resort", rest1_meal);
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Bad Resort", rest2_meal);

    public static final Vote vote1 = new Vote(VOTE1_ID, now(), restaurant1, user);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, OLD_DATE, restaurant2, user);

    static {
        restaurant1.setVotes(List.of(vote1));
        restaurant2.setVotes(List.of(vote2));
    }

    public static Restaurant getNewRest() {
        return new Restaurant(null, "NewRestaurant", null);
    }

    public static Restaurant getUpdatedRest() {
        return new Restaurant(RESTAURANT1_ID, "RestaurantUpdatedName", null);
    }

    public static Meal getNewMeal() {
        return new Meal(null, "NewMeal", 20, NOW_DATE);
    }

    public static Meal getUpdatedMeal() {
        return new Meal(MEAL1_ID, "MealUpdatedName", 30, NOW_DATE);
    }
}
