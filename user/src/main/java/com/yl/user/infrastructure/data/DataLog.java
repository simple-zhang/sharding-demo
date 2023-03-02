package com.yl.user.infrastructure.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @author simple-zhang
 * @date 3/1/2023 11:34 AM
 */
@Data
@TableName("data_log")
public class DataLog {

    @ApiModelProperty(value = "primary key")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "data size")
    private Long sum;

    @ApiModelProperty(value = "success size")
    private Long success;

    @ApiModelProperty(value = "fail size")
    private Long fail;

    @ApiModelProperty(value = "detail")
    private String detail;

    @ApiModelProperty(value = "create user id")
    private String createPersonId;

    @ApiModelProperty(value = "create time")
    private Date createTime;

    @ApiModelProperty(value = "delete or not")
    private Integer delFlag;

}
