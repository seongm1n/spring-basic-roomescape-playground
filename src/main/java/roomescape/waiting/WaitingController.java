package roomescape.waiting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/waitings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        waitingService.deleteById(id);
    }
}
