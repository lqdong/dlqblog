/**
 * 
 */
package com.dlq.blog.start;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author Donglq
 * @date 2017年9月18日 上午12:10:52
 */
public class StartBlog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        try {
            context.setResourceBase("src/main/webapp");
            server.setHandler(context);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
