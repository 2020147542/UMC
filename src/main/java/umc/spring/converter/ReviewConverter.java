package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.response.ReviewResponse;
import umc.spring.domain.Review;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReviewConverter {

    public static ReviewResponse.AddResultDTO toAddResultDTO(Review review) {
        return ReviewResponse.AddResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequest.ReviewAddDto addRequest) {

        return Review.builder()
                .title(addRequest.getTitle())
                .score(addRequest.getScore())
                .build();
    }
}
