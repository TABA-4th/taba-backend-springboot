package com.taba.nimonaemo.product.controller;

import com.taba.nimonaemo.product.exception.ProductNotFoundException;
import com.taba.nimonaemo.product.model.dto.request.RequestInputCareDeviceDto;
import com.taba.nimonaemo.product.model.dto.request.RequestProductDto;
import com.taba.nimonaemo.product.model.dto.response.ResponseProductDto;
import com.taba.nimonaemo.product.service.ScalpCareProductService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "두피 케어 상품 정보", description = "두피 케어 상품 관련 api")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ScalpCareProductController {
    private final ScalpCareProductService scalpCareProductService;

    /**
     * 두피 타입에 해당하는 샴푸
     *
     * @param dto           해당하는 두피 타입 요청 DTO
     * @return              타입에 맞는 샴푸 (모두 false 이면, 전체 샴푸 목록 return)
     */
    @PostMapping("/shampoo")
    public List<ResponseProductDto> shampoo(@Valid @RequestBody RequestProductDto dto) {
        return scalpCareProductService.findShampoo(dto);
    }

    /**
     * 두피 케어 기기
     *
     * @return              두피 케어 기기
     */
    @GetMapping("/device")
    public List<ResponseProductDto> careDevice() {
        return scalpCareProductService.findCareDevice();
    }

    /**
     * 두피 케어 제품 추가
     *
     * @param dto           요청 body
     */
    @PostMapping("/input")
    public void inputCareDevice(@Valid @RequestBody RequestInputCareDeviceDto dto) {
        scalpCareProductService.addCareDevice(dto);
    }
}
