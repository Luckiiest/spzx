package com.codelucky.spzx.user.mapper;

import com.codelucky.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {

    /**
     * 保存用户
     *
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 查找用户
     *
     * @param username
     * @return
     */
    UserInfo getByUsername(@Param("username") String username);

    /**
     * 更新登录信息
     *
     * @param userInfo
     */
    void updateById(UserInfo userInfo);
}
