package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Region;
import umc.spring.domain.Store;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
