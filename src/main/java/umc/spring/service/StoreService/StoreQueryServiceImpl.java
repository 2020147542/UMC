package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    // get 요청에 대한 비즈니스 로직 처리
    @Override
    public boolean isExistStore(Long storeId){
        return storeRepository.existsById(storeId);
    }
}
