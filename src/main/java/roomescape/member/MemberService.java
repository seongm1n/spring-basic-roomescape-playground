package roomescape.member;

import org.springframework.stereotype.Service;
import roomescape.auth.JwtTokenProvider;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member = memberRepository.save(new Member(memberRequest.getName(), memberRequest.getEmail(), memberRequest.getPassword(), "USER"));
        return new MemberResponse(member.getId(), member.getName(), member.getEmail());
    }

    public Member findMemberByToken(String token) {
        String value = jwtTokenProvider.getClaimValue(token, "id");
        Long id = null;
        if (value != null) {
            id = Long.valueOf(value);
        }
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found for the given token"));
    }
}
