package com.dlq.blog.service;

import com.dlq.blog.dao.User;
import com.dlq.blog.dao.UserDao;
import com.dlq.blog.datasource.interfaces.IDatasourceGetter;
import com.dlq.blog.db.DBContext;
import com.dlq.blog.db.DBWRType;
import com.dlq.blog.db.annotation.DBWR;
import com.dlq.blog.db.annotation.Router;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Function;

/**
 * @author donglq
 * @date 2017/10/4 12:26
 */
@Service
public class UserService {

    @Resource
    UserDao userDao;

    @Resource
    IDatasourceGetter datasourceGetter;

    @Router(routerField = "idcard", ruleBeanName = "userRule")
    public String insert(User user) {
        user.setTableIndex(DBContext.getTableSuffix());
        userDao.insert(user);
        return "success";
    }

    @Router(routerField = "idcard", ruleBeanName = "userRule")
    public User get(User user) {
        user.setTableIndex(DBContext.getTableSuffix());
        return userDao.select(user);
    }

    public List<User> getByIdcard(String idcard) {
        //获取表明
        String tableName = new Function<String, String>() {
            @Override
            public String apply(String s) {
                String suffix = "";
                //共8张表，用身份证前6位计算位于哪张表
                int tableIndex = Integer.valueOf(s.substring(0, 6)) % 8;
                DecimalFormat df = new DecimalFormat();
                df.applyPattern("_00");
                suffix = df.format(tableIndex);
                return "user" + suffix;
            }
        }.apply(idcard);
        List<User> users = datasourceGetter.getJdbcTemplate(tableName).query("select * from " + tableName + " where idcard='" + idcard + "'", new RowMapper<User>() {
            @Nullable
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println("idcard: " + rs.getString("idcard"));
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setIdcard(rs.getString("idcard"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        });
        return users;
    }

    @DBWR(DBWRType.WRITE)
    public Object write(User user) {
        return userDao.insert(user);
    }

    @DBWR(DBWRType.READ)
    public User read(User user) {
        return userDao.select(user);
    }

}
