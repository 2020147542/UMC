package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.StoreResponse;
import umc.spring.domain.Store;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StoreConverter {

    public static StoreResponse.AddResultDTO toAddResultDTO(Store store) {
        return StoreResponse.AddResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequest.StoreAddDto addRequest) {

        return Store.builder()
                .name(addRequest.getName())
                .address(addRequest.getAddress())
                .description(addRequest.getDescription())
                .build();
    }
}
