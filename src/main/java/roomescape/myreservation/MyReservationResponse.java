package roomescape.myreservation;

import roomescape.reservation.Reservation;
import roomescape.waiting.Waiting;

import java.time.LocalDate;
import java.time.LocalTime;

public record MyReservationResponse(
        Long id,
        String theme,
        LocalDate date,
        LocalTime time,
        String status
) {
    public static MyReservationResponse from(Reservation reservation) {
        return new MyReservationResponse(
                reservation.getId(),
                reservation.getTheme().getName(),
                reservation.getDate(),
                reservation.getTime().getValue(),
                "예약"
        );
    }

    public static MyReservationResponse from(Waiting waiting) {
        return new MyReservationResponse(
                waiting.getId(),
                waiting.getTheme().getName(),
                waiting.getDate(),
                waiting.getTime().getValue(),
                "예약대기"
        );
    }
}
