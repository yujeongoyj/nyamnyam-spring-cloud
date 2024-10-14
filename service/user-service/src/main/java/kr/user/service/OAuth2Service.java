package kr.user.service;

import kr.nyamnyam.model.domain.NaverMemberInfo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface OAuth2Service {
    Mono<String> getAccessToken(String code);
    Mono<NaverMemberInfo> getUserInfo(String accessToken);
    Mono<String> handleNaverLogin(String code);
}
