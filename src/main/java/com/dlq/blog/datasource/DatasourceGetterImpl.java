package com.dlq.blog.datasource;

import com.dlq.blog.datasource.interfaces.IDatasourceGetter;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * 数据源获取实现
 * @author donglq
 * @date 2017/10/4 23:20
 */
public class DatasourceGetterImpl implements IDatasourceGetter{

    /**数据源**/
    private static JdbcTemplate[] templates;

    /**默认数据源**/
    private static JdbcTemplate defaultTemplate;

    private final static DatasourceIndexFunction DATASOURCE_INDEX_FUNCTION = new DatasourceIndexFunction();

    /**
     * 初始化
     * @param datasources 多数据源，有序
     * @param defaultdataSource
     */
    public DatasourceGetterImpl(DataSource[] datasources, DataSource defaultdataSource) {
        Preconditions.checkNotNull(datasources);
        Preconditions.checkNotNull(defaultdataSource);
        this.defaultTemplate = new JdbcTemplate(defaultdataSource);
        this.templates = new JdbcTemplate[datasources.length];
        for (int i = 0; i < datasources.length; i++) {
            this.templates[i] = new JdbcTemplate(datasources[i]);
        }
    }

    /**
     * 数据源获取类
     * 根据表名获取JdbcTemplate
     */
    private static class DatasourceIndexFunction implements Function<String, JdbcTemplate> {
        @Override
        public JdbcTemplate apply(String input) {
            if (Strings.isNullOrEmpty(input)) {
                return defaultTemplate;
            }
            try {
                int digit = Integer.parseInt(CharMatcher.DIGIT.retainFrom(input));
                return templates[digit % templates.length];
            } catch (NumberFormatException e) {
            }
            return defaultTemplate;
        }
    }

    /**
     * 缓存表名对应的库JdbcTemplate
     */
    private static final LoadingCache<String, JdbcTemplate> cache = CacheBuilder.newBuilder().maximumSize(1024).build(
            new CacheLoader<String, JdbcTemplate>() {
                @Override
                public JdbcTemplate load(String key) throws Exception {
                    return DATASOURCE_INDEX_FUNCTION.apply(key);
                }
            }
    );

    /**
     * 通过表名获取JdbcTemplate
     * @param tablename
     * @return
     */
    @Override
    public JdbcTemplate getJdbcTemplate(String tablename) {
        try {
            return cache.get(tablename);
        } catch (ExecutionException e) {
        }
        return defaultTemplate;
    }

}
