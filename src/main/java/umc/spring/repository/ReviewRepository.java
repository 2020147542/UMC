package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
