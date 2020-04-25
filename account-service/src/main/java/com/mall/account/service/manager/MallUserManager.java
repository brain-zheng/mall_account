package com.mall.account.service.manager;

import com.mall.account.client.dto.MallUserDTO;
import com.mall.account.dal.dao.MallUserDAO;
import com.mall.account.dal.dataobject.MallUserDO;
import com.mall.account.service.converter.MallUserConverter;
import com.mall.common.service.enums.ServiceResultEnum;
import com.mall.common.service.util.MD5Util;
import com.mall.common.service.util.PageQueryUtil;
import com.mall.common.service.util.PageResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MallUserManager {

    @Resource
    private MallUserDAO mallUserDAO;

    public String register(String loginName, String password) {
        if (mallUserDAO.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        MallUserDO registerUser = new MallUserDO();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (mallUserDAO.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    public MallUserDTO queryUserByLoginNameAndPasswd(String loginName, String password) {
        MallUserDO mallUserDO = mallUserDAO.selectByLoginNameAndPasswd(loginName, password);
        return MallUserConverter.mallUserDO2DTO(mallUserDO);
    }

    public MallUserDTO selectByPrimaryKey(Integer userId) {
        MallUserDO mallUserDO = mallUserDAO.selectByPrimaryKey(userId);
        return MallUserConverter.mallUserDO2DTO(mallUserDO);

    }

    public int updateByPrimaryKeySelective(MallUserDTO record) {
        MallUserDO mallUserDO = MallUserConverter.mallUserDTO2DO(record);
        return mallUserDAO.updateByPrimaryKeySelective(mallUserDO);
    }

    public PageResult getMallUsersPage(PageQueryUtil pageUtil, String loginName) {
        List<MallUserDO> mallUsers = mallUserDAO.findMallUserList(pageUtil.getStart(), pageUtil.getLimit(), loginName);
        int total = mallUserDAO.getTotalMallUsers(loginName);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        return mallUserDAO.lockUserBatch(ids, lockStatus) > 0;
    }

}
