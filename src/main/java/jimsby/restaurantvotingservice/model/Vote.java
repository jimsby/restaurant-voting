package jimsby.restaurantvotingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jimsby.restaurantvotingservice.HasId;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date", "restaurant_id"}, name = "vote_unique_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Vote extends BaseEntity implements HasId {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference (value = "restaurant")
    @ToString.Exclude
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference (value = "user")
    @ToString.Exclude
    private User user;

    public Vote(Restaurant restaurant, User user){
        this();
        this.date = LocalDate.now();
        this.restaurant = restaurant;
        this.user = user;
    }
}
