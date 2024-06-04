package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.response.ReviewResponse;
import umc.spring.domain.mapping.Review;

import java.time.LocalDateTime;
import java.util.List;

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

    public static ReviewResponse.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResponse.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponse.ReviewListDTO toReviewListDTO(Page<Review> reviews) {

        List<ReviewResponse.ReviewPreviewDTO> reviewPreviewDTOList = reviews.stream()
                .map(ReviewConverter::toReviewPreviewDTO).toList();

        return ReviewResponse.ReviewListDTO.builder()
                .reviewList(reviewPreviewDTOList)
                .isFirst(reviews.isFirst())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .build();
    }
}
