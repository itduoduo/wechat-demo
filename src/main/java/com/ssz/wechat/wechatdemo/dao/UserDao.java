package com.ssz.wechat.wechatdemo.dao;


import com.ssz.wechat.wechatdemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserDao {

//    public User createUser(User user);
//
//    public void updateUser(User user);
//
//    public void deleteUser(Long userId);
//
//    public void correlationRoles(Long userId, Long... roleIds);
//
//    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

//    User findByUsername(String username);
//
//    Set<String> findRoles(String username);
//
//    Set<String> findPermissions(String username);
}
