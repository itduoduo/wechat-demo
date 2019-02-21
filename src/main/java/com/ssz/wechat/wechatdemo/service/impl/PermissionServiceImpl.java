package com.ssz.wechat.wechatdemo.service.impl;


import com.ssz.wechat.wechatdemo.dao.PermissionDao;
import com.ssz.wechat.wechatdemo.domain.Permission;
import com.ssz.wechat.wechatdemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

//    public Permission createPermission(Permission permission) {
//        return permissionDao.createPermission(permission);
//    }
//
//    public void deletePermission(Long permissionId) {
//        permissionDao.deletePermission(permissionId);
//    }
}
