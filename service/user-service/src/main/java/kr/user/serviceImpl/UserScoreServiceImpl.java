package kr.user.serviceImpl;

import kr.nyamnyam.model.domain.UserScore;
import kr.nyamnyam.model.repository.UserScoreRepository;
import kr.nyamnyam.service.UserScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserScoreServiceImpl implements UserScoreService {

    private final UserScoreRepository userScoreRepository;

    @Override
    public Mono<UserScore> save(UserScore userScore) {
        return userScoreRepository.save(userScore);
    }

    @Override
    public Flux<UserScore> findByScoreUserId(String scoreUserId) {
        return userScoreRepository.findByScoreUserId(scoreUserId);
    }

    @Override
    public Mono<Double> calculateUserAverageScore(String scoreUserId) {
        return findByScoreUserId(scoreUserId)
                .map(UserScore::getScore)
                .collectList()
                .map(scores -> scores.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0));
    }
}
