package com.cloud.common.persist.vo;

import lombok.Data;

/**
 * @author xiaofang
 * @date 2022/9/3
 */
@Data
public class ConditionVo {
        private static final long serialVersionUID = -5099378457111419832L;
        /**
         * 数据库字段名
         */
        private String column;
        /**
         * 字段值
         */
        private String value;
        /**
         * 连接类型，如like,equals,gt,ge,lt,le
         */
        private String type;
    }
