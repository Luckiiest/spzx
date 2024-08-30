package com.codelucky.spzx.user.service;

import com.codelucky.spzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {
    /**
     * 查询用户地址列表
     *
     * @return
     */
    List<UserAddress> findUserAddressList();

    /**
     * 根据id获取地址信息
     * @param id
     * @return
     */
    UserAddress getById(Long id);
}
