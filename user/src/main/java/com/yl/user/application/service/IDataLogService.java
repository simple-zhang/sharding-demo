package com.yl.user.application.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yl.user.application.domain.req.DataLogInsertReq;
import com.yl.user.application.domain.req.DataLogPageReq;
import com.yl.user.application.domain.resp.DataLogPageResp;
import com.yl.user.infrastructure.data.DataLog;

/**
 * @author simple-zhang
 * @date 3/1/2023 2:07 PM
 */
public interface IDataLogService extends IService<DataLog> {

    /**
     * page
     *
     * @param req req
     * @return {@link Page}<{@link DataLogPageResp}>
     */
    Page<DataLogPageResp> page(DataLogPageReq req);

    /**
     * data init
     *
     * @param dataSize per data size
     */
    void init(Integer dataSize);

    /**
     * insert
     *
     * @param req insertReq
     */
    void insert(DataLogInsertReq req);
}
