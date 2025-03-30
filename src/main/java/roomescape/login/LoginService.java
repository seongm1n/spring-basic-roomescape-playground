package roomescape.login;

import org.springframework.stereotype.Service;
import roomescape.auth.JwtTokenProvider;
import roomescape.member.Member;
import roomescape.member.MemberRepository;

@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.password());
        return jwtTokenProvider.generateToken(member);
    }

    public String getUserInfoFromToken(String token) {
        String memberName = jwtTokenProvider.getClaimValue(token, "name");
        Member member = memberRepository.findByName(memberName);
        return member.getName();
    }
}
