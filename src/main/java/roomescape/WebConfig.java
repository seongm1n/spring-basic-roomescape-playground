package roomescape;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import roomescape.admin.AdminInterceptor;
import roomescape.auth.JwtTokenProvider;
import roomescape.login.LoginMemberArgumentResolver;
import roomescape.member.MemberService;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public WebConfig(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver(memberService));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor(jwtTokenProvider))
                .addPathPatterns("/admin/**");
    }
}
