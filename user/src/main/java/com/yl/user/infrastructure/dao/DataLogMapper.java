package com.yl.user.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yl.user.infrastructure.data.DataLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author simple-zhang
 * @date 3/1/2023 11:38 AM
 */
@Mapper
public interface DataLogMapper extends BaseMapper<DataLog> {
}
