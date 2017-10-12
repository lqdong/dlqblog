package com.dlq.blog.db;

/**
 * 工具类，存放当前线程数据源key和表名后缀
 * 使用treadLocal的方式来保证线程安全
 * @author donglq
 * @date 2017/10/3 22:56
 */
public class DBContext {

    /**数据库逻辑名**/
    private static final ThreadLocal<String> dbKeyHolder = new ThreadLocal<String>();

    /**表明后缀**/
    private static final ThreadLocal<String> tableSuffixHolder = new ThreadLocal<String>();

    public static void setDbKey(String dbKey) {
        dbKeyHolder.set(dbKey);
    }

    public static String getDbKey() {
        return dbKeyHolder.get();
    }

    public static void clearDbKey() {
        dbKeyHolder.remove();
    }

    public static void setTableSuffix(String tableIndex){
        tableSuffixHolder.set(tableIndex);
    }

    public static String getTableSuffix(){
        return tableSuffixHolder.get();
    }
    public static void clearTableSuffix(){
        tableSuffixHolder.remove();
    }

}
