package umc.spring.service.RegionService;

import umc.spring.domain.Member;
import umc.spring.domain.Region;

import java.util.Optional;

public interface RegionQueryService {
    boolean isExistRegion(Long regionId);
}
