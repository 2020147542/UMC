package umc.spring.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.StoreResponse;
import umc.spring.api.response.common.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {



    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponse.AddResultDTO> add(
            @RequestBody StoreRequest.AddDto addRequest,
            @ExistRegion @Valid @PathVariable Long regionId
    ){
        Store store = storeCommandService.addStore(addRequest, regionId);
        return ApiResponse.onSuccess(StoreConverter.toAddResultDTO(store));
    }
}
