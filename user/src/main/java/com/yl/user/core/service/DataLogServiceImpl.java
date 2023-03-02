package com.yl.user.core.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yl.user.application.constant.DataLogConstant;
import com.yl.user.application.domain.req.DataLogInsertReq;
import com.yl.user.application.domain.req.DataLogPageReq;
import com.yl.user.application.domain.resp.DataLogPageResp;
import com.yl.user.application.factory.DataLogFactory;
import com.yl.user.application.service.IDataLogService;
import com.yl.user.infrastructure.dao.DataLogMapper;
import com.yl.user.infrastructure.data.DataLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author simple-zhang
 * @date 3/1/2023 2:07 PM
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataLogServiceImpl extends ServiceImpl<DataLogMapper, DataLog> implements IDataLogService {

    private final DataLogMapper dataLogMapper;

    @Override
    public Page<DataLogPageResp> page(DataLogPageReq req) {
        QueryWrapper<DataLog> pageQuery = new QueryWrapper<>();
        pageQuery.le(Objects.nonNull(req.getMaxCreateDate()), "create_Time", req.getMaxCreateDate())
                .gt(Objects.nonNull(req.getMinCreateDate()), "create_Time", req.getMinCreateDate());
        Page<DataLog> page = dataLogMapper.selectPage(new Page<>(req.getCurrentPage(), req.getPerSize()), pageQuery);
        List<DataLogPageResp> list = page.getRecords().stream()
                .map(e -> {
                    DataLogPageResp resp = new DataLogPageResp();
                    BeanUtils.copyProperties(e, resp);
                    return resp;
                })
                .collect(Collectors.toList());
        Page<DataLogPageResp> respPage = new Page<>();
        BeanUtils.copyProperties(page, respPage);
        respPage.setRecords(list);
        return respPage;
    }

    @Override
    public void init(Integer dataSize) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("%s-data-log-init").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(DataLogConstant.PER_DATA_SIZE), factory);

        for (int i = 0; i < dataSize; i++) {
            CompletableFuture.runAsync(() -> {
                List<DataLog> list = new ArrayList<>(DataLogConstant.PER_DATA_SIZE);
                for (int j = 0; j < DataLogConstant.PER_DATA_SIZE; j++) {
                    DataLog dataLog = DataLogFactory.toEntry();
                    list.add(dataLog);
                }
                this.saveBatch(list);
            }, executor);
        }
    }

    @Override
    public void insert(DataLogInsertReq req) {
        log.info("insert log,data:{}", JSON.toJSONString(req.toEntry()));
        baseMapper.insert(req.toEntry());
    }
}
