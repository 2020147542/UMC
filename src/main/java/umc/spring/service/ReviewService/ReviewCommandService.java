package umc.spring.service.ReviewService;

import umc.spring.api.request.ReviewRequest;
import umc.spring.domain.Review;

public interface ReviewCommandService {
    Review addReview(ReviewRequest.AddDto reviewRequest, Long userId, Long storeId);
}
