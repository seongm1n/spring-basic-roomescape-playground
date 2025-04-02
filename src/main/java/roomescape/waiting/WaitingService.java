package roomescape.waiting;

import org.springframework.stereotype.Service;
import roomescape.exception.InvalidReservationException;
import roomescape.member.LoginMember;
import roomescape.theme.Theme;
import roomescape.theme.ThemeRepository;
import roomescape.time.Time;
import roomescape.time.TimeRepository;

@Service
public class WaitingService {

    private final WaitingRepository waitingRepository;
    private final ThemeRepository themeRepository;
    private final TimeRepository timeRepository;

    public WaitingService(WaitingRepository waitingRepository, ThemeRepository themeRepository, TimeRepository timeRepository) {
        this.waitingRepository = waitingRepository;
        this.themeRepository = themeRepository;
        this.timeRepository = timeRepository;
    }

    public WaitingResponse save(WaitingRequest waitingRequest, LoginMember loginMember) {
        Theme theme = themeRepository.findById(waitingRequest.theme())
                .orElseThrow(InvalidReservationException::invalidTheme);
        Time time = timeRepository.findById(waitingRequest.time())
                .orElseThrow(InvalidReservationException::invalidTime);

        Waiting waiting = new Waiting(
                loginMember.getId(),
                theme,
                time,
                waitingRequest.date()
        );

        Waiting savedWaiting = waitingRepository.save(waiting);
        return new WaitingResponse(savedWaiting.getId(), savedWaiting.getTheme().getId(), savedWaiting.getTime().getId(), savedWaiting.getDate());
    }

    public void deleteById(Long id) {
        waitingRepository.deleteById(id);
    }
}
