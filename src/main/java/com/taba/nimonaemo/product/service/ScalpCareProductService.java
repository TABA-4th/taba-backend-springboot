package com.taba.nimonaemo.product.service;

import com.taba.nimonaemo.member.exception.AlreadyNameException;
import com.taba.nimonaemo.product.exception.ProductNotFoundException;
import com.taba.nimonaemo.product.model.dto.request.RequestInputCareDeviceDto;
import com.taba.nimonaemo.product.model.dto.request.RequestProductDto;
import com.taba.nimonaemo.product.model.dto.response.ResponseProductDto;
import com.taba.nimonaemo.product.model.entity.ProductProperty;
import com.taba.nimonaemo.product.model.entity.ScalpCareProduct;
import com.taba.nimonaemo.product.repository.ProductPropertyRepository;
import com.taba.nimonaemo.product.repository.ProductRepository;
import com.taba.nimonaemo.product.repository.ScalpCareProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class ScalpCareProductService {
    private final ProductPropertyRepository productPropertyRepository;

    private final ScalpCareProductRepository scalpCareProductRepository;

    private final ProductRepository productRepository;

    public List<ResponseProductDto> findShampoo(RequestProductDto requestDto) {
        List<ScalpCareProduct> scalpCareProductList = productRepository.findAllByShampooType(requestDto);
        System.out.println("샴푸 리포지토리 확인 "+scalpCareProductList.size());
        List<ResponseProductDto> result = new ArrayList<>();
        if (!scalpCareProductList.isEmpty()) {
            for(int i = 0; i < scalpCareProductList.size(); i++) {
                ResponseProductDto responseDto = ResponseProductDto.builder()
                        .scalpCareProduct(scalpCareProductList.get(i))
                        .build();
                result.add(responseDto);
            }
            return result;
        }
        else {
            throw new ProductNotFoundException();
        }
    }

    public List<ResponseProductDto> findCareDevice() {
        List<ScalpCareProduct> scalpCareProductList = productPropertyRepository.findByCareDevice();
        List<ResponseProductDto> result = new ArrayList<>();
        if (!scalpCareProductList.isEmpty()) {
            for(int i = 0; i < scalpCareProductList.size(); i++) {
                ResponseProductDto dto = ResponseProductDto.builder()
                        .scalpCareProduct(scalpCareProductList.get(i))
                        .build();
                result.add(dto);
            }
            return result;
        }
        else {
            throw new ProductNotFoundException();
        }
    }

    public void addCareDevice(RequestInputCareDeviceDto dto) {
        checkAlreadyName(dto.getName());

        ScalpCareProduct scalpCareProduct = ScalpCareProduct.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .purchaseUrl(dto.getPurchaseUrl())
                .build();

        scalpCareProductRepository.save(scalpCareProduct);

        ProductProperty productProperty = ProductProperty.builder()
                .dry(dto.getDry())
                .greasy(dto.getGreasy())
                .sensitive(dto.getSensitive())
                .dermatitis(dto.getDermatitis())
                .neutral(dto.getNeutral())
                .loss(dto.getLoss())
                .careDevice(dto.getCareDevice())
                .scalpCareProduct(scalpCareProduct)
                .build();

        productPropertyRepository.save(productProperty);
    }

    private void checkAlreadyName(String name) {
        Optional<ScalpCareProduct> alreadyProduct = scalpCareProductRepository.findByName(name);
        if (alreadyProduct.isPresent()){
            throw new AlreadyNameException();
        }
    }
}
