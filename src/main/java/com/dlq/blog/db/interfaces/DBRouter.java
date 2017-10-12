package com.dlq.blog.db.interfaces;

/**
 * DB路由接口，通过调用该接口来自动判断数据位于哪个库和表
 * @author donglq
 * @date 2017/10/3 23:51
 */
public interface DBRouter {

    public String doRouteByResource(DBRule dbRule, Object resource);

}
