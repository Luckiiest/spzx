package com.codelucky.spzx.user.mapper;

import com.codelucky.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAddressMapper {

    /**
     * 根据用户id查询用户地址列表
     *
     * @param userId
     */
    List<UserAddress> findByUserId(Long userId);

    /**
     * 根据id获取地址信息
     *
     * @param id
     * @return
     */
    UserAddress getById(Long id);
}
