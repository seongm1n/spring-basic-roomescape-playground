package roomescape.myreservation;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.auth.JwtTokenProvider;

import java.util.List;

@RestController
public class MyReservationController {

    private final MyReservationService myReservationService;
    private final JwtTokenProvider jwtTokenProvider;

    public MyReservationController(MyReservationService myReservationService, JwtTokenProvider jwtTokenProvider) {
        this.myReservationService = myReservationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/reservations-mine")
    public List<MyReservationResponse> getMyReservations(@CookieValue("token") String token) {
        String value = jwtTokenProvider.getClaimValue(token, "id");
        Long memberId = Long.valueOf(value);
        return myReservationService.findMyReservations(memberId);
    }
}
