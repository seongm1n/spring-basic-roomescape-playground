package roomescape.myreservation;

import roomescape.reservation.Reservation;

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
}
