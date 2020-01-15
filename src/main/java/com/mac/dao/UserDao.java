package com.mac.dao;

import com.mac.vo.dto.UserPermissionDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author yog
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 通过用户id查询权限对应code
     * @param userId 用户id
     * @return 对应权限集合
     */
    List<UserPermissionDto> findPermissionCodeByUserId(String userId);
}
