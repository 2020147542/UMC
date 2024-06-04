package umc.spring.service.StoreService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.api.request.MissionRequest;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.converter.*;
import umc.spring.domain.*;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.RegionHandler;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.*;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    // 나머지 요청에 대한 비즈니스 로직 처리
    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional // 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    public Store addStore(StoreRequest.StoreAddDto addRequest, Long regionId){

        Region region = regionRepository.findById(regionId).orElseThrow(
                () -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)
        );
        Store newStore = StoreConverter.toStore(addRequest);
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }

    @Override
    @Transactional
    public Review addReview(ReviewRequest.ReviewAddDto reviewRequest, Long userId, Long storeId){

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

    @Override
    @Transactional
    public Mission addMission(MissionRequest.MissionAddDto missionRequest, Long storeId){

        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        Mission mission = MissionConverter.toMission(missionRequest);
        mission.setStore(store);

        return missionRepository.save(mission);
    }
}
