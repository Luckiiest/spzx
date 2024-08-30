package com.codelucky.spzx.user.controller;

import com.codelucky.spzx.model.entity.user.UserAddress;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.user.service.UserAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/userAddress")
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    /**
     * /api/user/userAddress/auth/findUserAddressList
     * 查询用户地址列表
     *
     * @return
     */
    @GetMapping("/auth/findUserAddressList")
    public Result findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * /api/user/userAddress/getUserAddress/{id}
     * 根据id获取地址信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserAddress/{id}")
    public Result<UserAddress> getUserAddress(@PathVariable Long id) {
        UserAddress userAddress = userAddressService.getById(id);
        return Result.build(userAddress, ResultCodeEnum.SUCCESS);
    }

}
