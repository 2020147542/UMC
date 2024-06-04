package umc.spring.service.StoreService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.api.request.MemberRequest;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.StoreResponse;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.exception.handler.FoodCategoryHandler;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.RegionHandler;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.*;
import umc.spring.service.MemberService.MemberCommandService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    // 나머지 요청에 대한 비즈니스 로직 처리
    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional // 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    public Store addStore(StoreRequest.AddDto addRequest, Long regionId){

        Region region = regionRepository.findById(regionId).orElseThrow(
                () -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)
        );
        Store newStore = StoreConverter.toStore(addRequest);
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }

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
