package com.yl.user.application.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yl.user.application.domain.req.DataLogInsertReq;
import com.yl.user.application.domain.req.DataLogPageReq;
import com.yl.user.application.domain.resp.DataLogPageResp;
import com.yl.user.application.service.IDataLogService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author simple-zhang
 * @date 3/1/2023 1:30 PM
 */
@Api(tags = "data log management")
@RestController
@RequestMapping("/data-log")
@RequiredArgsConstructor
public class DataLogController {

    private final IDataLogService dataLogService;

    @PostMapping("/page")
    public Page<DataLogPageResp> page(@RequestBody DataLogPageReq req) {
        return dataLogService.page(req);
    }

    @PutMapping("/init")
    public void init(@RequestParam Integer dataSize) {
        dataLogService.init(dataSize);
    }

    @PostMapping("/insert")
    public void insert(@RequestBody DataLogInsertReq req) {
        dataLogService.insert(req);
    }
}
