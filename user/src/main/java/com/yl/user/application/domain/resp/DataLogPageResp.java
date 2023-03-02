package com.yl.user.application.domain.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author simple-zhang
 * @date 3/1/2023 2:05 PM
 */
@Data
public class DataLogPageResp {

    @ApiModelProperty(value = "primary key")
    private Long logId;

}
