package com.hg.tmzm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hg.tmzm.common.domain.BaseEntity;
import com.hg.tmzm.common.domain.BaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 基础Service实现
 *
 * @author hg
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {
}
