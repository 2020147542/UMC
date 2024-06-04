package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.api.response.ReviewResponse;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.Review;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    boolean isExistStore(Long storeId);
    Optional<Store> findStore(Long storeId);
    Page<Review> getReviewList(Long StoreId, Integer page);
}
