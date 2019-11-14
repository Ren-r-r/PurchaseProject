package com.ryz.service.impl;

import com.ryz.entity.SysUsers;
import com.ryz.entity.SysUsersExample;
import com.ryz.mapper.SysUsersMapper;
import com.ryz.service.SysUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUsersServiceimpl implements SysUsersService {

    @Autowired(required = false)
    private SysUsersMapper SysUsersMapper;

    @Override
    public SysUsers login(SysUsers user) {
        SysUsersExample Example=new SysUsersExample();
        Example.createCriteria().andLoginIdEqualTo(user.getLoginId())
                                .andPasswordEqualTo(user.getPassword());
        List<SysUsers> sysUsers = SysUsersMapper.selectByExample(Example);
        return sysUsers.size()>0?sysUsers.get(0):null;
    }

    @Override
    public void updatepwd(SysUsers user) {
        SysUsersMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public SysUsers findById(long id) {
        return SysUsersMapper.selectByPrimaryKey(id);
    }
}
