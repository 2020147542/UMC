package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.exception.handler.TempHandler;
import umc.spring.repository.StoreRepository;
import umc.spring.service.TempService.TempQueryService;

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
