package com.codelucky.spzx.user.service.impl;

import com.codelucky.spzx.model.entity.user.UserAddress;
import com.codelucky.spzx.user.mapper.UserAddressMapper;
import com.codelucky.spzx.user.service.UserAddressService;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 查询用户地址列表
     *
     * @return
     */
    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<UserAddress> userAddressList = userAddressMapper.findByUserId(userId);
        return userAddressList;
    }

    /**
     * 根据id获取地址信息
     *
     * @param id
     * @return
     */
    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.getById(id);
    }
}
