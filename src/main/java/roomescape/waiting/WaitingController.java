package roomescape.waiting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roomescape.member.LoginMember;

@RestController
public class WaitingController {

    private final WaitingService waitingService;

    public WaitingController(WaitingService waitingService) {
        this.waitingService = waitingService;
    }

    @PostMapping("/waitings")
    @ResponseStatus(HttpStatus.CREATED)
    public WaitingResponse create(@RequestBody WaitingRequest waitingRequest, LoginMember loginMember) {
        return waitingService.save(waitingRequest, loginMember);
    }
}
