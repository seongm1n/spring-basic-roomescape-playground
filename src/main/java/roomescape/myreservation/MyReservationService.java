package roomescape.myreservation;

import org.springframework.stereotype.Service;
import roomescape.reservation.ReservationRepository;

import java.util.List;

@Service
public class MyReservationService {

    private final ReservationRepository reservationRepository;

    public MyReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<MyReservationResponse> findMyReservations(Long memberId) {
        return reservationRepository.findByMemberId(memberId).stream()
                .map(MyReservationResponse::from)
                .toList();
    }
}
