package com.yl.user.application.domain.req;

import com.yl.user.application.factory.DataLogFactory;
import com.yl.user.infrastructure.data.DataLog;
import lombok.Data;

/**
 * @author simple-zhang
 * @date 3/2/2023 5:01 PM
 */
@Data
public class DataLogInsertReq {

    private String detail;

    private Long sum;

    public DataLog toEntry() {
        DataLog dataLog = DataLogFactory.toEntry();
        dataLog.setDetail(this.detail);
        dataLog.setSum(sum);
        return dataLog;
    }
}
