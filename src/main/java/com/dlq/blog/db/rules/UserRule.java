package com.dlq.blog.db.rules;

import com.dlq.blog.db.DBRouteType;
import com.dlq.blog.db.interfaces.DBRule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户分库分表规则
 * @author donglq
 * @date 2017/10/4 21:21
 */
@Component("userRule")
public class UserRule implements DBRule {

    @Override
    public List<String> getDbKeys() {
        List<String> list = new ArrayList<>(4);
        list.add("user_00");
        list.add("user_01");
        list.add("user_02");
        list.add("user_03");
        return list;
    }

    @Override
    public int getDbCount() {
        return 4;
    }

    @Override
    public int getTableCount() {
        return 2;
    }

    @Override
    public String getTableSuffixStyle() {
        return "_00";
    }

    @Override
    public DBRouteType getDBRouteType() {
        return DBRouteType.DBTABLE;
    }

    /**
     * 根据身份证前6位分库分表
     * @param resource
     * @return
     */
    @Override
    public int getResourceCode(Object resource) {
        return Integer.valueOf(((String)resource).substring(0, 6));
    }
}
