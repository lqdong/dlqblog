package com.dlq.blog.home;

import com.alibaba.fastjson.JSON;
import com.dlq.blog.dao.User;
import com.dlq.blog.dao.UserDao;
import com.dlq.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author donglq
 * @date 2017/10/4 11:09
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/insert")
    @ResponseBody
    public String insert() {
        User user = new User();
        user.setFirstname("lq");
        user.setLastname("dong");
        user.setGender(1);
        user.setIdcard("211383199202085436");
        user.setAddress("湖南长沙");
        return userService.insert(user);
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get() {
        User user = new User();
        user.setIdcard("211383199202085436");
        String result = userService.get(user).toString();
        return result;
    }

    @RequestMapping(value = "/getByIdcard")
    @ResponseBody
    public String getByIdcard() {
        return JSON.toJSONString(userService.getByIdcard("211383199202085436"));
    }

    @RequestMapping(value = "/write")
    @ResponseBody
    public Object write() {
        User user = new User();
        user.setFirstname("lq");
        user.setLastname("dong");
        user.setGender(1);
        user.setIdcard("211383199202085437");
        user.setAddress("湖南长沙");
        return userService.write(user);
    }

    @RequestMapping(value = "/read")
    @ResponseBody
    public String read() {
        User user = new User();
        user.setIdcard("211383199202085437");
        String result = userService.read(user).toString();
        return result;
    }

}
