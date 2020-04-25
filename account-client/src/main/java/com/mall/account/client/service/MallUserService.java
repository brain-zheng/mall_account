package com.mall.account.client.service;


import com.mall.account.client.dto.MallUserDTO;
import com.mall.common.service.util.PageQueryUtil;
import com.mall.common.service.util.PageResult;


public interface MallUserService {

    String register(String loginName, String password);

    MallUserDTO queryUserByLoginNameAndPasswd(String loginName, String password);

    MallUserDTO selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(MallUserDTO record);

    PageResult getMallUsersPage(PageQueryUtil pageUtil, String loginName);

    Boolean lockUsers(Integer[] ids, int lockStatus);

}
