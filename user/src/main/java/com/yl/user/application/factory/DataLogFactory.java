package com.yl.user.application.factory;

import com.yl.common.enums.DelFlagEnum;
import com.yl.user.infrastructure.data.DataLog;
import java.util.Date;

/**
 * @author simple-zhang
 * @date 3/1/2023 2:52 PM
 */
public class DataLogFactory {

    private DataLogFactory() {
    }

    public static DataLog toEntry() {
        DataLog dataLog = new DataLog();
        dataLog.setSum(0L);
        dataLog.setSuccess(0L);
        dataLog.setFail(0L);
        dataLog.setDetail("Detail");
        dataLog.setCreatePersonId("123");
        dataLog.setCreateTime(new Date());
        dataLog.setDelFlag(DelFlagEnum.NO.getCode());
        return dataLog;
    }
}
