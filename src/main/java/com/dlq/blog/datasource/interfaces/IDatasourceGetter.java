package com.dlq.blog.datasource.interfaces;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据源获取接口
 * @author donglq
 * @date 2017/10/4 23:20
 */
public interface IDatasourceGetter {

    JdbcTemplate getJdbcTemplate(String tablename);

}
