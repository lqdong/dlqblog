package com.dlq.blog.db;

/**
 * 读、写类型枚举
 * @author donglq
 * @date 2017/10/5 1:50
 */
public enum DBWRType {
    WRITE(1, "WRITE", "写"), READ(2, "READ", "读");

    DBWRType(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int code;

    public String name;

    public String desc;

}
