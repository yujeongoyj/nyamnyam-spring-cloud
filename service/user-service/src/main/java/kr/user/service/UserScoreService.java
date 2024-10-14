package kr.user.service;

import kr.nyamnyam.model.domain.UserScore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserScoreService {
    Mono<UserScore> save(UserScore userScore);
    Flux<UserScore> findByScoreUserId(String scoreUserId);
    Mono<Double> calculateUserAverageScore(String scoreUserId);
}
