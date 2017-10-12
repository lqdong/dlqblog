package com.dlq.blog.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 动态获取当前数据源
 * @author donglq
 * @date 2017/10/3 22:59
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取当前数据源
     * @return
     */
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        String dbKey = DBContext.getDbKey();
        System.out.println("dbKey: " + dbKey);
        return dbKey;
    }
}
