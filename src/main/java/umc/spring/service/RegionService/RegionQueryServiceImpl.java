package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.service.MemberService.MemberQueryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionQueryServiceImpl implements RegionQueryService {

    private final RegionRepository regionRepository;

    // get 요청에 대한 비즈니스 로직 처리
    @Override
    public boolean isExistRegion(Long regionId){
        return regionRepository.existsById(regionId);
    }
}
