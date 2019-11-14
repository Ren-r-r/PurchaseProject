package com.ryz.service;

import com.ryz.entity.SysUsers;

/**
 * 用户接口
 */
public interface SysUsersService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    SysUsers login(SysUsers user);

    /**
     * 修改用户密码
     * @param user
     */
    void updatepwd(SysUsers user);

    /**
     * 根据用户id查询
     * @param id
     * @return
     */
    SysUsers findById(long id);
}
