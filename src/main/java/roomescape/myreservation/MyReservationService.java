package roomescape.myreservation;

import org.springframework.stereotype.Service;
import roomescape.reservation.ReservationRepository;
import roomescape.waiting.WaitingRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class MyReservationService {

    private final ReservationRepository reservationRepository;
    private final WaitingRepository waitingRepository;

    public MyReservationService(ReservationRepository reservationRepository, WaitingRepository waitingRepository) {
        this.reservationRepository = reservationRepository;
        this.waitingRepository = waitingRepository;
    }

    public List<MyReservationResponse> findMyReservations(Long memberId) {
        List<MyReservationResponse> reservation =  reservationRepository.findByMemberId(memberId).stream()
                .map(MyReservationResponse::from)
                .toList();

        List<MyReservationResponse> waiting = waitingRepository.findWaitingByMemberId(memberId).stream()
                .map(MyReservationResponse::from)
                .toList();

        return Stream.concat(reservation.stream(), waiting.stream())
                .toList();
    }
}
