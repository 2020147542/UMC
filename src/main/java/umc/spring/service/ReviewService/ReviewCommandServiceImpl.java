package umc.spring.service.ReviewService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.RegionHandler;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    // 나머지 요청에 대한 비즈니스 로직 처리
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review addReview(ReviewRequest.AddDto reviewRequest, Long userId, Long storeId){

        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );
        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        Review newReview = ReviewConverter.toReview(reviewRequest);
        newReview.setStore(store);
        newReview.setMember(member);

        return reviewRepository.save(newReview);
    }
}
