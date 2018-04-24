package com.king.service.impl;

import com.king.entity.Resource;
import com.king.mapper.ResourceMapper;
import com.king.service.IResourceService;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
	
}
