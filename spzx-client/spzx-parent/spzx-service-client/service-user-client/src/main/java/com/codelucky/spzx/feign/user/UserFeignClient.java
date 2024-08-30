package com.codelucky.spzx.feign.user;

import com.codelucky.spzx.model.entity.user.UserAddress;
import com.codelucky.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    Result<UserAddress> getUserAddress(@PathVariable Long id);
}
