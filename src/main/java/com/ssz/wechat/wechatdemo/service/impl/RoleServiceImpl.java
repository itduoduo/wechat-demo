package com.ssz.wechat.wechatdemo.service.impl;

import com.ssz.wechat.wechatdemo.dao.RoleDao;
import com.ssz.wechat.wechatdemo.domain.Role;
import com.ssz.wechat.wechatdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


//    public Role createRole(Role role) {
//        return roleDao.createRole(role);
//    }
//
//    public void deleteRole(Long roleId) {
//        roleDao.deleteRole(roleId);
//    }
//
//    /**
//     * 添加角色-权限之间关系
//     * @param roleId
//     * @param permissionIds
//     */
//    public void correlationPermissions(Long roleId, Long... permissionIds) {
//        roleDao.correlationPermissions(roleId, permissionIds);
//    }
//
//    /**
//     * 移除角色-权限之间关系
//     * @param roleId
//     * @param permissionIds
//     */
//    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
//        roleDao.uncorrelationPermissions(roleId, permissionIds);
//    }

}
