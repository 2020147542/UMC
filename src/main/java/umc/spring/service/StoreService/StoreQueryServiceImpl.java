package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.api.response.ReviewResponse;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.Review;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    // get 요청에 대한 비즈니스 로직 처리
    @Override
    public boolean isExistStore(Long storeId){
        return storeRepository.existsById(storeId);
    }

    @Override
    public Optional<Store> findStore(Long storeId){
        return storeRepository.findById(storeId);
    }

    // jap에서 페이징을 위한 추상화 제공
    @Override
    public Page<Review> getReviewList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
}
