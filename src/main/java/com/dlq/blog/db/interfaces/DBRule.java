package com.dlq.blog.db.interfaces;

import com.dlq.blog.db.DBRouteType;

import java.util.List;

/**
 * 分库分表规则
 * @author donglq
 * @date 2017/10/4 20:33
 */
public interface DBRule {

    /**
     * 数据源逻辑名
     * @return
     */
    List<String> getDbKeys();

    /**
     * 库数量
     * @return
     */
    int getDbCount();

    /**
     * 每个库中表数量
     * @return
     */
    int getTableCount();

    /**
     * 表后缀样式，如_00
     * @return
     */
    String getTableSuffixStyle();

    /**
     * 路由类型：分库、分表、分库分表
     * @return
     */
    DBRouteType getDBRouteType();

    /**
     * 根据参数值获取用来计算分库分表的数值
     * @param resource
     * @return
     */
    int getResourceCode(Object resource);

}
