package com.dlq.blog.db;

/**
 * 路由类型
 * @author donglq
 * @date 2017/10/4 20:34
 */
public enum DBRouteType {
    DB(1, "分库"),
    TABLE(2, "分表"),
    DBTABLE(3, "分库分表");

    DBRouteType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code;

    public String desc;

}
