/**
 * 
 */
package com.dlq.blog.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * home页
 * @author Donglq
 * @date 2017年9月17日 下午11:05:33
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@RequestMapping(value = "/show")
	@ResponseBody
	public String show() {
		return "welcome to my blog, it's building......";
	}
	
}
