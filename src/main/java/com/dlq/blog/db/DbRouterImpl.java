package com.dlq.blog.db;

import com.dlq.blog.db.interfaces.DBRouter;
import com.dlq.blog.db.interfaces.DBRule;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * DB路由实现，确定库名和表名
 * @author donglq
 * @date 2017/10/3 23:53
 */
@Service("dBRouter")
public class DbRouterImpl implements DBRouter {

    /**
     * 根据dbRule和resource确定库表，并存入上下文中
     * @param dbRule 分库分表的一些规则信息
     * @param resource 用来做分库分表规则的字段值
     * @return
     */
    @Override
    public String doRouteByResource(DBRule dbRule, Object resource) {
        String dbKey = null;
        int resourceCode = dbRule.getResourceCode(resource);
        if (dbRule.getDbKeys() != null && dbRule.getDbCount() > 0) {
            long dbIndex = 0;
            long tbIndex = 0;
            if (dbRule.getDBRouteType() == DBRouteType.DBTABLE && dbRule.getTableCount() > 0) {
                //分库分表
                tbIndex = resourceCode % (dbRule.getDbCount() * dbRule.getTableCount());
                String tableIndex = getFormateTableIndex(dbRule.getTableSuffixStyle(), tbIndex);
                DBContext.setTableSuffix(tableIndex);
                dbIndex = tbIndex % dbRule.getDbCount();
                dbKey = dbRule.getDbKeys().get(Long.valueOf(dbIndex).intValue());
                DBContext.setDbKey(dbKey);
            } else if (dbRule.getDBRouteType() == DBRouteType.DB) {
                //分库
                DBContext.setTableSuffix("");
                dbIndex = resourceCode % dbRule.getDbCount();
                dbKey = dbRule.getDbKeys().get(Long.valueOf(dbIndex).intValue());
                DBContext.setDbKey(dbKey);
            } else if (dbRule.getDBRouteType() == DBRouteType.TABLE) {
                //分表
                tbIndex = resourceCode % dbRule.getTableCount();
                String tableIndex = getFormateTableIndex(dbRule.getTableSuffixStyle(), tbIndex);
                DBContext.setTableSuffix(tableIndex);
                DBContext.setDbKey("");
            }
        }
        return dbKey;
    }

    /**
     * @Description 格式化表名后缀，将1格式化为_01
     */
    private static String getFormateTableIndex(String style, long tbIndex) {
        String tableIndex = null;
        DecimalFormat df = new DecimalFormat();
        if (StringUtils.isEmpty(style)) {
            return "";
        }
        df.applyPattern(style);
        tableIndex = df.format(tbIndex);
        return tableIndex;
    }

}
