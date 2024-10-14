package kr.user.repository;

import kr.nyamnyam.model.domain.UsersThumbnail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserThumbnailRepository extends ReactiveMongoRepository<UsersThumbnail, String> {
    Mono<UsersThumbnail> findByUserId(String userId);
}
