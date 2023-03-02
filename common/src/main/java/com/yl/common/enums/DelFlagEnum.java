package com.yl.common.enums;

/**
 * @author simple-zhang
 * @date 3/1/2023 2:55 PM
 */
public enum DelFlagEnum {

    NO(0),
    YES(1),
    ;
    private Integer code;

    DelFlagEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
