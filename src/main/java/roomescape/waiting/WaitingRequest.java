package roomescape.waiting;

import java.time.LocalDate;

public record WaitingRequest(Long theme, Long time, LocalDate date) {
}
