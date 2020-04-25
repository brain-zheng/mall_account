package com.mall.account.dal.dao;


import com.mall.account.dal.dataobject.MallUserDO;
import com.mall.common.service.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserDAO {

    MallUserDO selectByLoginName(String loginName);

    int insertSelective(MallUserDO record);

    MallUserDO selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    MallUserDO selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(MallUserDO record);

    List<MallUserDO> findMallUserList(@Param("start") Integer start, @Param("limit") Integer limit, @Param("loginName") String loginName);

    int getTotalMallUsers(@Param("loginName") String loginName);

    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") int lockStatus);

}
