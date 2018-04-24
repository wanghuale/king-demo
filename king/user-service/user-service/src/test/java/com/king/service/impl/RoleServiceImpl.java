package com.king.service.impl;

import com.king.entity.Role;
import com.king.mapper.RoleMapper;
import com.king.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
}
