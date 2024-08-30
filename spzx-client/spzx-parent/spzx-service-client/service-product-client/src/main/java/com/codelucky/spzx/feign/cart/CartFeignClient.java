package com.codelucky.spzx.feign.cart;

import com.codelucky.spzx.model.entity.h5.CartInfo;
import com.codelucky.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "service-cart")
public interface CartFeignClient {
    /**
     * 获取全部选中的购物项
     *
     * @return
     */
    @GetMapping("/getAllCkecked")
    Result<List<CartInfo>> getAllCkecked();
}
