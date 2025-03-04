package com.niu.service.impl;


import com.niu.dao.vo.UserVO;
import com.niu.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:14
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public Integer add(UserPara userPara) {

    Date date = new Date();
    userPara.setCreateTime(date);
    userPara.setUpdateTime(date);

    User user = new User();
    BeanUtils.copyProperties(userPara, user);
    return userMapper.insertSelective(user);
  }

  @Override
  public Integer update(UserPara userPara) {

    userPara.setUpdateTime(new Date());
    User user = new User();
    BeanUtils.copyProperties(userPara, user);

    UserExample example = new UserExample();
    example.createCriteria().andUuidEqualTo(userPara.getUuid());
    return userMapper.updateByExampleSelective(user, example);
  }

  @Override
  public Integer delete(UserPara userPara) {

    return userMapper.deleteByPrimaryKey(userPara.getUuid());
  }

  @Override
  public List<UserVO> select(UserPara userPara) {

    UserExample example = new UserExample();
    example.setOrderByClause("update_time desc");
    example.createCriteria().andAgeLessThan(userPara.getAge());

    List<User> userList = userMapper.selectByExample(example);
    List<UserVO> result = new ArrayList<>(userList.size());
    userList.forEach(t -> {
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(t, userVO);
      result.add(userVO);
    });

    return result;
  }

}
