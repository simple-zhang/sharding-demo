package com.yl.user.application.domain.req;

import com.yl.common.value.BasePage;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author simple-zhang
 * @date 3/1/2023 2:05 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataLogPageReq extends BasePage {

    @ApiModelProperty(value = "primary key")
    private Long logId;

    @ApiModelProperty(value = "min create time")
    private Date minCreateDate;

    @ApiModelProperty(value = "max create time")
    private Date maxCreateDate;
}
