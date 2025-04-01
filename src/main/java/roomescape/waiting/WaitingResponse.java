package roomescape.waiting;

import java.time.LocalDate;

public record WaitingResponse(Long id, Long theme, Long time, LocalDate date) {
}
