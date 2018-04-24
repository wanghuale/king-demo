package com.king.service.impl;

import com.king.entity.User;
import com.king.mapper.UserMapper;
import com.king.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanghuay
 * @since 2017-07-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
}
