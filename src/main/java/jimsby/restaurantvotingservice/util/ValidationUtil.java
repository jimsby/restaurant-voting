package jimsby.restaurantvotingservice.util;

import jimsby.restaurantvotingservice.HasId;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@UtilityClass
public class ValidationUtil {
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
