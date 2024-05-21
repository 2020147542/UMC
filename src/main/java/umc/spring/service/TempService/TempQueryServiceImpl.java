package umc.spring.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiFormat.code.status.ErrorStatus;
import umc.spring.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {

    // get 요청에 대한 비즈니스 로직 처리
    @Override
    public void CheckFlag(Integer flag){
        if(flag == 1){
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
