package com.mall.account.service.service;

import com.mall.account.client.dto.MallUserDTO;
import com.mall.account.client.enums.DubboErrorEnum;
import com.mall.account.client.service.MallUserService;
import com.mall.account.service.manager.MallUserManager;
import com.mall.common.service.util.PageQueryUtil;
import com.mall.common.service.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("MallUserServiceImpl")
public class MallUserServiceImpl implements MallUserService {

    @Resource
    private MallUserManager mallUserManager;

    @Override
    public String register(String loginName, String password) {
        if (StringUtils.isEmpty(loginName)) {
            return DubboErrorEnum.LOGINNAME_IS_NULL.getResult();
        }
        if (StringUtils.isEmpty(password)) {
            return DubboErrorEnum.PASSWD_IS_NULL.getResult();
        }
        return mallUserManager.register(loginName, password);
    }

    @Override
    public MallUserDTO queryUserByLoginNameAndPasswd(String loginName, String password) {
        if (StringUtils.isEmpty(loginName)) {
            return null;
        }
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return mallUserManager.queryUserByLoginNameAndPasswd(loginName, password);
    }

    @Override
    public MallUserDTO selectByPrimaryKey(Integer userId) {
        if (userId < 0) {
            return null;
        }
        return mallUserManager.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(MallUserDTO record) {
        if (record == null) {
            return 0;
        }
        return mallUserManager.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageResult getMallUsersPage(PageQueryUtil pageUtil, String loginName) {
        if (pageUtil == null) {
            return null;
        }
        return mallUserManager.getMallUsersPage(pageUtil, loginName);
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1){
            return false;
        }
        return mallUserManager.lockUsers(ids, lockStatus);
    }
}
